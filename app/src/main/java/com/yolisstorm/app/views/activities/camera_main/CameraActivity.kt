package com.yolisstorm.app.views.activities.camera_main

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yolisstorm.app.R
import com.yolisstorm.app.databinding.ActivityCameraBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module


class CameraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)

        val binding : ActivityCameraBinding = DataBindingUtil.setContentView(this, R.layout.activity_camera)

        val viewModel by viewModel<CameraViewModel>()

        binding.lifecycleOwner = this

	    binding.viewModel = viewModel

    }
}