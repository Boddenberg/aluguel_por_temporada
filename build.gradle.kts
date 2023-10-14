import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.4"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
	id("org.jetbrains.kotlin.plugin.allopen") version "1.8.22"
	id("org.jetbrains.kotlin.plugin.jpa") version "1.8.22"
}

buildscript {
	dependencies {
		classpath ("org.jetbrains.kotlin:kotlin-noarg:1.8.22")
	}
}

group = "JuninWins"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
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
	//dependencias web
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation ("org.springframework.boot:spring-boot-starter-hateoas")
	// dependencias validadoras
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation
	implementation("org.springframework.boot:spring-boot-starter-validation")
	//dependencias de banco de dados
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-docker-compose")
	implementation ("com.mysql:mysql-connector-j:8.1.0")
	//dependencias de comunicação
	implementation ("com.squareup.retrofit2:retrofit:2.9.0")
	implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
	implementation ("com.squareup.retrofit2:adapter-rxjava2:2.9.0")
	//dependencias deserializadoras
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.modelmapper:modelmapper:2.4.4")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	compileOnly("org.projectlombok:lombok")
	implementation("org.hibernate:hibernate-validator:8.0.0.Final")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
