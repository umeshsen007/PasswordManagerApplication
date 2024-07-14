package com.example.passwordmanager.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.MainActivity
import com.example.passwordmanager.dto.EncryptionUtil
import com.example.passwordmanager.dto.UserDetailDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class UserDetailViewModel : ViewModel() {
    private val userDetailDao = MainActivity.database.userDetailDao()
    val userDetailList = MutableLiveData<List<UserDetailDto?>?>(null)
    val secretKey = EncryptionUtil.generateKey()

    init {
        getAllUserDetails()
    }

    fun addAccount(userDetailDto: UserDetailDto) {
        viewModelScope.launch(Dispatchers.IO) {
            val encryptedPassword = EncryptionUtil.encrypt(userDetailDto.password, secretKey)
            userDetailDao.insert(userDetailDto.copy(password = encryptedPassword))
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
            val encryptedPassword = EncryptionUtil.encrypt(userDetailDto.password, secretKey)
            userDetailDao.update(userDetailDto.copy(password = encryptedPassword))
            getAllUserDetails()
        }
    }

    private fun getAllUserDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            val userDetails = userDetailDao.getAllUserDetails()
            val decryptedUserDetails = userDetails?.map { userDetail ->
                userDetail?.let {
                    val decryptedPassword = EncryptionUtil.decrypt(it.password, secretKey)
                    it.copy(password = decryptedPassword)
                }
            }
            userDetailList.postValue(decryptedUserDetails)
        }
    }
}