package ru.hse.hw4.data.repository

import ru.hse.hw4.data.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long> {
}