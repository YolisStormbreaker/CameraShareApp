package com.yolisstorm.app.utils

import android.net.Uri
import java.io.File

fun getFile(uri: Uri?) : File? =
	if (uri == null || uri.path == null)
		null
	else
		File(uri.path!!)