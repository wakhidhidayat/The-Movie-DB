package com.wahidhidayat.themoviedb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Videos(
    @SerializedName("id")
    var movieId: Int?,

    @SerializedName("results")
    var results: List<VideoResult>?
) : Parcelable