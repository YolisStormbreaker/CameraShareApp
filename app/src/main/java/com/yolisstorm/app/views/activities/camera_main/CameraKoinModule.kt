package com.yolisstorm.app.views.activities.camera_main

import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val CameraKoinModule  = module {
	single { CameraRepository.getInstance() }
	viewModel { CameraViewModel(get(), androidApplication()) }
}