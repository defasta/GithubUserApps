package com.example.githubuserapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var id: Int = 0,
    var username: String? = null,
    var name: String? = null,
    var followers: String? = null,
    var following: String? = null
) : Parcelable
