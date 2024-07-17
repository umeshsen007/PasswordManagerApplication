package com.example.passwordmanager.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.MainActivity
import com.example.passwordmanager.dto.EncryptionUtil
import com.example.passwordmanager.dto.KeyEntity
import com.example.passwordmanager.dto.UserDetailDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.Key

@RequiresApi(Build.VERSION_CODES.O)
class UserDetailViewModel : ViewModel() {
    private val userDetailDao = MainActivity.database.userDetailDao()
    private val keyDao = MainActivity.database.keyDao()

    val userDetailList = MutableLiveData<List<UserDetailDto?>?>(null)
    private lateinit var secretKey: String
    private lateinit var decodedSecretKey: Key

    init {
        viewModelScope.launch {
            secretKey = getKey() ?: generateAndStoreKey()
            decodedSecretKey = EncryptionUtil.decodeKey(secretKey)
            getAllUserDetails()
        }
    }

    fun addAccount(userDetailDto: UserDetailDto) {
        viewModelScope.launch(Dispatchers.IO) {
            val encryptedPassword = EncryptionUtil.encrypt(userDetailDto.password, decodedSecretKey)
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
            val encryptedPassword = EncryptionUtil.encrypt(userDetailDto.password, decodedSecretKey)
            userDetailDao.update(userDetailDto.copy(password = encryptedPassword))
            getAllUserDetails()
        }
    }

    private fun getAllUserDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            val userDetails = userDetailDao.getAllUserDetails()
            val decryptedUserDetails = userDetails?.map { userDetail ->
                userDetail?.let {
                    val decryptedPassword = EncryptionUtil.decrypt(it.password, decodedSecretKey)
                    it.copy(password = decryptedPassword)
                }
            }
            userDetailList.postValue(decryptedUserDetails)
        }
    }

    private suspend fun generateAndStoreKey(): String {
        val newKey = EncryptionUtil.generateKey()
        val stringKey = EncryptionUtil.encodeKey(newKey)
        withContext(Dispatchers.IO) {
            keyDao.insertKey(KeyEntity(id = 1, key = stringKey))
        }
        return stringKey
    }

    private suspend fun getKey(): String? {
        return withContext(Dispatchers.IO) {
            keyDao.getKey(1)?.key
        }
    }
}