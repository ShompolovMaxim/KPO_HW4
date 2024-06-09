package ru.hse.hw4.mapper

import org.mapstruct.Mapper
import ru.hse.hw4.dto.AuthUser
import ru.hse.hw4.dto.User

interface UserMapper {
    fun authUser2orderUser(authUser: AuthUser): User
}