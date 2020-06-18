package com.yolisstorm.app.views.activities.camera_main

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yolisstorm.app.enums.CaptureStatus
import com.yolisstorm.app.utils.Event

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

	private val _savedFilePath = MutableLiveData<Uri?>(null)
	val filePath : LiveData<Uri?>
	    get() = _savedFilePath

}