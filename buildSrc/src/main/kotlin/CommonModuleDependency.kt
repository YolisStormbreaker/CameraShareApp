import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler

/*
Define common dependencies, so they can be easily updated across feature modules
 */
fun DependencyHandler.addTestDependencies() {

    testImplementation(TestLibraryDependency.JUNIT)
    androidTestImplementation(TestLibraryDependency.JUNIT)
    androidTestImplementation(TestLibraryDependency.TEST_RUNNER)
    androidTestImplementation(TestLibraryDependency.ESPRESSO_CORE)
    testImplementation(TestLibraryDependency.KLUENT)
    androidTestImplementation(TestLibraryDependency.KLUENT_ANDROID)
    testImplementation(TestLibraryDependency.MOCKITO_INLINE)
    androidTestImplementation(TestLibraryDependency.MOCKITO_ANDROID)
    testImplementation(TestLibraryDependency.MOCKITO_KOTLIN)
    testImplementation(TestLibraryDependency.COROUTINES_TEST)
    testImplementation(TestLibraryDependency.ANDROID_X_CORE_TESTING)
}

/*
 * These extensions mimic the extensions that are generated on the fly by Gradle.
 * They are used here to provide above dependency syntax that mimics Gradle Kotlin DSL
 * syntax in module\build.gradle.kts.kts.kts files.
 */
@Suppress("detekt.UnusedPrivateMember")
private fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

@Suppress("detekt.UnusedPrivateMember")
private fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

@Suppress("detekt.UnusedPrivateMember")
private fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

private fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

private fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

private fun DependencyHandler.project(
    path: String,
    configuration: String? = null
): ProjectDependency {
    val notation = if (configuration != null) {
        mapOf("path" to path, "configuration" to configuration)
    } else {
        mapOf("path" to path)
    }

    return uncheckedCast(project(notation))
}

@Suppress("unchecked_cast", "nothing_to_inline", "detekt.UnsafeCast")
private inline fun <T> uncheckedCast(obj: Any?): T = obj as T
