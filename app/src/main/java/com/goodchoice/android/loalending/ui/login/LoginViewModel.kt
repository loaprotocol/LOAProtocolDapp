package com.goodchoice.android.loalending.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goodchoice.android.loalending.model.MemberInfo
import com.goodchoice.android.loalending.network.service.NetworkService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class LoginViewModel(private val networkService: NetworkService) : ViewModel() {

    // 로그인체크
    val isLogin = MutableLiveData(false)

    // loginCode
    val loginCode = MutableLiveData("")

    // pwchangeCode
    val pwChangeCode = MutableLiveData("")

    // logoutCode
    val logoutCode = MutableLiveData("")

    // secessionCode
    val secessionCode = MutableLiveData("")

    // 유저 정보
    var memberInfo: MemberInfo? = null

    // 로그인성공시 이동할 페이지
    var returnPage=MutableLiveData("")
    var tempReturnPage=""


    fun login(memEmail: String, memPw: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val loginResponse = networkService.requestLogin(
                    memEmail, memPw
                )
                memberInfo=loginResponse.resultData
                loginCode.postValue(loginResponse.resultCode)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun logout() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val logoutResponse = networkService.requestLogout()
                Timber.e(logoutResponse.toString())
                logoutCode.postValue(logoutResponse.resultCode)

            } catch (e: Exception) {

            }
        }
    }

    fun pwChange(pwOld: String, pwNew: String, pwRe: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val pwChangeResponse = networkService.requestPwChange(
                    pwOld = pwOld,
                    pwNew = pwNew,
                    pwRe = pwRe
                )
                pwChangeCode.postValue(pwChangeResponse.resultCode)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun secession() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val secessionResponse = networkService.requestSecession()
                secessionCode.postValue(secessionResponse.resultCode)

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

}