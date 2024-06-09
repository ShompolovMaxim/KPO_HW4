import org.springframework.boot.gradle.plugin.SpringBootPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("io.spring.dependency-management")
    id("org.springframework.boot") apply false
    id("io.freefair.lombok") apply false
}

allprojects {
    repositories {
        mavenCentral()
        mavenLocal()
    }
}

subprojects{
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "io.freefair.lombok")

    dependencyManagement{
        imports {
            val springCloudVersion: String by project

            mavenBom(SpringBootPlugin.BOM_COORDINATES)
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
        }

        dependencies {
            val mapstructVersion: String by project
            val springDocVersion: String by project
            dependency("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springDocVersion")
            dependency("org.mapstruct:mapstruct:$mapstructVersion")
            dependency("org.mapstruct:mapstruct-processor:$mapstructVersion")
            dependency("io.jsonwebtoken:jjwt-api:0.12.3")
            dependency("io.jsonwebtoken:jjwt-impl:0.12.3")
            dependency("io.jsonwebtoken:jjwt-jackson:0.12.3")
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
    tasks.withType<Jar>() {
        enabled = true
    }
}
