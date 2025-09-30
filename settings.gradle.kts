rootProject.name = "rivulet"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
  "rivulet-core",
  "rivulet-connectors:rivulet-source-mock",
  "rivulet-connectors:rivulet-sink-jdbc",
  "rivulet-jobs"
)


dependencyResolutionManagement {
  versionCatalogs {
    create("libs1") {
      from(files("./gradle/libs.versions.toml")) // Adjust path if needed
    }
  }
}

pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenCentral()
  }
}


