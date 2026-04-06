plugins {
    id("java")
    id("com.google.protobuf") version "0.9.6"
    id("org.springframework.boot") version "3.2.0"         // ← добавить
    id("io.spring.dependency-management") version "1.1.4"
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
    implementation("net.devh:grpc-server-spring-boot-starter:3.1.0.RELEASE")
    implementation("io.grpc:grpc-stub:1.62.2")
    implementation("io.grpc:grpc-protobuf:1.62.2")
    implementation("com.google.protobuf:protobuf-java:3.24.0")
    implementation("com.google.protobuf:protobuf-java-util:3.24.0")
}

//Other
dependencies {
    compileOnly("javax.annotation:javax.annotation-api:1.3.2")
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
    plugins {
        create("grpc") {  // ← этого не хватало
            artifact = "io.grpc:protoc-gen-grpc-java:1.62.2"
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                create("grpc")  // ← и этого
            }
        }
    }
}