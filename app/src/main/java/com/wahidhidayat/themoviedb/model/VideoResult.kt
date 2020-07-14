package com.wahidhidayat.themoviedb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoResult(
    @SerializedName("id")
    var videoId: String?,

    @SerializedName("key")
    var key: String?,

    @SerializedName("name")
    var name: String?,

    @SerializedName("site")
    var site: String?,

    @SerializedName("type")
    var type: String?
) : Parcelable