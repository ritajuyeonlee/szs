plugins {
    id("java")
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"

}
configurations {
    all {
        exclude("org.springframework.boot", "spring-boot-starter-logging")
    }
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.auth0:java-jwt:3.8.1")

    runtimeOnly("com.h2database:h2")


    // Spring Framework & Spring Cloud
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.5")

    testImplementation("com.h2database:h2")
    testImplementation("io.mockk:mockk:1.13.2")
    testImplementation("org.awaitility:awaitility:3.0.0")
    testImplementation("com.github.tomakehurst:wiremock:2.27.2")
    testImplementation("io.rest-assured:kotlin-extensions:5.3.0")
    testImplementation("io.rest-assured:spring-mock-mvc:5.3.0")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    //implementation("com.querydsl:querydsl-jpa:${queryDslVersion}:jakarta") -> 둘 다 자카르타!
    //annotationProcessor("com.querydsl:querydsl-apt:${queryDslVersion}:jakarta") -> 둘 다 자카르타!
    annotationProcessor("jakarta.annotation:jakarta.annotation-api")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")


}

tasks.test {
    useJUnitPlatform()
}