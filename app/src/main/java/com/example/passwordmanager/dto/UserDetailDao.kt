package com.example.passwordmanager.dto

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDetailDao {
    @Insert
    suspend fun insert(userDetail: UserDetailDto)

    @Delete
    suspend fun delete(userDetail: UserDetailDto)
    @Update
    suspend fun update(userDetail: UserDetailDto)

    @Query("SELECT * FROM user_detail")
     fun getAllUserDetails(): List<UserDetailDto?>?

}