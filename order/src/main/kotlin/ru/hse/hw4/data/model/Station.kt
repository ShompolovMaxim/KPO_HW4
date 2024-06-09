package ru.hse.hw4.data.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("station")
data class Station(
    @Id
    val id: Long,
    val station: String,
)