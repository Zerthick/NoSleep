import org.spongepowered.gradle.plugin.config.PluginLoaders
import org.spongepowered.plugin.metadata.PluginDependency

plugins {
    `java-library`
    id("org.spongepowered.gradle.plugin") version "1.1.1"
}

group = "io.github.zerthick"
version = "1.3.0-alpha"

repositories {
    mavenCentral()
}

sponge {
    apiVersion("8.0.0")
    plugin("nosleep") {
        loader(PluginLoaders.JAVA_PLAIN)
        displayName("NoSleep")
        mainClass("io.github.zerthick.nosleep.NoSleep")
        description("A simple plugin to prevent sleeping through the night.")
        links {
            homepage("https://ore.spongepowered.org/Zerthick/NoSleep")
            source("https://github.com/Zerthick/NoSleep")
            issues("https://github.com/Zerthick/NoSleep/issues")
        }
        contributor("Zerthick") {
            description("Lead Developer")
        }
        dependency("spongeapi") {
            loadOrder(PluginDependency.LoadOrder.AFTER)
            optional(false)
        }
    }
}

val javaTarget = 8 // Sponge targets a minimum of Java 8
java {
    sourceCompatibility = JavaVersion.toVersion(javaTarget)
    targetCompatibility = JavaVersion.toVersion(javaTarget)
    if (JavaVersion.current() < JavaVersion.toVersion(javaTarget)) {
        toolchain.languageVersion.set(JavaLanguageVersion.of(javaTarget))
    }
}

tasks.withType(JavaCompile::class).configureEach {
    options.apply {
        encoding = "utf-8" // Consistent source file encoding
        release.set(javaTarget)
    }
}

// Make sure all tasks which produce archives (jar, sources jar, javadoc jar, etc) produce more consistent output
tasks.withType(AbstractArchiveTask::class).configureEach {
    isReproducibleFileOrder = true
    isPreserveFileTimestamps = false
}
