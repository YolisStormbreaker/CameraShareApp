package com.yolisstorm.app.utils

import android.net.Uri
import androidx.databinding.BindingAdapter
import com.google.android.material.textview.MaterialTextView
import com.yolisstorm.app.R
import com.yolisstorm.app.enums.CaptureStatus

@BindingAdapter("setStatus", "setFilePath")
fun MaterialTextView.setStatus(status: CaptureStatus?, filePath: Uri?) {
	text = when (status) {
		null -> {
			resources.getString(R.string.status_error)
		}
		CaptureStatus.ReadyToShoot -> {
			resources.getString(R.string.status_ready)
		}
		CaptureStatus.FileSaved -> {
			if (filePath == null || filePath.path == null)
				resources.getString(R.string.status_error)
			else
				resources.getString(R.string.status_saved_in_template, filePath.path)
		}
	}
}