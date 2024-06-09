package ru.hse.hw4.service

import io.jsonwebtoken.Jwts
import ru.hse.hw4.exception.IncorrectUserDataException
import org.springframework.stereotype.Service
import ru.hse.hw4.data.model.Session
import ru.hse.hw4.data.model.User
import ru.hse.hw4.data.repository.SessionRepository
import ru.hse.hw4.data.repository.UserRepository
import ru.hse.hw4.service.verifier.UserDataVerifier
import java.sql.Timestamp
import java.time.Instant
import kotlin.jvm.optionals.getOrNull

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository,
    private val userDataVerifier: UserDataVerifier,
): UserService {
    companion object{
        const val SECONDS_BEFORE_EXPIRE: Long = 7 * 24 * 3600
    }

    override fun signUp(nickname: String, password: String, email: String) {
        userDataVerifier.verify(nickname, password, email)
        try {
            userRepository.save(User(nickname = nickname, password = password, email = email))
        } catch (ex: Exception) {
            throw IncorrectUserDataException("User with such nickname or email already exists!")
        }
    }

    private fun getJvt(user: User): String {
        return Jwts.builder()
            .claims()
            .subject(user.email)
            .issuedAt(Timestamp.from(Instant.now()))
            .expiration(Timestamp.from(Instant.now().plusSeconds(SECONDS_BEFORE_EXPIRE)))
            .and()
            .compact()
    }

    override fun signIn(email: String, password: String): String? {
        val users = userRepository.findAll()
        val user: User? = users.find { it.email == email && it.password == password }
        if (user != null) {
            val session = sessionRepository.save(
                Session(
                    userId = user.id!!,
                    token = getJvt(user),
                    expires = Timestamp.from(Instant.now().plusSeconds(SECONDS_BEFORE_EXPIRE))
                )
            )
            return session.token
        } else {
            return null
        }
    }

    override fun getIdByTokenIfAuthorized(token: String): User? {
        val session: Session = sessionRepository.findByToken(token).getOrNull() ?: return null
        if (session.expires < Timestamp.from(Instant.now())) {
            return null
        }
        return userRepository.findById(session.userId).getOrNull()
    }
}