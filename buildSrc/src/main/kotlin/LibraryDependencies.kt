
private object LibraryVersion {

	const val ACTIVITY 						= 	"1.2.0-alpha05"
	const val APPCOMPAT 					= 	"1.3.0-alpha01"
	const val BTN_WITH_CIRCLE_LOADER 		= 	"2.2.0"
	const val CAMERA_X                      =   "1.0.0-beta05"
	const val CAMERA_X_ADDITIONAL           =   "1.0.0-alpha12"
	const val CIRCLE_IMAGEVIEW 				= 	"3.0.1"
	const val CONSTRAINT_LAYOUT 			= 	"2.0.0-beta7"
	const val COORDINATOR_LAYOUT 			= 	"1.1.0"
	const val CORE_KTX 						= 	"1.5.0-alpha01"
	const val FIREBASE_AUTH 				= 	"19.1.0"
	const val FIREBASE_CRASHLYTICS 			= 	"2.10.1"
	const val FIREBASE_CORE 				= 	"17.2.0"
	const val FIREBASE_KTX 					= 	"19.1.0"
	const val FIREBASE_MESSAGING 			= 	"20.0.0"
	const val FLOATING_SEARCH_VIEW 			= 	"2.1.1"
	const val FRAGMENT 						= 	"1.3.0-alpha06"
	const val GLIDE 						= 	"4.11.0"
	const val G_MAPS 						= 	"17.0.0"
	const val G_MAPS_KTX 					= 	"0.3.1"
	const val G_MAPS_LOCATION 				= 	"17.0.0"
	const val G_MAPS_PLACES 				= 	"2.2.0"
	const val G_MAPS_UTILS_KTX 				= 	"0.3.1"
	const val G_PLAY_SERVICES 				= 	"17.0.0"
	const val GSON 							= 	"2.8.6"
	const val LEAKCANARY 					= 	"2.3"
	const val LIFECYCLE 					= 	"2.3.0-alpha04"
	const val LIFECYCLE_EXT 				= 	"2.2.0"
	const val MATERIAL_CALENDAR 			= 	"1.7.0"
	const val MATERIAL 						= 	"1.3.0-alpha01"
	const val OKHTTP_3 						= 	"4.6.0"
	const val OKHTTP_LOGGING_INTERCEPTOR 	= 	"4.6.0"
	const val PAGING 						= 	"3.0.0-SNAPSHOT"
	const val PLAY_CORE 					= 	"1.7.2"
	const val RECYCLER_VIEW_SELECTION 		= 	"1.1.0-rc01"
	const val RECYCLER_VIEW 				= 	"1.2.0-alpha03"
	const val RETROFIT 						= 	"2.7.2"
	const val RETROFIT_COROUTINES   		= 	"0.9.2"
	const val ROOM 							= 	"2.2.4"
	const val ROUNDED_IMAGEVIEW 			= 	"2.3.0"
	const val SERIALIZATION_RUNTIME 		= 	"0.20.0"
	const val SHIMMER						= 	"0.5.0"
	const val SWIPTE_TO_REFRESH_LAYOUT 		= 	"1.1.0-rc01"
	const val SUPPORT 						= 	"1.1.0"
	const val SUPPORT_CARD_VIEW 			= 	"1.0.0"
	const val SUPPORT_CORE_UTILS 			= 	"1.0.0"
	const val TIMBER 						= 	"4.7.1"
	const val WORK_MANAGER 					= 	"2.4.0-beta01"

}

object LibraryDependencies {

	object AndroidSupport {

		const val ActivityKtx   =   "androidx.activity:activity-ktx:${LibraryVersion.ACTIVITY}"

		const val AppCompat     =   "androidx.appcompat:appcompat:${LibraryVersion.APPCOMPAT}"
		const val CoreKtx       =   "androidx.core:core-ktx:${LibraryVersion.CORE_KTX}"
		const val CoreUtils     =   "androidx.legacy:legacy-support-core-utils:${LibraryVersion.SUPPORT_CORE_UTILS}"
		const val Paging        =   "androidx.paging:paging-runtime:${LibraryVersion.PAGING}"
		const val PlayCore      =   "com.google.android.play:core:${LibraryVersion.PLAY_CORE}"

