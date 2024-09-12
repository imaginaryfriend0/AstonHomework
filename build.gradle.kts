plugins {
    kotlin("jvm") version "1.8.20"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation("org.testng:testng:7.7.1")
}

application {
    mainClass.set("org.example.Main")
}

tasks.test {
    useTestNG()
}