plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    implementation("org.flywaydb:flyway-core:6.1.4")
    implementation("org.postgresql:postgresql:42.2.9")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}