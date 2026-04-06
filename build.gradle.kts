plugins {
    id("java")
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
    // Source: https://mvnrepository.com/artifact/io.grpc/grpc-netty-shaded
    implementation("io.grpc:grpc-netty-shaded:1.80.0")
    // Source: https://mvnrepository.com/artifact/io.grpc/grpc-protobuf
    implementation("io.grpc:grpc-protobuf:1.80.0")
    // Source: https://mvnrepository.com/artifact/io.grpc/grpc-stub
    implementation("io.grpc:grpc-stub:1.80.0")
}

//Spring
dependencies {
    // Source: https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web
    implementation("org.springframework.boot:spring-boot-starter-web:3.5.13")
    implementation("org.springframework.boot:spring-boot-data-jpa:3.5.13")
}

// JUnit
dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// Lombok
dependencies {
    implementation("org.projectlombok:lombok:1.18.44")
    testImplementation("org.projectlombok:lombok:1.18.44")
    compileOnly("org.projectlombok:lombok:1.18.44")
    runtimeOnly("org.projectlombok:lombok:1.18.44")
}

tasks.test {
    useJUnitPlatform()
}