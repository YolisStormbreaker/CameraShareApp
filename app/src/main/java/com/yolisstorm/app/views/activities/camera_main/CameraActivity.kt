package com.yolisstorm.app.views.activities.camera_main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.Camera
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import androidx.databinding.DataBindingUtil
import com.yolisstorm.app.R
import com.yolisstorm.app.databinding.ActivityCameraBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.concurrent.ExecutorService


class CameraActivity : AppCompatActivity() {

	private lateinit var binding: ActivityCameraBinding
	private val viewModel : CameraViewModel by lazy { getViewModel(CameraViewModel::class) }
	private lateinit var viewFinder : PreviewView

	private var preview: Preview? = null
	private var imageCapture: ImageCapture? = null
	private var camera: Camera? = null

	/** Blocking camera operations are performed using this executor */
	private lateinit var cameraExecutor: ExecutorService

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)

		binding = DataBindingUtil.setContentView(this, R.layout.activity_camera)

		binding.lifecycleOwner = this

		binding.viewModel = viewModel

		viewFinder = binding.viewFinder

	}


}