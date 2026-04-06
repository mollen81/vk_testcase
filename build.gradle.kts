plugins {
    id("java")
    id("com.google.protobuf") version "0.9.6"
}

group = "org.project"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
}


// Tarantool
dependencies {
    implementation("io.tarantool:tarantool-client:1.5.0")
    implementation("io.tarantool:spring-data-tarantool:0.5.2")
}

// gRPC
dependencies {
    // Source: https://mvnrepository.com/artifact/net.devh/grpc-server-spring-boot-starter
    implementation("net.devh:grpc-server-spring-boot-starter:3.1.0.RELEASE")
}

//Spring
dependencies {

}

// JUnit
dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// Lombok
dependencies {
    compileOnly("org.projectlombok:lombok:1.18.44")
    annotationProcessor("org.projectlombok:lombok:1.18.44")
    testCompileOnly("org.projectlombok:lombok:1.18.44")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.44")
}

tasks.test {
    useJUnitPlatform()
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.24.0"
    }
    generateProtoTasks {
        all().forEach { task ->

        }
    }
}