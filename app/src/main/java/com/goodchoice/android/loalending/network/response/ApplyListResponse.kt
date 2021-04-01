package com.goodchoice.android.loalending.network.response

import com.goodchoice.android.loalending.model.Apply
import com.goodchoice.android.loalending.model.MemberInfo
import com.google.gson.annotations.SerializedName

data class ApplyListResponse(
    @SerializedName("resultCode")
    val resultCode: String,
    @SerializedName("resultData")
    val resultData: List<Apply>,
    @SerializedName("resultMsg")
    val resultMsg: String
)