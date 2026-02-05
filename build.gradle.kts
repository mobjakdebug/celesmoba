import java.text.SimpleDateFormat
import java.util.*

plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.serialization") version "2.1.0"
    id("org.jetbrains.compose") version "1.7.3"
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.0"
}

group = "org.mobwhy.celesmob"
version = "1.0.0"

println("Java: " + System.getProperty("java.version") + " JVM: " + System.getProperty("java.vm.version") + "(" + System.getProperty("java.vendor") + ") Arch: " + System.getProperty("os.arch"))
println("CelesMob Launcher -> https://celesmob.example.com/")

val isGitHubActions = System.getenv("GITHUB_ACTIONS") == "true"

if (isGitHubActions) {
    val timeStamp = SimpleDateFormat("yyyyMMdd-HHmm").format(Date())
    version = "nightly-$timeStamp"
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-receivers")
        freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
        freeCompilerArgs.add("-opt-in=androidx.compose.animation.ExperimentalAnimationApi")
        freeCompilerArgs.add("-opt-in=androidx.compose.material3.ExperimentalMaterial3Api")
    }

    sourceSets {
        named("main") {
            kotlin.srcDirs("src/main/kotlin/org/mobwhy/celesmob")
        }
    }
}

repositories {
    mavenCentral()
    maven("https://packages.jetbrains.team/maven/p/compose/dev")
    google()
    maven("https://androidx.dev/storage/repository/release")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    // Compose for Desktop
    implementation(compose.desktop.currentOs)
    implementation(compose.foundation)
    implementation(compose.material3)
    implementation(compose.ui)
    implementation(compose.animation)

    // Core dependencies
    implementation("com.auth0:java-jwt:4.5.0")
    implementation("org.slf4j:slf4j-api:2.0.17")
    implementation("org.slf4j:slf4j-log4j12:2.0.17")
    implementation("org.apache.logging.log4j:log4j-api:2.25.3")
    implementation("org.apache.logging.log4j:log4j-core:2.25.3")

    implementation("com.squareup.okhttp3:okhttp:5.1.0")
    implementation("com.google.code.gson:gson:2.13.2")
    implementation("commons-io:commons-io:2.19.0")
    implementation("org.java-websocket:Java-WebSocket:1.6.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1")
}

compose.desktop {
    application {
        mainClass = "org.mobwhy.celesmob.MainKt"

        nativeDistributions {
            targetFormats(org.jetbrains.compose.desktop.application.dsl.TargetFormat.Exe, org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi)
            packageName = "celesmob"
            packageVersion = "1.0.0"

            windows {
                menuGroup = "CelesMob"
                upgradeUuid = "8CE3BD7A-7D80-4A9A-AC77-363BE433021D"
            }
        }
    }
}

// Create a standalone fat JAR that works on any system with Java
tasks.register<Jar>("fatJar") {
    archiveFileName.set("celestial-${version}-standalone.jar")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    
    manifest {
        attributes["Main-Class"] = "org.mobwhy.celesmob.MainKt"
        attributes["Implementation-Title"] = "CelesMob Launcher"
        attributes["Implementation-Version"] = version
    }
    
    // Include the main compiled classes
    from(sourceSets["main"].output)
    
    // Include all runtime dependencies
    dependsOn(configurations.runtimeClasspath)
    configurations.runtimeClasspath.get().forEach { file ->
        if (file.isFile) {
            from(zipTree(file))
        }
    }
}

