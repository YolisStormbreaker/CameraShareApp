object AndroidDefaultConfig {
	const val COMPILE_SDK_VERSION = 30
	const val MIN_SDK_VERSION = 23
	const val TARGET_SDK_VERSION = 30
	const val BUILD_TOOLS_VERSION = "29.0.3"

	const val VERSION_CODE = 1
	const val VERSION_NAME = "1.0.0"

	const val ID = "com.yolisstorm.camerashareapp"
	const val TEST_INSTRUMENTATION_RUNNER = "android.support.test.runner.AndroidJUnitRunner"
}

interface BuildType {

	companion object {
		const val RELEASE = "release"
		const val DEBUG = "debug"
	}

	val isMinifyEnabled: Boolean
}

object BuildTypeDebug : BuildType {
	override val isMinifyEnabled = false
}

object BuildTypeRelease : BuildType {
	override val isMinifyEnabled = true
}

object TestOptions {
	const val IS_RETURN_DEFAULT_VALUES = true
}

interface ApplicationFlavor {
	val applicationIdSuffix : String
	val versionCode : Int
	val versionNameSuffix : String
	val flavorName : String
}

object CameraShareAPp : ApplicationFlavor {
	override val applicationIdSuffix: String = ".full"
	override val versionCode: Int = AndroidDefaultConfig.VERSION_CODE
	override val versionNameSuffix: String = "-full"
	override val flavorName: String = "CameraShareAPp"
}

