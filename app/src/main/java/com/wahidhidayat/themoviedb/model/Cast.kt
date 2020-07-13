package com.wahidhidayat.themoviedb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cast(
    @SerializedName("name")
    var name: String?,

    @SerializedName("profile_path")
    var profile_path: String?,

    @SerializedName("character")
    var character: String?

) : Parcelable