package com.goodchoice.android.loalending.network.response

import com.goodchoice.android.loalending.model.Apply
import com.goodchoice.android.loalending.model.Loan
import com.google.gson.annotations.SerializedName

data class LoanListResponse(
    @SerializedName("resultCode")
    val resultCode: String,
    @SerializedName("resultData")
    val resultData: List<Loan>,
    @SerializedName("resultMsg")
    val resultMsg: String
)