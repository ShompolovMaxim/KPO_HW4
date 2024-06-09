package ru.hse.hw4.data.repository

import ru.hse.hw4.data.model.Order
import org.springframework.data.repository.CrudRepository

interface OrderRepository: CrudRepository<Order, Long> {
}