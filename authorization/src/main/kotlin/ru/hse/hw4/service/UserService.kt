package ru.hse.hw4.service

import ru.hse.hw4.data.model.User

interface UserService {
    fun signUp(nickname: String, password: String, email: String)

    fun signIn(email: String, password: String): String?

    fun getIdByTokenIfAuthorized(token: String): User?
}