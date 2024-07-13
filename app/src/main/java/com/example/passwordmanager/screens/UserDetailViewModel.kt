package com.example.passwordmanager.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.passwordmanager.dto.UserDetailDto

// ViewModel to manage the list of accounts
class UserDetailViewModel : ViewModel() {
    val accounts = MutableLiveData<List<UserDetailDto>>(emptyList())

    private val list = ArrayList<UserDetailDto>()

    fun addAccount(userDetailDto: UserDetailDto) {
        val updatedList = accounts.value.orEmpty().toMutableList().apply {
            add(userDetailDto)
        }
        accounts.postValue(updatedList)
    }
}
