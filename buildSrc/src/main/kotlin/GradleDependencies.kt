import org.gradle.api.artifacts.dsl.RepositoryHandler
import java.net.URI

object GradlePluginVersion {
	const val ANDROID_GRADLE                =   "4.0.0"
	const val KTLINT_GRADLE                 =   "9.1.1"
	const val DETEKT                        =   "1.9.1"
	const val CRASHLYTICS_GRADLE            =   CoreVersion.CRASHLYTICS_GRADLE
	const val KOTLIN                        =   CoreVersion.KOTLIN
	const val GOOGLE_GMS                    =   CoreVersion.GMS_GOOGLE_SERVICES
	const val GRADLE_UPDATER_VERSION_PLUGIN =   "0.28.0"
	const val SAFE_ARGS                     =   CoreVersion.NAVIGATION
}

object GradlePluginId {
	const val DETEKT                        =   "io.gitlab.arturbosch.detekt"
	const val KTLINT_GRADLE                 =   "org.jlleitschuh.gradle.ktlint"
	const val ANDROID_APPLICATION           =   "com.android.application"
	const val ANDROID_DYNAMIC_FEATURE       =   "com.android.dynamic-feature"
	const val ANDROID_LIBRARY               =   "com.android.library"
	const val KOTLIN_JVM                    =   "org.jetbrains.kotlin.jvm"
	const val KOTLIN_ANDROID                =   "org.jetbrains.kotlin.android"
	const val KOTLIN_ANDROID_EXTENSIONS     =   "org.jetbrains.kotlin.android.extensions"
	const val KOTLIN_SERIALIZATION          =   "org.jetbrains.kotlin.serialization"
	const val KOTLIN_KAPT                   =   "kotlin-kapt"
	const val GOOGLE_GMS_PLUGIN             =   "com.google.gms.google-services"
	const val CRASHLYTICS_PLUGIN            =   "com.google.firebase.crashlytics"
	const val GRADLE_UPDATE_PLUGIN          =   "com.github.ben-manes.versions"
	const val SAFE_ARGS                     =   "androidx.navigation.safeargs.kotlin"
}

object GradleOldWayPlugins {
	const val ANDROID_GRADLE                =   "com.android.tools.build:gradle:${GradlePluginVersion.ANDROID_GRADLE}"
	const val KOTLIN_GRADLE                 =   "org.jetbrains.kotlin:kotlin-gradle-plugin:${GradlePluginVersion.KOTLIN}"
	const val GOOGLE_GMS_SERVICES           =   "com.google.gms:google-services:${GradlePluginVersion.GOOGLE_GMS}"
	const val FIREBASE_CRASHLYTICS          =   "com.google.firebase:firebase-crashlytics-gradle:${GradlePluginVersion.CRASHLYTICS_GRADLE}"
	const val SAFE_ARGS                     =   "androidx.navigation:navigation-safe-args-gradle-plugin:${GradlePluginVersion.SAFE_ARGS}"
}

fun addRepos(handler : RepositoryHandler) {
	handler.google()
	handler.jcenter()
	handler.maven { url = URI.create("https://jitpack.io") }
	handler.maven { url = URI.create("https://androidx-dev-prod.appspot.com/snapshots/builds/6455973/artifacts/repository") }
}