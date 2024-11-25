package com.example.gomarina_mobile.dummyData


val dummyUsers = listOf(
    User("user1", "password123"),
    User("user2", "password456"),
    User("user3", "password789")
)

data class User(
    val username: String,
    val password: String
)
