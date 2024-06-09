package ru.hse.hw4.data.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.sql.Timestamp
import java.time.Instant

@Table("user")
data class User(
    @Id
    var id: Long? = null,
    val nickname: String,
    val email: String,
    val password: String,
    val created: Timestamp = Timestamp.from(Instant.now())
)
