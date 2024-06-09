package ru.hse.hw4.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import ru.hse.hw4.api.UserApi
import ru.hse.hw4.data.model.User
import ru.hse.hw4.exception.IncorrectUserDataException
import ru.hse.hw4.service.UserService

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
): UserApi {
    @PostMapping("/sign-up")
    override fun signUp(
        @RequestParam(required = true) nickname: String,
        @RequestParam(required = true) password: String,
        @RequestParam(required = true) email: String
    ): ResponseEntity<String> {
        try{
            userService.signUp(nickname, password, email)
        } catch (ex: IncorrectUserDataException) {
            return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
        }
        return ResponseEntity("Successful sign up!!!", HttpStatus.OK)
    }

    @PostMapping("/sign-in")
    override fun signIn(
        @RequestParam(required = true) email: String,
        @RequestParam(required = true) password: String
    ): ResponseEntity<String>  {
        val token = userService.signIn(email, password)
        return if (token != null) {
            ResponseEntity("Successful sign in with token: $token", HttpStatus.OK)
        } else {
            ResponseEntity("Incorrect email or password!", HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping
    override fun getIdByTokenIfAuthorized(@RequestParam(required = true) token: String): ResponseEntity<User?> {
        val user = userService.getIdByTokenIfAuthorized(token)
        return if (user == null) {
            ResponseEntity(null, HttpStatus.UNAUTHORIZED)
        } else {
            ResponseEntity(userService.getIdByTokenIfAuthorized(token), HttpStatus.OK)
        }
    }
}