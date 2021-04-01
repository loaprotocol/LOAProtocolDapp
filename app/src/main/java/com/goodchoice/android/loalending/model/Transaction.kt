package com.goodchoice.android.loalending.model

data class Transaction(
    val seq: String,
    val time: String,
    val moneyType: String,
    val transactionType: String,
    val address: String,
    val addressType: String,
    val addressName: String,
    val changeAmount: String,
    val totalAmount: String,
    val etc: String
)