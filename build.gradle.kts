plugins {
	java
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.sonarqube") version "5.0.0.4638"
	jacoco
}

group = "code.luisfbejaranob"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
	finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
	dependsOn(tasks.test)
	reports {
		xml.required.set(true)
		csv.required.set(false)
		html.required.set(true)
	}
}

val sonarToken: String? by project

sonarqube {
	properties {
		property("sonar.projectKey", "test-scaffold-clean-architecture")
		property("sonar.projectName", "test-scaffold-clean-architecture")
		property("sonar.projectVersion", "1.0")
		property("sonar.host.url", "http://localhost:9000")
		sonarToken?.let { property("sonar.login", it) }
		property("sonar.coverage.jacoco.xmlReportPaths", "${project.layout.buildDirectory}/reports/jacoco/test/jacocoTestReport.xml")
		property("sonar.exclusions", "**/ObjectUtil.java")
		property("sonar.coverage.exclusions", "**/TestScaffoldCleanArchitectureApplication.java, **/GlobalExceptionHandler.java")
	}
}

tasks.named("sonarqube").configure {
	dependsOn("jacocoTestReport")
}