package ru.hse.hw4.service.verifier

interface UserDataVerifier {
    fun verify(nickname: String, password: String, email: String)
}