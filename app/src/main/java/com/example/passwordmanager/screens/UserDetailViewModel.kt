package com.example.passwordmanager.screens

// ViewModel to manage the list of accounts
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.MainActivity
import com.example.passwordmanager.dto.EncryptionUtil
import com.example.passwordmanager.dto.UserDetailDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.annotations.NotNull

class UserDetailViewModel : ViewModel() {
    private val userDetailDao = MainActivity.database.userDetailDao()
    private val accountsList = mutableListOf<UserDetailDto>()
    val userDetailList = MutableLiveData<List<UserDetailDto?>?>(null)

    init {
        getAllUserDetails()
    }

    fun addAccount(userDetailDto: UserDetailDto) {
        viewModelScope.launch(Dispatchers.IO) {
            val encryptedPassword = EncryptionUtil.encrypt(userDetailDto.password)
            userDetailDao.insert(userDetailDto.copy(password = encryptedPassword.toString(Charsets.ISO_8859_1)))
            getAllUserDetails()
        }
    }

    fun deleteAccount(userDetailDto: UserDetailDto) {
        viewModelScope.launch(Dispatchers.IO) {
            userDetailDao.delete(userDetailDto)
            getAllUserDetails()
        }
    }
    fun updateAccount(userDetailDto: UserDetailDto) {
        viewModelScope.launch(Dispatchers.IO) {
            val encryptedPassword = EncryptionUtil.encrypt(userDetailDto.password)
            userDetailDao.update(userDetailDto.copy(password = encryptedPassword.toString(Charsets.ISO_8859_1)))
            getAllUserDetails()
        }
    }

    private fun getAllUserDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            val userDetails = userDetailDao.getAllUserDetails()
            userDetailList.postValue(userDetails)
        }
    }
}
