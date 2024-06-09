package ru.hse.hw4.job

import ru.hse.hw4.service.OrderService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ProcessOrderJob(
    private val orderService: OrderService
): Job {
    @Scheduled(fixedRate = 10000)
    override fun execute() {
        orderService.processOrders()
    }
}