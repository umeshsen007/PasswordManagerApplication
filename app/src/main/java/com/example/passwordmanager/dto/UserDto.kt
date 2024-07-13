package com.example.passwordmanager.dto

import androidx.lifecycle.MutableLiveData

class UserDetailDto(val accountName: String, val username: String, val password: String)

val userDetail = MutableLiveData<List<UserDetailDto>?>()

val list = ArrayList<UserDetailDto>()

fun getUserDetailDetailList(): ArrayList<UserDetailDto> {
    list.clear()
    list.add(UserDetailDto("","",""))
    return list
}