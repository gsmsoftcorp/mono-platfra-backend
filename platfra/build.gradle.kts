plugins {
	java
	id("org.springframework.boot") version "3.2.1"
	id("io.spring.dependency-management") version "1.1.4"

}

group = "com.gsm"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
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
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation ("org.springframework.boot:spring-boot-starter-mail")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")

	// Freemaker
	implementation("org.springframework.boot:spring-boot-starter-freemarker")

	// MapStruct
	// https://mvnrepository.com/artifact/org.mapstruct/mapstruct-processor
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")
	// https://mvnrepository.com/artifact/org.mapstruct/mapstruct-processor
	testAnnotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")
	// https://mvnrepository.com/artifact/org.mapstruct/mapstruct
	implementation("org.mapstruct:mapstruct:1.5.3.Final")
	// https://mvnrepository.com/artifact/org.projectlombok/lombok-mapstruct-binding
	implementation("org.projectlombok:lombok-mapstruct-binding:0.2.0")

	// QueryDSL Implementation
	implementation ("com.querydsl:querydsl-jpa:5.0.0:jakarta")
	annotationProcessor("com.querydsl:querydsl-apt:5.0.0:jakarta")
	annotationProcessor("jakarta.annotation:jakarta.annotation-api")
	annotationProcessor("jakarta.persistence:jakarta.persistence-api")

	// JPA Logging
	implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.9.0")

	// JWT Token
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	// AWS 연동 추가
	implementation ("org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE")

	// For OAuth2
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")

	// For Feign Client
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.1.0")

	// Spring AOP
	implementation("org.springframework.boot:spring-boot-starter-aop")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
