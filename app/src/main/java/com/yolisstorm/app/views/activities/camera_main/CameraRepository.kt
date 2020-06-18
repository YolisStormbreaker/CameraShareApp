package com.yolisstorm.app.views.activities.camera_main

class CameraRepository private constructor(
) {

    companion object {

        //Для Singleton
        @Volatile
        private var instance: CameraRepository? = null

        fun getInstance() =
                instance ?: synchronized(this) {
                    instance ?: CameraRepository().also { instance = it }
                }
        }

}