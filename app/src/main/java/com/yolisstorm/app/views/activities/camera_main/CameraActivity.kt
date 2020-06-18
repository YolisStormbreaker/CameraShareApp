package com.yolisstorm.app.views.activities.camera_main

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.webkit.MimeTypeMap
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.core.net.toFile
import androidx.databinding.DataBindingUtil
import com.google.common.util.concurrent.ListenableFuture
import com.yolisstorm.app.R
import com.yolisstorm.app.databinding.ActivityCameraBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel
import timber.log.Timber
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min


class CameraActivity : AppCompatActivity() {

	private lateinit var binding: ActivityCameraBinding
	private val viewModel : CameraViewModel by lazy { getViewModel(CameraViewModel::class) }
	private lateinit var viewFinder : PreviewView

	private var preview: Preview? = null
	private var imageCapture: ImageCapture? = null
	private var camera: Camera? = null
	private lateinit var cameraProviderFuture : ListenableFuture<ProcessCameraProvider>
	private var cameraProvider: ProcessCameraProvider? = null
	private var lensFacing: Int = CameraSelector.LENS_FACING_BACK
	private lateinit var outputDirectory: File

	/** Blocking camera operations are performed using this executor */
	private lateinit var cameraExecutor: ExecutorService

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)

		binding = DataBindingUtil.setContentView(this, R.layout.activity_camera)

		binding.lifecycleOwner = this

		binding.viewModel = viewModel

		viewFinder = binding.viewFinder

	}

	fun setUp(viewFinder: PreviewView) {
		cameraExecutor = Executors.newSingleThreadExecutor()

		outputDirectory = getOutputDirectory(applicationContext)

		viewFinder.post {
			setUpCamera()
		}
	}

	private fun setUpCamera() {
		cameraProviderFuture = ProcessCameraProvider.getInstance(applicationContext)
		cameraProviderFuture.addListener(Runnable {

			// CameraProvider
			cameraProvider = cameraProviderFuture.get()

			// Select lensFacing depending on the available cameras
			lensFacing = when {
				hasBackCamera() -> CameraSelector.LENS_FACING_BACK
				hasFrontCamera() -> CameraSelector.LENS_FACING_FRONT
				else -> throw IllegalStateException("Back and front camera are unavailable")
			}

			// Build and bind the camera use cases
			bindCameraUseCases()

		}, ContextCompat.getMainExecutor(applicationContext))
	}

	private fun hasBackCamera(): Boolean {
		return cameraProvider?.hasCamera(CameraSelector.DEFAULT_BACK_CAMERA) ?: false
	}

	private fun hasFrontCamera(): Boolean {
		return cameraProvider?.hasCamera(CameraSelector.DEFAULT_FRONT_CAMERA) ?: false
	}

	private fun aspectRatio(width: Int, height: Int): Int {
		val previewRatio = max(width, height).toDouble() / min(width, height)
		if (abs(previewRatio - RATIO_4_3_VALUE) <= abs(previewRatio - RATIO_16_9_VALUE)) {
			return AspectRatio.RATIO_4_3
		}
		return AspectRatio.RATIO_16_9
	}

	private fun bindCameraUseCases() {

		// Get screen metrics used to setup camera for full screen resolution
		val metrics = DisplayMetrics().also { viewFinder.display.getRealMetrics(it) }
		Timber.d("Screen metrics: ${metrics.widthPixels} x ${metrics.heightPixels}")

		val screenAspectRatio = aspectRatio(metrics.widthPixels, metrics.heightPixels)
		Timber.d("Preview aspect ratio: $screenAspectRatio")

		val rotation = viewFinder.display.rotation

		// CameraProvider
		val cameraProvider = cameraProvider
			?: throw IllegalStateException("Camera initialization failed.")

		// CameraSelector
		val cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()

		// Preview
		preview = Preview.Builder()
			// We request aspect ratio but no resolution
			.setTargetAspectRatio(screenAspectRatio)
			// Set initial target rotation
			.setTargetRotation(rotation)
			.build()

		// ImageCapture
		imageCapture = ImageCapture.Builder()
			.setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
			// We request aspect ratio but no resolution to match preview config, but letting
			// CameraX optimize for whatever specific resolution best fits our use cases
			.setTargetAspectRatio(screenAspectRatio)
			// Set initial target rotation, we will have to call this again if rotation changes
			// during the lifecycle of this use case
			.setTargetRotation(rotation)
			.setFlashMode(ImageCapture.FLASH_MODE_OFF)
			.build()

		// Must unbind the use-cases before rebinding them
		cameraProvider.unbindAll()

		try {
			// A variable number of use-cases can be passed here -
			// camera provides access to CameraControl & CameraInfo
			camera = cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)

			// Attach the viewfinder's surface provider to preview use case
			preview?.setSurfaceProvider(viewFinder.createSurfaceProvider())
		} catch (exc: Exception) {
			Timber.e("Use case binding failed - $exc")
		}
	}

	private fun capturePhoto() : File {
		// Get a stable reference of the modifiable image capture use case
		imageCapture?.let { imageCapture ->

			// Create output file to hold the image
			val photoFile = createFile(outputDirectory, FILENAME, PHOTO_EXTENSION)

			// Setup image capture metadata
			val metadata = ImageCapture.Metadata().apply {

				// Mirror image when using the front camera
				isReversedHorizontal = lensFacing == CameraSelector.LENS_FACING_FRONT
			}

			// Create output options object which contains file + metadata
			val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile)
				.setMetadata(metadata)
				.build()

			// Setup image capture listener which is triggered after photo has been taken
			imageCapture.takePicture(
				outputOptions, cameraExecutor, object : ImageCapture.OnImageSavedCallback {
					override fun onError(exc: ImageCaptureException) {
						Timber.e("Photo capture failed: ${exc.message}")
					}

					override fun onImageSaved(output: ImageCapture.OutputFileResults) {
						val savedUri = output.savedUri ?: Uri.fromFile(photoFile)
						Timber.d("Photo capture succeeded: $savedUri")

						/*// We can only change the foreground Drawable using API level 23+ API
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
							// Update the gallery thumbnail with latest picture taken
							//setGalleryThumbnail(savedUri)
						}

						// Implicit broadcasts will be ignored for devices running API level >= 24
						// so if you only target API level 24+ you can remove this statement
						if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
							requireActivity().sendBroadcast(
								Intent(android.hardware.Camera.ACTION_NEW_PICTURE, savedUri)
							)
						}*/

						// If the folder selected is an external media directory, this is
						// unnecessary but otherwise other apps will not be able to access our
						// images unless we scan them using [MediaScannerConnection]
						val mimeType = MimeTypeMap.getSingleton()
							.getMimeTypeFromExtension(savedUri.toFile().extension)
						MediaScannerConnection.scanFile(
							applicationContext,
							arrayOf(savedUri.toFile().absolutePath),
							arrayOf(mimeType)
						) { _, uri ->
							Timber.d("Image capture scanned into media store: $uri")
						}
					}
				})
		}
	}

	companion object {

		private const val TAG = "CameraShareApp"
		private const val FILENAME = "yyyy-MM-dd-HH-mm-ss-SSS"
		private const val PHOTO_EXTENSION = ".jpg"
		private const val RATIO_4_3_VALUE = 4.0 / 3.0
		private const val RATIO_16_9_VALUE = 16.0 / 9.0

		private fun createFile(baseFolder: File, format: String, extension: String) =
			File(baseFolder, SimpleDateFormat(format, Locale.getDefault())
				.format(System.currentTimeMillis()) + extension)

		private fun getOutputDirectory(context: Context): File {
			val appContext = context.applicationContext
			val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
				File(it, appContext.resources.getString(R.string.app_name)).apply { mkdirs() } }
			return if (mediaDir != null && mediaDir.exists())
				mediaDir else appContext.filesDir
		}
	}

}