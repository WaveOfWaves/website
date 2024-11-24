plugins {
    application
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.serialization") version "2.0.21"
}


repositories {
    mavenCentral()
    maven("https://repo.mineinabyss.com/releases")
    mavenLocal()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("me.dvyy:shocky:0.0.5")
    implementation("com.sksamuel.scrimage:scrimage-core:4.2.0")
    implementation("com.sksamuel.scrimage:scrimage-webp:4.2.0")
}

kotlin {
    jvmToolchain(21)
}

sourceSets {
    main {
        kotlin.srcDirs("src")
    }
}

application {
    mainClass = "MainKt"
}