		object Design {
			const val CardView              =   "androidx.cardview:cardview:${LibraryVersion.SUPPORT_CARD_VIEW}"
			const val ConstraintLayout      =   "androidx.constraintlayout:constraintlayout:${LibraryVersion.CONSTRAINT_LAYOUT}"
			const val CoordinatorLayout     =   "androidx.coordinatorlayout:coordinatorlayout:${LibraryVersion.COORDINATOR_LAYOUT}"
			const val Material              =   "com.google.android.material:material:${LibraryVersion.MATERIAL}"
			const val Recyclerview          =   "androidx.recyclerview:recyclerview:${LibraryVersion.RECYCLER_VIEW}"
			const val RecyclerviewSelection =   "androidx.recyclerview:recyclerview-selection:${LibraryVersion.RECYCLER_VIEW_SELECTION}"
			const val SwipeToRefreshLayout  =   "androidx.swiperefreshlayout:swiperefreshlayout:${LibraryVersion.SWIPTE_TO_REFRESH_LAYOUT}"
		}

		object Fragment {
			const val FragmentRuntimeKtx    =   "androidx.fragment:fragment-ktx:${LibraryVersion.FRAGMENT}"
			const val FragmentTesting       =   "androidx.fragment:fragment-testing:${LibraryVersion.FRAGMENT}"
		}

		object Gms {
			const val GoogleServices        =  "com.google.gms:google-services:${CoreVersion.GMS_GOOGLE_SERVICES}"

			object PlayServices {
					const val Maps              =   "com.google.android.gms:play-services-maps:${LibraryVersion.G_PLAY_SERVICES}"
			}
		}

	}

	object CameraX {
		const val Camera2               =   "androidx.camera:camera-camera2:${LibraryVersion.CAMERA_X}"
		//The following line is optional, as the core library is included indirectly by camera-camera2
		const val Core                  =   "androidx.camera:camera-core:${LibraryVersion.CAMERA_X}"
		// If you want to additionally use the CameraX Lifecycle library
		const val Lifecycle             =   "androidx.camera:camera-lifecycle:${LibraryVersion.CAMERA_X}"
		// If you want to additionally use the CameraX View class
		const val View                  =   "androidx.camera:camera-view:${LibraryVersion.CAMERA_X_ADDITIONAL}"
		// If you want to additionally use the CameraX Extensions library
		const val Extensions            =   "androidx.camera:camera-extensions:${LibraryVersion.CAMERA_X_ADDITIONAL}"
	}

	object Facebook {
		const val Shimmer = "com.facebook.shimmer:shimmer:${LibraryVersion.SHIMMER}"
	}

	object Firebase {
		const val Analytics =
			"com.google.firebase:firebase-analytics:${LibraryVersion.FIREBASE_CORE}"
		const val Auth = "com.google.firebase:firebase-auth:${LibraryVersion.FIREBASE_AUTH}"
		const val CommonKtx =
			"com.google.firebase:firebase-common-ktx:${LibraryVersion.FIREBASE_KTX}"
		const val Core = "com.google.firebase:firebase-core:${LibraryVersion.FIREBASE_CORE}"
		const val Crashlytics =
			"com.crashlytics.sdk.android:crashlytics:${LibraryVersion.FIREBASE_CRASHLYTICS}"
		const val Messaging =
			"com.google.firebase:firebase-messaging:${LibraryVersion.FIREBASE_MESSAGING}"
	}

	object Glide {
		const val Compiler = "com.github.bumptech.glide:compiler:${LibraryVersion.GLIDE}"
		const val Runtime = "com.github.bumptech.glide:glide:${LibraryVersion.GLIDE}"
	}

	object GoogleMaps {
		const val Location =
			"com.google.android.gms:play-services-location:${LibraryVersion.G_MAPS_LOCATION}"
		const val MapsKtx = "com.google.maps.android:maps-ktx:${LibraryVersion.G_MAPS_KTX}"
		const val MapsUtilsKtx =
			"com.google.maps.android:maps-utils-ktx:${LibraryVersion.G_MAPS_UTILS_KTX}"
		const val Places =
			"com.google.android.libraries.places:places:${LibraryVersion.G_MAPS_PLACES}"
	}

	object Koin {
		const val Core              =   "org.koin:koin-core:${CoreVersion.KOIN}"
		const val Scope             =   "org.koin:koin-androidx-scope:${CoreVersion.KOIN}"
		const val ViewModel         =   "org.koin:koin-androidx-viewmodel:${CoreVersion.KOIN}"
		const val Ext               =   "org.koin:koin-androidx-ext:${CoreVersion.KOIN}"
		const val Test              =   "org.koin:koin-test:${CoreVersion.KOIN}"
	}

	object Kotlin {
		const val Core = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${CoreVersion.KOTLIN_CORE}"

		object Coroutines {
			const val Core =
				"org.jetbrains.kotlinx:kotlinx-coroutines-core:${CoreVersion.COROUTINES_ANDROID}"
			const val Android =
				"org.jetbrains.kotlinx:kotlinx-coroutines-android:${CoreVersion.COROUTINES_ANDROID}"
		}

