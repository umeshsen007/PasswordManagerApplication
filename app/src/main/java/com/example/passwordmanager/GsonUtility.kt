package com.example.passwordmanager

import com.google.gson.Gson

object GsonUtility {
    private val gson = Gson()

    fun <T> fromJson(json: String, classOfT: Class<T>): T {
        return gson.fromJson(json, classOfT)
    }
}