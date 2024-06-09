package ru.hse.hw4.exception

class UserIsNotAuthorisedException(override val message: String): RuntimeException(message)