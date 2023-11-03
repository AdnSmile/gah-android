package com.vvwxx.gahandroid.data.remote.response

import com.google.gson.annotations.SerializedName

data class WebResponse<T>(

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("data")
    val data: T
)
