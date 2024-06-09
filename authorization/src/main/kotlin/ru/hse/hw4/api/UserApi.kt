package ru.hse.hw4.api

import org.springframework.http.ResponseEntity
import ru.hse.hw4.data.model.User

interface UserApi {
    fun signUp(nickname: String, password: String, email: String): ResponseEntity<String>

    fun signIn(email: String, password: String): ResponseEntity<String>

    fun getIdByTokenIfAuthorized(token: String): ResponseEntity<User?>
}