		const val Reflection =
			"org.jetbrains.kotlin:kotlin-reflect:${CoreVersion.KOTLIN_REFLECTION}"

	}

	object Lifecycle {
		const val Compiler      =   "androidx.lifecycle:lifecycle-compiler:${LibraryVersion.LIFECYCLE}"
		const val Coroutines    =   "androidx.lifecycle:lifecycle-livedata-ktx:${LibraryVersion.LIFECYCLE}"
		const val Java8         =   "androidx.lifecycle:lifecycle-common-java8:${LibraryVersion.LIFECYCLE}"
		const val Extensions    =   "androidx.lifecycle:lifecycle-extensions:${LibraryVersion.LIFECYCLE_EXT}"
		const val LivedataKtx   =   "androidx.lifecycle:lifecycle-livedata-ktx:${LibraryVersion.LIFECYCLE}"
		const val ViewModelKtx  =   "androidx.lifecycle:lifecycle-viewmodel-ktx:${LibraryVersion.LIFECYCLE}"
		const val Runtime       =   "androidx.lifecycle:lifecycle-runtime:${LibraryVersion.LIFECYCLE}"
	}

	object Main {
		const val Gson = "com.google.code.gson:gson:${LibraryVersion.GSON}"
		const val Leakcanary =
			"com.squareup.leakcanary:leakcanary-android:${LibraryVersion.LEAKCANARY}"
		const val Timber = "com.jakewharton.timber:timber:${LibraryVersion.TIMBER}"
	}

	object Navigation {
		const val FragmentKtx =
			"androidx.navigation:navigation-fragment-ktx:${CoreVersion.NAVIGATION}"
		const val UiKtx = "androidx.navigation:navigation-ui-ktx:${CoreVersion.NAVIGATION}"
		const val RuntimeKtx =
			"androidx.navigation:navigation-runtime-ktx:${CoreVersion.NAVIGATION}"
		const val DynamicFeature =
			"androidx.navigation:navigation-dynamic-features-fragment:${CoreVersion.NAVIGATION}"
	}

	object Okhttp {
		const val Main = "com.squareup.okhttp3:okhttp:${LibraryVersion.OKHTTP_3}"
		const val LoggingInterceptor =
			"com.squareup.okhttp3:logging-interceptor:${LibraryVersion.OKHTTP_LOGGING_INTERCEPTOR}"
	}

	object Other {
		const val BtnWithCircleLoader =
			"br.com.simplepass:loading-button-android:${LibraryVersion.BTN_WITH_CIRCLE_LOADER}"
		const val CircleImageView =
			"de.hdodenhof:circleimageview:${LibraryVersion.CIRCLE_IMAGEVIEW}"
		const val FloatingSearchView =
			"com.github.arimorty:floatingsearchview:${LibraryVersion.FLOATING_SEARCH_VIEW}"
		const val MaterialCalendar =
			"com.applandeo:material-calendar-view:${LibraryVersion.MATERIAL_CALENDAR}"
		const val RoundedImageView =
			"com.makeramen:roundedimageview:${LibraryVersion.ROUNDED_IMAGEVIEW}"
	}

	object Retrofit {
		const val Runtime = "com.squareup.retrofit2:retrofit:${LibraryVersion.RETROFIT}"
		const val Mock = "com.squareup.retrofit2:retrofit-mock:${LibraryVersion.RETROFIT}"
		const val Coroutine = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${LibraryVersion.RETROFIT_COROUTINES}"

		object Converters {
			const val Gson = "com.squareup.retrofit2:converter-gson:${LibraryVersion.RETROFIT}"
			const val Scalars =
				"com.squareup.retrofit2:converter-scalars:${LibraryVersion.RETROFIT}"
		}
	}

	object Room {
		const val Compiler = "androidx.room:room-compiler:${LibraryVersion.ROOM}"
		const val Ktx = "androidx.room:room-ktx:${LibraryVersion.ROOM}"
		const val Runtime = "androidx.room:room-runtime:${LibraryVersion.ROOM}"
	}

	object WorkManager {
		const val Firebase = "androidx.work:work-firebase:${LibraryVersion.WORK_MANAGER}"
		const val Runtime = "androidx.work:work-runtime:${LibraryVersion.WORK_MANAGER}"
		const val RuntimeKtx = "androidx.work:work-runtime-ktx:${LibraryVersion.WORK_MANAGER}"
		const val Testing = "androidx.work:work-testing:${LibraryVersion.WORK_MANAGER}"
	}
}
