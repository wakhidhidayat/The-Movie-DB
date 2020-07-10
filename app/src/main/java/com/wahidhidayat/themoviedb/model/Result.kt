package com.wahidhidayat.themoviedb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
    @SerializedName("title")
    var title: String?,

    @SerializedName("poster_path")
    var poster_path: String?,

    @SerializedName("backdrop_path")
    var backdrop_path: String?,

    @SerializedName("overview")
    var overview: String?

) : Parcelable