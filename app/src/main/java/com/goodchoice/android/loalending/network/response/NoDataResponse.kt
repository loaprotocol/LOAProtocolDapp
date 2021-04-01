package com.goodchoice.android.loalending.network.response

import com.goodchoice.android.loalending.model.Apply
import com.google.gson.annotations.SerializedName

data class NoDataResponse(
    @SerializedName("resultCode")
    val resultCode: String,
    @SerializedName("resultMsg")
    val resultMsg: String
)