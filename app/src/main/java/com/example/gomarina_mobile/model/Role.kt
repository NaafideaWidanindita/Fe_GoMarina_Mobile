package com.example.gomarina_mobile.model

import java.math.BigInteger

enum class RoleType{
    user,
    admin
}

data class Role(
    val id: BigInteger,
    val role: RoleType,
    val username: String,
    val password: String,
    val name: String,
    val telp: String
)
