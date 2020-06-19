package com.yolisstorm.app.utils

import android.annotation.TargetApi
import android.app.Activity
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.yolisstorm.app.R


/**
 * Проверка на то, что разрешение дано
 *
 * @param activity
 * @param permission разрешение, которое нужно проверить
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun checkAccessPermissionGranted(activity: Activity, permission: String): Boolean =
	ContextCompat.checkSelfPermission(
		activity.applicationContext,
		permission
	) == PackageManager.PERMISSION_GRANTED

/**
 * Вежливый запрос доступа к разрешениям
 *
 * @param activity
 * @param permission Разрешение, которое нужно запросить
 * @param explanationTitle Заголовок диалога с объяснением причины
 * @param explanationContent Объяснение необходимости этого разрешения
 */
fun requestPermission(
	activity: Activity,
	permission: String,
	explanationTitle: String,
	explanationContent: String,
	requestCode: Int
) {
	if (!checkAccessPermissionGranted(activity, permission)) {
		// Permission is not granted
		// Should we show an explanation?
		if (ActivityCompat.shouldShowRequestPermissionRationale(
				activity, permission
			)
		) {
			// Show an explanation to the user *asynchronously* -- don't block
			// this thread waiting for the user's response! After the user
			// sees the explanation, try again to request the permission.
			val alertBuilder = AlertDialog.Builder(activity, R.style.DialogTheme)
			alertBuilder.setCancelable(true)
			alertBuilder.setTitle(explanationTitle)
			alertBuilder.setMessage(explanationContent)
			alertBuilder.setPositiveButton(
				android.R.string.yes
			) { _, _ ->
				request(activity, permission, requestCode)
			}.create()
			alertBuilder.show()
		} else {
			// No explanation needed, we can request the permission.
			request(activity, permission, requestCode)
		}
	}
}

/**
 * Запрос доступа к разрешению
 *
 * @param activity
 * @param permission разрешение на запрос
 */
@TargetApi(Build.VERSION_CODES.M)
private fun request(activity: Activity, permission: String, requestCode: Int) {
	activity.requestPermissions(
		arrayOf(permission),
		requestCode
	)
}
