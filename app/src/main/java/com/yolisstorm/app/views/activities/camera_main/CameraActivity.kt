package com.yolisstorm.app.views.activities.camera_main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.yolisstorm.app.R
import com.yolisstorm.app.databinding.ActivityCameraBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel


class CameraActivity : AppCompatActivity() {

	private lateinit var binding: ActivityCameraBinding
	private val viewModel : CameraViewModel by lazy { getViewModel(CameraViewModel::class) }

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)

		binding = DataBindingUtil.setContentView(this, R.layout.activity_camera)

		binding.lifecycleOwner = this

		binding.viewModel = viewModel

	}
}