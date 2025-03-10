package com.example.passwordmanager.dto

import android.os.Build
import androidx.annotation.RequiresApi
import java.security.Key
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

object EncryptionUtil {

    private const val ALGORITHM = "AES"
    private const val TRANSFORMATION = "AES"

    fun generateKey(): Key {
        val keyGen = KeyGenerator.getInstance(ALGORITHM)
        keyGen.init(256) // For AES-256
        return keyGen.generateKey()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun encrypt(data: String, secretKey: Key): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val encryptedBytes = cipher.doFinal(data.toByteArray())
        return Base64.getEncoder().encodeToString(encryptedBytes)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun decrypt(encryptedData: String, secretKey: Key): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        val decodedBytes = Base64.getDecoder().decode(encryptedData)
        val decryptedBytes = cipher.doFinal(decodedBytes)
        return String(decryptedBytes)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun encodeKey(key: Key): String {
        return Base64.getEncoder().encodeToString(key.encoded)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun decodeKey(encodedKey: String): SecretKey {
        val decodedKey = Base64.getDecoder().decode(encodedKey)
        return SecretKeySpec(decodedKey, 0, decodedKey.size, "AES")
    }

}
