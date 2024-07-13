package com.example.passwordmanager

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class NavArgWrapperDto(val navArgVo: String) : Parcelable

@Parcelize
class NavArgWrapperDto2(val navArgVo: String?, val from: String? = null) : Parcelable