package ru.hse.hw4.data.repository

import ru.hse.hw4.data.model.Station
import org.springframework.data.repository.CrudRepository
import java.util.*

interface StationRepository: CrudRepository<Station, Long> {
    fun findByStation(station: String): Optional<Station>
}