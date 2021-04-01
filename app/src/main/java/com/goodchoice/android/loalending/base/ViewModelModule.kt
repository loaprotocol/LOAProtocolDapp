package com.goodchoice.android.loalending.base

import com.goodchoice.android.loalending.ui.apply.ApplyViewModel
import com.goodchoice.android.loalending.ui.loan.LoanViewModel
import com.goodchoice.android.loalending.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModeModule = module {
    single { LoanViewModel(get()) }

    // 앱 전역에서 쓰므로 single로 선언
    single(createdAtStart = true) { LoginViewModel(get()) }
    single { ApplyViewModel(get()) }
}