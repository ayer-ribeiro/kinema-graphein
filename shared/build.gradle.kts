plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktorfit)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
//    listOf(
//        iosX64(),
//        iosArm64(),
//        iosSimulatorArm64()
//    ).forEach {
//        it.binaries.framework {
//            baseName = "shared"
//            isStatic = true
//        }
//    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.koin.core)

            implementation(libs.ktorfit.lib)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.contentNegotiation)
            implementation(libs.ktor.json)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.napier)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.koin.test)
            implementation(libs.koin.test.junit4)
        }
    }
}

dependencies {
    with(libs.ktorfit.ksp) {
        add("kspCommonMainMetadata", this)
//        add("kspJvm", this)
//        add("kspJvmTest", this)
        add("kspAndroid", this)
        add("kspAndroidTest", this)
//        add("kspIosX64", this)
//        add("kspIosX64Test", this)
//        add("kspIosArm64", this)
//        add("kspIosArm64Test", this)
//        add("kspIosSimulatorArm64", this)
//        add("kspIosSimulatorArm64Test", this)
//        add("kspMacosX64", this)
//        add("kspMacosX64Test", this)
//        add("kspJs", this)
//        add("kspJsTest", this)
    }
}

android {
    namespace = "dev.ayer.kinemagraphein"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
