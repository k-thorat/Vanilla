import org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask

plugins {
    kotlin("multiplatform") version "1.3.71"
}

repositories {
    mavenCentral()
}

var frameworkName = "Vanilla"

kotlin {
    targets {
        jvm()
        iosArm64 {
            binaries.framework {
                baseName = frameworkName
            }
        }
        iosX64 {
            binaries.framework {
                baseName = frameworkName
            }
        }
    }
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        named("commonTest") {
            dependencies {
        		implementation(kotlin("test-common"))
        		implementation(kotlin("test-annotations-common"))
            }
        }
        named("jvmMain") {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
        named("jvmTest") {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
        val iosMain by creating {
            dependencies {
            }
        }
        val iosTest by creating {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        getByName("iosArm64Main") { dependsOn(iosMain) }
        getByName("iosArm64Test") { dependsOn(iosTest) }
        getByName("iosX64Main") { dependsOn(iosMain) }
        getByName("iosX64Test") { dependsOn(iosTest) }
    }
}