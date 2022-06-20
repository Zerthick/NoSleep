/*
 * Copyright (C) 2018-2022 Zerthick
 *
 * This file is part of NoSleep.
 *
 * NoSleep is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NoSleep is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with NoSleep.  If not, see <http://www.gnu.org/licenses/>.
 */

import org.spongepowered.gradle.plugin.config.PluginLoaders
import org.spongepowered.plugin.metadata.model.PluginDependency

plugins {
    `java-library`
    id("org.spongepowered.gradle.plugin") version "2.0.2"
}

group = "io.github.zerthick"
version = "1.4.0"

repositories {
    mavenCentral()
}

sponge {
    apiVersion("9.0.0")
    license("GPLV3")
    loader {
        name(PluginLoaders.JAVA_PLAIN)
        version("1.0")
    }
    plugin("nosleep") {
        displayName("NoSleep")
        entrypoint("io.github.zerthick.nosleep.NoSleep")
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

val javaTarget = 17
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
        if (JavaVersion.current().isJava10Compatible) {
            release.set(javaTarget)
        }
    }
}

// Make sure all tasks which produce archives (jar, sources jar, javadoc jar, etc) produce more consistent output
tasks.withType(AbstractArchiveTask::class).configureEach {
    isReproducibleFileOrder = true
    isPreserveFileTimestamps = false
}
