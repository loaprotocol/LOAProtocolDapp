package com.goodchoice.android.loalending.network.response

import androidx.annotation.Keep
import com.goodchoice.android.loalending.model.Apply
import com.google.gson.annotations.SerializedName

@Keep
data class ApplyResponse(
    @SerializedName("resultCode")
    val resultCode: String,
    @SerializedName("resultData")
    val resultData: Apply,
    @SerializedName("resultMsg")
    val resultMsg: String
)