package com.example.passwordmanager.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.passwordmanager.dto.UserDetailDto

// ViewModel to manage the list of accounts
class UserDetailViewModel : ViewModel() {
    val accounts = MutableLiveData<List<UserDetailDto>>(emptyList())

    private val accountList = mutableListOf<UserDetailDto>()

    fun addAccount(userDetailDto: UserDetailDto) {
        accountList.add(userDetailDto)
        accounts.value = accountList.toList()  // Update LiveData with new list
    }

    fun deleteAccount(userDetailDto: UserDetailDto) {
        accountList.remove(userDetailDto)
        accounts.value = accountList.toList()  // Update LiveData with new list
    }
}
