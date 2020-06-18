import kotlin.reflect.full.memberProperties

private const val FEATURE_PREFIX = ":features"

// "Module" means "project" in terminology of Gradle API. To be specific each "Android module" is a Gradle "subproject"
@Suppress("unused")
object ModuleDependency {
	// All consts are accessed via reflection
	const val app = ":app"

	/*    const val FEATURE_ALBUM = ":feature_album"
		const val FEATURE_PROFILE = ":feature_profile"
		const val FEATURE_FAVOURITE = ":feature_favourite"*/
	const val LibraryCommon = ":libraries:common"
	const val LibraryFirebaseAnalytics = ":libraries:firebase_analytics"
	const val LibraryFirebaseAuth = ":libraries:firebase_auth"
	const val LibraryGoogleMapsAndPlaces = ":libraries:google_maps_places"
	const val LibraryLiveSharedPrefs = ":libraries:live_shared_prefs"
	const val DataSourceNetworkCovidStats = ":data_sources:network:covid_stats"
	const val DataSourceDatabase = ":data_sources:databases:main_database"
	const val DataSourceRepositoryCovidStats = ":data_sources:repositories:covid_stats_repo"
	const val FeatureSummary = ":features:summary"
	//const val LIBRARY_TEST_UTILS = ":library_test_utils"

	// False positive" function can be private"
	// See: https://youtrack.jetbrains.com/issue/KT-33610
	fun getAllModules() = ModuleDependency::class.memberProperties
		.filter { it.isConst }
		.map { it.getter.call().toString() }
		.toSet()

	fun getDynamicFeatureModules() = getAllModules()
		.filter { it.startsWith(FEATURE_PREFIX) }
		.toSet()
}
