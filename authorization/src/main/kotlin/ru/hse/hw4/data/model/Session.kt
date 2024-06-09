package ru.hse.hw4.data.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.sql.Timestamp

@Table("session")
data class Session(
    @Id
    val id: Long? = null,
    @Column("user_id")
    val userId: Long,
    val token: String,
    val expires: Timestamp,
)
