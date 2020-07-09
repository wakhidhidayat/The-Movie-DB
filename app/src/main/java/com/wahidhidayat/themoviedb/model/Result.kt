package com.wahidhidayat.themoviedb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
    @SerializedName("title")
    var title: String?,

    @SerializedName("poster_path")
    var poster_path: String?

) : Parcelable