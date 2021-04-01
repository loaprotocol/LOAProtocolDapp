package com.goodchoice.android.loalending.network.response

import androidx.annotation.Keep
import com.goodchoice.android.loalending.model.MemberInfo
import com.google.gson.annotations.SerializedName

@Keep
data class LoginResponse(
    @SerializedName("resultCode")
    val resultCode:String,
    @SerializedName("resultData")
    val resultData : MemberInfo,
    @SerializedName("resultMsg")
    val resultMsg:String
)
