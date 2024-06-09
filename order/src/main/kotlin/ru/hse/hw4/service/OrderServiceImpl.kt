package ru.hse.hw4.service

import feign.FeignException
import org.springframework.stereotype.Service
import ru.hse.hw4.client.AuthorizationClient
import ru.hse.hw4.data.model.Order
import ru.hse.hw4.data.model.Station
import ru.hse.hw4.data.repository.OrderRepository
import ru.hse.hw4.data.repository.StationRepository
import ru.hse.hw4.dto.AuthUser
import ru.hse.hw4.dto.User
import ru.hse.hw4.exception.IncorrectStationException
import ru.hse.hw4.exception.UserIsNotAuthorisedException
import ru.hse.hw4.mapper.UserMapper
import java.sql.Timestamp
import java.time.Instant
import kotlin.jvm.optionals.getOrNull

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val stationRepository: StationRepository,
    private val authorizationClient: AuthorizationClient,
    private val userMapper: UserMapper
): OrderService {
    override fun addOrder(token: String, fromStationName: String, toStationName: String): Long {
        val authUser: AuthUser
        try {
            authUser = authorizationClient.getIdByTokenIfAuthorized(token).body!!
        } catch (ex: FeignException) {
            throw UserIsNotAuthorisedException("User is not authorized")
        }
        val user: User = userMapper.authUser2orderUser(authUser)
        val fromStation: Station = stationRepository.findByStation(fromStationName).getOrNull() ?:
            throw IncorrectStationException("From Station with such name does not exist")
        val toStation: Station = stationRepository.findByStation(toStationName).getOrNull() ?:
            throw IncorrectStationException("To Station with such name does not exist")
        val newOrder = orderRepository.save(
            Order(
                userId = user.id!!,
                toStationId = toStation.id,
                fromStationId = fromStation.id,
                created = Timestamp.from(Instant.now()),
            )
        )
        return newOrder.id!!
    }

    override fun getOrder(id: Long): Order? {
        return orderRepository.findById(id).getOrNull()
    }

    override fun processOrders() {
        val newOrders = orderRepository.findAll().filter { it.status == 1L }
        for(order in newOrders) {
            order.status = (2L..3L).random()
            orderRepository.save(order)
        }
    }
}