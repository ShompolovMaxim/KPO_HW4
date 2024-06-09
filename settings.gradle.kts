pluginManagement{
    plugins{
        val kotlinVersion: String by settings
        val springBootVersion: String by settings
        val dependencyManagementVersion: String by settings
        val lombokVersion: String by settings

        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version dependencyManagementVersion
        id("io.freefair.lombok") version lombokVersion
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "HW4"
include(":authorization")
include(":order")
