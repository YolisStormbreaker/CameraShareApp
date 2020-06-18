
buildscript {
    addRepos(repositories)
    dependencies {
        classpath(GradleOldWayPlugins.ANDROID_GRADLE)
        classpath(kotlin("gradle-plugin", version = CoreVersion.KOTLIN))
        classpath(kotlin("serialization", version = CoreVersion.KOTLIN))
        classpath(GradleOldWayPlugins.FIREBASE_CRASHLYTICS)
    }
}

plugins {
    id("com.github.ben-manes.versions") version GradlePluginVersion.GRADLE_UPDATER_VERSION_PLUGIN
    id(GradlePluginId.DETEKT) version GradlePluginVersion.DETEKT
}

allprojects {

    addRepos(repositories)
    apply(plugin = GradlePluginId.DETEKT)
}

subprojects {
    tasks.withType<Test> {
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
    }

    apply(plugin = GradlePluginId.DETEKT)

    detekt {
        config = files("${project.rootDir}/detekt.yml")
        parallel = true
        ignoreFailures = true // If set to `true` the build does not fail when the maxIssues count was reached. Defaults to `false`.
        reports {
            html {
                enabled = true                                // Enable/Disable HTML report (default: true)
                destination = file("build/reports/detekt.html") // Path where HTML report will be stored (default: `build/reports/detekt/detekt.html`)
            }
        }
    }
}


tasks {
    // Gradle versions plugin configuration
    "dependencyUpdates"(com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask::class) {
        resolutionStrategy {
            componentSelection {
                all {
                    // Do not show pre-release version of library in generated dependency report
                    val rejected = kotlin.collections.listOf(
                            "alpha",
                            "beta",
                            "rc",
                            "cr",
                            "m",
                            "preview"
                    )
                            .map { qualifier -> kotlin.text.Regex("(?i).*[.-]$qualifier[.\\d-]*") }
                            .any { it.matches(candidate.version) }
                    if (rejected) {
                        reject("Release candidate")
                    }

                    // kAndroid newest version is 0.8.8 (jcenter), however maven repository contains version 0.8.7 and
                    // plugin fails to recognize it correctly
                    if (candidate.group == "com.pawegio.kandroid") {
                        reject("version ${candidate.version} is broken for ${candidate.group}'")
                    }
                }
            }
        }
    }
}

task<Delete>("clean"){
    delete(rootProject.buildDir)
}