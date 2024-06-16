plugins {
    id("java")
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.3"

}
configurations {
    all {
        exclude("org.springframework.boot", "spring-boot-starter-logging")
    }
}

group = "com.szs"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17


repositories {
    mavenCentral()
}
dependencies {

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
    implementation("org.hibernate:hibernate-validator:6.1.0.Final")


    runtimeOnly("com.h2database:h2")

    implementation("io.jsonwebtoken:jjwt-api:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.2")
    implementation("com.auth0:java-jwt:4.4.0")

    implementation("com.google.code.gson:gson:2.8.9")

    implementation("org.jsoup:jsoup:1.15.3")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")

    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.assertj:assertj-core:3.25.3")
    testImplementation("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")


}

tasks.test {
    useJUnitPlatform()
}