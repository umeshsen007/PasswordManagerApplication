package com.example.passwordmanager.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.security.Key

@Entity(tableName = "keys")
data class KeyEntity(
    @PrimaryKey val id: Int,
    val key: String
)