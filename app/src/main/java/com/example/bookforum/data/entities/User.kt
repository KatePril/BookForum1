package com.example.bookforum.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bookforum.ui.databaseUi.userUI.states.UserDetails

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val password: String,
    val email: String,
    val salt: String
)

fun User.toDetails(): UserDetails = UserDetails(
    id = id,
    username = username,
    password = password,
    email = email,
    salt = salt
)
