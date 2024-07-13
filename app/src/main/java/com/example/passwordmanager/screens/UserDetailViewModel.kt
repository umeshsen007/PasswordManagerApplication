package com.example.passwordmanager.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.passwordmanager.dto.UserDetailDto

class UserDetailViewModel : ViewModel() {
    val userDetail = MutableLiveData<List<UserDetailDto>?>()

    private val list = ArrayList<UserDetailDto>()

    fun getUserDetailDetailList(): ArrayList<UserDetailDto> {
        list.clear()
        list.add(UserDetailDto("","",""))
        return list
    }
}
