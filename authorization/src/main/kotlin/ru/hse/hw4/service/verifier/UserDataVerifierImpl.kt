package ru.hse.hw4.service.verifier

import org.springframework.stereotype.Component
import ru.hse.hw4.exception.IncorrectUserDataException

@Component
class UserDataVerifierImpl: UserDataVerifier {
    companion object{
        const val MIN_PASSWORD_LENGTH = 8
    }

    private fun verifyEmail(email: String) {
        val emailRegex = """.+@.+\..+""".toRegex()
        if(!emailRegex.matches(email)) {
            throw IncorrectUserDataException("Incorrect email")
        }
    }

    private fun verifyPassword(password: String) {
        if (password.length < MIN_PASSWORD_LENGTH) {
            throw IncorrectUserDataException("Incorrect password")
        }
        val passwordRegexCapital  = """.*[A-Z].*""".toRegex()
        val passwordRegexSmall  = """.*[a-z].*""".toRegex()
        val passwordRegexDigit  = """.*[0-9].*""".toRegex()
        val passwordRegexSpecial = """.*[~!@#$%^&*()_+"№;:?=\[\]{}|/\\'<>`.,-].*""".toRegex()
        if(!(passwordRegexCapital.matches(password) &&
                    passwordRegexSmall.matches(password) &&
                    passwordRegexDigit.matches(password) &&
                    passwordRegexSpecial.matches(password))) {
            throw IncorrectUserDataException("Incorrect password")
        }
    }

    private fun verifyNickname(nickname: String){
        if (nickname.isEmpty()){
            throw IncorrectUserDataException("Incorrect nickname")
        }
    }

    override fun verify(nickname: String, password: String, email: String){
        verifyNickname(nickname)
        verifyPassword(password)
        verifyEmail(email)
    }
}