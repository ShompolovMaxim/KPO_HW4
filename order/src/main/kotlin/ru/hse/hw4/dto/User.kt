package ru.hse.hw4.dto

import java.sql.Timestamp

data class User(
    var id: Long?,
    val nickname: String,
    val email: String,
    val password: String,
    val created: Timestamp
)