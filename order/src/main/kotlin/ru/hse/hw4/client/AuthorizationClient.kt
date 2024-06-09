package ru.hse.hw4.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.hse.hw4.dto.AuthUser

@FeignClient("authorizationClient")
interface AuthorizationClient {
    @GetMapping
    fun getIdByTokenIfAuthorized(@RequestParam(required = true) token: String): ResponseEntity<AuthUser?>
}