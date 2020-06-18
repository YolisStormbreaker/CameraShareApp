package com.yolisstorm.app.enums

enum class CaptureStatus(val status: Int) {

	ReadyToShoot(0),
	FileSaved(1);

	companion object {
		fun getByValue(value: Int) = values().find { it.status == value }
	}
}