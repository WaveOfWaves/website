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
    implementation("id.zelory:compressor:3.0.1")
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
