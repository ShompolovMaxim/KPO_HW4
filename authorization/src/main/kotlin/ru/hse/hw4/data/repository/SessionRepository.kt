package ru.hse.hw4.data.repository

import ru.hse.hw4.data.model.Session
import org.springframework.data.repository.CrudRepository
import java.util.*

interface SessionRepository: CrudRepository<Session, Long> {
    fun findByToken(token: String): Optional<Session>
}