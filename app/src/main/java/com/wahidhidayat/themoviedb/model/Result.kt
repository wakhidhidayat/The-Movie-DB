package com.wahidhidayat.themoviedb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
    @SerializedName("id")
    var id: Int?,

    @SerializedName("title")
    var title: String?,

    @SerializedName("poster_path")
    var poster_path: String?,

    @SerializedName("backdrop_path")
    var backdrop_path: String?,

    @SerializedName("overview")
    var overview: String?,

    @SerializedName("release_date")
    var release_date: String?,

    @SerializedName("vote_average")
    var vote_average: Double?,

    @SerializedName("genre_ids")
    var genres: List<Int>?

) : Parcelable