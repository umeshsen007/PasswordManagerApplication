package com.example.passwordmanager.dto

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.passwordmanager.dto.UserDetailDto

@Database(entities = [UserDetailDto::class, KeyEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    // define dao
    abstract fun userDetailDao(): UserDetailDao

    abstract fun keyDao(): KeyDao

}