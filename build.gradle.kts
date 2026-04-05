plugins {
    id("java")
}

group = "org.project"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// Tarantool
dependencies {
    implementation("io.tarantool:tarantool-client:1.5.0")
}

tasks.test {
    useJUnitPlatform()
}