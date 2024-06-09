package ru.hse.hw4.data.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.sql.Timestamp

@Table("order")
data class Order(
    @Id
    val id: Long? = null,
    @Column("user_id")
    val userId: Long,
    @Column("from_station_id")
    val fromStationId: Long,
    @Column("to_station_id")
    val toStationId: Long,
    var status: Long = 1,
    val created: Timestamp? = null,
)