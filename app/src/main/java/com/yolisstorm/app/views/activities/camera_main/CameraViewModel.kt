package com.yolisstorm.app.views.activities.camera_main

import android.Manifest
import android.app.Activity
import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yolisstorm.app.R
import com.yolisstorm.app.enums.CaptureStatus
import com.yolisstorm.app.utils.Event
import com.yolisstorm.app.utils.checkAccessPermissionGranted
import com.yolisstorm.app.utils.requestPermission
import java.io.File

class CameraViewModel (
	private val repository: CameraRepository,
	application: Application
) : AndroidViewModel(application) {

	private val _wantToShare = MutableLiveData<Event<Unit>>()
	val wantToShare : LiveData<Event<Unit>>
	    get() = _wantToShare
	fun isTimeToShare() {_wantToShare.postValue(Event(Unit))}

	private val _captureStatus = MutableLiveData<CaptureStatus>(CaptureStatus.ReadyToShoot)
	val captureStatus : LiveData<CaptureStatus>
	    get() = _captureStatus
	fun updateStatus(status: CaptureStatus, file: File? = null) {
		when (status) {
			CaptureStatus.Error -> {
				_captureStatus.postValue(status)
			}
			CaptureStatus.ReadyToShoot,
			CaptureStatus.FileSaved -> {
				if (file != null) {
					_savedFilePath.postValue(Event(file))
					_captureStatus.postValue(status)
				} else if (_savedFilePath.value?.peekContent() == null && status == CaptureStatus.ReadyToShoot) {
					_captureStatus.postValue(status)
				}
			}
		}
	}

	private val _savedFilePath = MutableLiveData<Event<File?>>(Event(null))
	val savedFilePath : LiveData<Event<File?>>
	    get() = _savedFilePath

	private val _permissionGrantedResult = MutableLiveData<Event<Boolean>>()
	val permissionGrantedResult : LiveData<Event<Boolean>>
	    get() = _permissionGrantedResult
	fun changePermissionStatus(isGranted: Boolean) {
		_permissionGrantedResult.postValue(Event(isGranted))
	}

	fun requestCameraPermission(activity: Activity) {
		requestPermission(
			activity,
			Manifest.permission.CAMERA,
			activity.resources.getString(R.string.polite_camera_access_title),
			activity.resources.getString(R.string.polite_camera_access_content),
			REQUEST_CAMERA_PERMISSION
		)
	}

	fun checkIsCameraPermissionGranted(activity: Activity) {
		_permissionGrantedResult.postValue(Event(checkAccessPermissionGranted(activity, Manifest.permission.CAMERA)))
	}

	val REQUEST_CAMERA_PERMISSION = 2707

}