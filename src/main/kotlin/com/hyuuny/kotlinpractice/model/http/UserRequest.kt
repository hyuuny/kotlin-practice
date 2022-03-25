package com.hyuuny.kotlinpractice.model.http

data class UserRequest(
    var name: String? = null,
    val age: Int? = null,
    val email: String? = null,
    val address: String? = null
)