package ru.hse.hw4.controller

import ru.hse.hw4.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import ru.hse.hw4.api.OrderApi
import ru.hse.hw4.data.model.Order
import ru.hse.hw4.exception.IncorrectStationException
import ru.hse.hw4.exception.UserIsNotAuthorisedException

@RestController
@RequestMapping("/order")
class OrderController(
    private val orderService: OrderService
): OrderApi {
    @PostMapping
    override fun createOrder(
        @RequestParam(required = true) token: String,
        @RequestParam(required = true) fromStationName: String,
        @RequestParam(required = true) toStationName: String,
    ): ResponseEntity<String> {
        try {
            val addedOrderId: Long = orderService.addOrder(token, fromStationName, toStationName)
            return ResponseEntity("Order successfully made with order id: $addedOrderId", HttpStatus.OK)
        } catch (ex: IncorrectStationException) {
            return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
        } catch (ex: UserIsNotAuthorisedException) {
            return ResponseEntity(ex.message, HttpStatus.UNAUTHORIZED)
        }
    }

    @GetMapping
    override fun getOrder(@RequestParam(required = true) id: Long): Order {
        val order = orderService.getOrder(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "No order with such id")
        return order
    }
}