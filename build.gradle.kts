plugins {
    groovy
	java
	alias(libs.plugins.springBootVersion)
	alias(libs.plugins.springDependencyManagement)
}

group = "com.developbetter"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(25)
	}
}

repositories {
	mavenCentral()
    maven { url = uri("https://packages.confluent.io./maven") }
}

extra["springCloudVersion"] = "2025.1.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
	implementation("org.springframework.cloud:spring-cloud-starter-vault-config")
	implementation("org.springframework.cloud:spring-cloud-stream")
	implementation("org.springframework.cloud:spring-cloud-stream-binder-kafka") {
		exclude(group = "org.apache.kafka", module = "kafka-clients")
	}
	implementation(libs.kafka.clients)
	implementation("org.springframework.boot:spring-boot-starter-kafka")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	developmentOnly("org.springframework.boot:spring-boot-docker-compose")

    testImplementation(platform(libs.spock.bom))
    testImplementation(libs.bundles.spock.spring.integration.testing)
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test") {
		exclude(group = "org.assertj", module = "assertj-core")
	}
	testImplementation(libs.assertj.core)
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:testcontainers-spock")
	testImplementation("org.springframework.cloud:spring-cloud-stream-test-binder")
	testImplementation("org.springframework.boot:spring-boot-starter-kafka-test") {
		exclude(group = "org.apache.kafka", module = "kafka-clients")
	}
	testImplementation(libs.kafka.clients)
	testImplementation("org.testcontainers:testcontainers-kafka")
	testImplementation("org.testcontainers:testcontainers-vault")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
