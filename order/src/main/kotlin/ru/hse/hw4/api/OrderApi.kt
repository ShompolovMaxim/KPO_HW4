package ru.hse.hw4.api

import org.springframework.http.ResponseEntity
import ru.hse.hw4.data.model.Order

interface OrderApi {
    fun createOrder(token: String, fromStationName: String, toStationName: String): ResponseEntity<String>

    fun getOrder(id: Long): Order
}