package com.goodchoice.android.loalending.model

data class Loan(
    val seq: Int,
    val apply_seq:Int,
    val status: String,
    var statusName: String,
    val repaymentDay: String,
    val repaymentKind: String,
    val repaymentName: String,
    val collateralAddr1: String,
    val collateralAddr2: String,
    val residenceAddr1: String,
    val residenceAddr2: String,
    var loanAmount: String,
    val interestRate: Double,
    val loanTermSdate: String,
    var loanTermEdate: String,
    var loanTermMonth:String
)
