package com.yolisstorm.app.utils

import androidx.databinding.BindingAdapter
import com.google.android.material.textview.MaterialTextView
import com.yolisstorm.app.R
import com.yolisstorm.app.enums.CaptureStatus

@BindingAdapter("setStatus", "setFilePath")
fun MaterialTextView.setStatus(status: CaptureStatus?, filePath: String?) {
	text = when (status) {
		null -> {
			resources.getString(R.string.status_error)
		}
		CaptureStatus.ReadyToShoot -> {
			resources.getString(R.string.status_ready)
		}
		CaptureStatus.FileSaved -> {
			if (filePath == null)
				resources.getString(R.string.status_error)
			else
				resources.getString(R.string.status_saved_in_template, filePath)
		}
		CaptureStatus.Error -> {
			resources.getString(R.string.status_error)
		}
	}
}