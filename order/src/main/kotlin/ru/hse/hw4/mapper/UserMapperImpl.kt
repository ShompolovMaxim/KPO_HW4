package ru.hse.hw4.mapper

import org.springframework.stereotype.Component
import ru.hse.hw4.dto.AuthUser
import ru.hse.hw4.dto.User

@Component
class UserMapperImpl: UserMapper {
    override fun authUser2orderUser(authUser: AuthUser): User {
        return User(
            id = authUser.id,
            email = authUser.email,
            nickname = authUser.nickname,
            password = authUser.password,
            created = authUser.created,
        )
    }
}