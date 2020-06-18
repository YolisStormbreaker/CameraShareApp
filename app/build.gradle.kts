plugins {
	id(GradlePluginId.ANDROID_APPLICATION)
	kotlin("android")
	kotlin("android.extensions")
	id(GradlePluginId.DETEKT)
	id(GradlePluginId.CRASHLYTICS_PLUGIN)
	id(GradlePluginId.GRADLE_UPDATE_PLUGIN)
}

android {
	compileSdkVersion(AndroidDefaultConfig.COMPILE_SDK_VERSION)
	buildToolsVersion(AndroidDefaultConfig.BUILD_TOOLS_VERSION)

	defaultConfig {
		applicationId = AndroidDefaultConfig.ID
		minSdkVersion(AndroidDefaultConfig.MIN_SDK_VERSION)
		targetSdkVersion(AndroidDefaultConfig.TARGET_SDK_VERSION)
		versionCode = AndroidDefaultConfig.VERSION_CODE
		versionName = AndroidDefaultConfig.VERSION_NAME

		testInstrumentationRunner = AndroidDefaultConfig.TEST_INSTRUMENTATION_RUNNER

	}
	flavorDimensions("version")
	productFlavors {
		create(CameraShareAPp.flavorName) {
			applicationIdSuffix  = CameraShareAPp.applicationIdSuffix
			versionCode = CameraShareAPp.versionCode
			versionNameSuffix  = CameraShareAPp.versionNameSuffix
		}
	}
	buildTypes {
		getByName(BuildType.RELEASE) {
			isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
			proguardFiles("proguard-android.txt", "proguard-rules.pro")
			addManifestPlaceholders(mapOf("enableCrashReporting" to "true", "enableFirebaseAnalyticsReporting" to "true"))
		}

		getByName(BuildType.DEBUG) {
			isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
			addManifestPlaceholders(mapOf("enableCrashReporting" to "false", "enableFirebaseAnalyticsReporting" to "false"))
		}

		compileOptions {
			sourceCompatibility = JavaVersion.VERSION_1_8
			targetCompatibility = JavaVersion.VERSION_1_8
		}

		testOptions {
			unitTests.isReturnDefaultValues = TestOptions.IS_RETURN_DEFAULT_VALUES
		}
	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}

	kotlinOptions {
		jvmTarget = JavaVersion.VERSION_1_8.toString()
	}

	buildFeatures.dataBinding = true

}

dependencies {

	implementation(LibraryDependencies.Kotlin.Core)
	implementation(LibraryDependencies.Kotlin.Reflection)
	implementation(LibraryDependencies.Kotlin.Coroutines.Android)

	implementation(LibraryDependencies.Main.Timber)
	implementation(LibraryDependencies.AndroidSupport.AppCompat)
	implementation(LibraryDependencies.AndroidSupport.CoreKtx)
	implementation(LibraryDependencies.AndroidSupport.Fragment.FragmentRuntimeKtx)

	implementation(LibraryDependencies.Koin.Core)
	implementation(LibraryDependencies.Koin.ViewModel)
	implementation(LibraryDependencies.Koin.Ext)

	implementation(LibraryDependencies.AndroidSupport.Design.ConstraintLayout)
	implementation(LibraryDependencies.AndroidSupport.Design.Material)

	implementation(LibraryDependencies.Lifecycle.Extensions)
	implementation(LibraryDependencies.Lifecycle.LivedataKtx)
	implementation(LibraryDependencies.Lifecycle.ViewModelKtx)

	implementation(LibraryDependencies.CameraX.Camera2)
	implementation(LibraryDependencies.CameraX.View)
}

fun com.android.build.gradle.internal.dsl.BaseFlavor.buildConfigFieldFromGradleProperty(gradlePropertyName: String) {
	val propertyValue = project.properties[gradlePropertyName] as? String
	checkNotNull(propertyValue) { "Gradle property $gradlePropertyName is null" }

	val androidResourceName = "GRADLE_${gradlePropertyName.toSnakeCase()}".toUpperCase()
	buildConfigField("String", androidResourceName, propertyValue)
}

fun getDynamicFeatureModuleNames() = ModuleDependency.getDynamicFeatureModules()
	.map { it.replace(":feature:", "") }
	.toSet()

fun String.toSnakeCase() = this.split(Regex("(?=[A-Z])")).joinToString("_") { it.toLowerCase() }

fun com.android.build.gradle.internal.dsl.DefaultConfig.buildConfigField(name: String, value: Set<String>) {
	// Generates String that holds Java String Array code
	val strValue = value.joinToString(prefix = "{", separator = ",", postfix = "}", transform = { "\"$it\"" })
	buildConfigField("String[]", name, strValue)
}