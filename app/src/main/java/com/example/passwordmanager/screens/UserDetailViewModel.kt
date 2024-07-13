package com.example.passwordmanager.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.passwordmanager.dto.UserDetailDto

// ViewModel to manage the list of accounts
class UserDetailViewModel : ViewModel() {
    val userDetailList = MutableLiveData<List<UserDetailDto>>(null)

    private val accountsList = mutableListOf<UserDetailDto>()

    fun addAccount(userDetailDto: UserDetailDto) {
        accountsList.add(userDetailDto)
        userDetailList.value = accountsList.toList()  // Update LiveData with new list
    }

    fun deleteAccount(userDetailDto: UserDetailDto) {
        accountsList.remove(userDetailDto)
        userDetailList.value = accountsList.toList()  // Update LiveData with new list
    }
}
