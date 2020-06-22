package com.yolisstorm.app

import android.app.Application
import com.yolisstorm.app.BuildConfig
import com.yolisstorm.app.views.activities.camera_main.CameraKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE
import timber.log.Timber

class MainApplication : Application() {

	private val modulesList = listOf(
			CameraKoinModule
	)

	override fun onCreate() {
		super.onCreate()
		configureTimber()
		configureKoin()
	}

	private fun configureTimber() {
		if (BuildConfig.DEBUG)
			Timber.plant(Timber.DebugTree())
	}

	private fun configureKoin() {
		startKoin {
			logger(object : Logger() {
				override fun log(level: Level, msg: MESSAGE) {
					if (BuildConfig.DEBUG)
						when (level) {
							Level.DEBUG -> Timber.d(msg)
							Level.ERROR -> Timber.e(msg)
							Level.INFO -> Timber.i(msg)
							Level.NONE -> Timber.w(msg)
						}
				}
			})
			androidContext(this@MainApplication)
			modules(modulesList)
		}
	}

}
