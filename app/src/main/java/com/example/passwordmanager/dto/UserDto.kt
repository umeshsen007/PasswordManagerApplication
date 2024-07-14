package com.example.passwordmanager.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

// Data class to hold account information

@Entity(tableName = "user_detail")
data class UserDetailDto(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val accountName: String,
    val username: String,
    val password: String
)