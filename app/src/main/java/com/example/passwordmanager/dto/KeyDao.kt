package com.example.passwordmanager.dto

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface KeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKey(keyEntity: KeyEntity)

    @Query("SELECT * FROM keys WHERE id = :id")
    suspend fun getKey(id: Int): KeyEntity?
}