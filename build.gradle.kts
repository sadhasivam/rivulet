plugins {
  id("java")
  // Use alias from version catalog instead of hardcoding version
  id("com.diffplug.spotless") version "8.0.0" apply false

}

allprojects {
  group = "com.ssj.rivulet"
  version = "0.1.0-SNAPSHOT"

  repositories {
    mavenCentral()
  }
}

subprojects {
  apply(plugin = "java")
  apply(plugin = "com.diffplug.spotless")

  java {
    toolchain {
      languageVersion.set(JavaLanguageVersion.of(17))
    }
  }

  dependencies {
    // Testing framework

    // testImplementation(platform(libs.junit.bom))
    //testImplementation(libs.bundles.testing)

    testImplementation(platform("org.junit:junit-bom:6.0.0-RC3"))

    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Logging (available for all subprojects)
    //implementation(libs.bundles.logging)
  }

  tasks.test {
    useJUnitPlatform()
  }

  // Configure Spotless
  configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    java {
      googleJavaFormat("1.24.0").aosp()
      removeUnusedImports()
      trimTrailingWhitespace()
      endWithNewline()
    }
    kotlinGradle {
      target("*.gradle.kts", "**/*.gradle.kts")
      ktlint()
        .editorConfigOverride(
          mapOf(
            "indent_size" to "4",
            "continuation_indent_size" to "4",
            "max_line_length" to "120",
            "insert_final_newline" to "true",
            "ktlint_standard_no-wildcard-imports" to "disabled"
          )
        )
      trimTrailingWhitespace()
      endWithNewline()
    }

    // Format TOML files (version catalogs, etc.)
    format("toml") {
      target("*.toml", "**/*.toml")
      trimTrailingWhitespace()
      endWithNewline()
      // Optional: use prettier for TOML formatting
      // prettier(mapOf("parser" to "toml")).config(mapOf("tabWidth" to 2))
    }

    // Format properties files
    format("properties") {
      target("*.properties", "**/*.properties")
      trimTrailingWhitespace()
      endWithNewline()
    }

    // Format Markdown files
    format("markdown") {
      target("*.md", "**/*.md")
      trimTrailingWhitespace()
      endWithNewline()
      // Optional: use prettier for markdown
      // prettier(mapOf("parser" to "markdown"))
    }

    // Format JSON files
    json {
      target("*.json", "**/*.json")
      gson()
        .indentWithSpaces(2)
        .sortByKeys()
      trimTrailingWhitespace()
      endWithNewline()
    }

    // Format YAML files
    format("yaml") {
      target("*.yml", "*.yaml", "**/*.yml", "**/*.yaml")
      trimTrailingWhitespace()
      endWithNewline()
      // Optional: use prettier for YAML
      // prettier(mapOf("parser" to "yaml"))
    }

  }
}
