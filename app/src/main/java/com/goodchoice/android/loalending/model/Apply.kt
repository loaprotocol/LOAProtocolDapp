package com.goodchoice.android.loalending.model

import com.google.gson.annotations.SerializedName

data class Apply(
    val seq: Int,
    var status: String,
    var statusName: String,
    val name: String,
    val phone: String,
    val addr1: String,
    val addr2: String,
    val memo: String,
    var maxLoan: String? = "0",
    @SerializedName("price")
    var hopeLoan: String,
    val comment: String

)
