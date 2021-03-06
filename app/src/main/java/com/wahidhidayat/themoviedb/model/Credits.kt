package com.wahidhidayat.themoviedb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Credits(
    @SerializedName("cast")
    var cast: List<Cast>?

) : Parcelable