package ru.hse.hw4.service

import ru.hse.hw4.data.model.Order

interface OrderService {
    fun addOrder(token: String, fromStationName: String, toStationName: String): Long

    fun getOrder(id: Long): Order?

    fun processOrders()
}