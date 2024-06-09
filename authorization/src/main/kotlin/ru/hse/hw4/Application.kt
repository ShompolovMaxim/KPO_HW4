package ru.hse.hw4

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableScheduling

@EnableFeignClients
@EnableScheduling
@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}