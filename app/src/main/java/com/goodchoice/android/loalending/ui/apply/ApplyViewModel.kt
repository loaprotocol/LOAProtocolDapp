package com.goodchoice.android.loalending.ui.apply

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goodchoice.android.loalending.model.Apply
import com.goodchoice.android.loalending.model.LoanApply
import com.goodchoice.android.loalending.network.response.ApplyResponse
import com.goodchoice.android.loalending.network.service.NetworkService
import com.goodchoice.android.loalending.util.comma
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class ApplyViewModel(private val networkService: NetworkService) : ViewModel() {

    lateinit var loanApply: LoanApply
    lateinit var lastApply: Apply
    var priceComma = ""
    var submitSuccess = MutableLiveData(false)
    val applyCancelCheck = MutableLiveData(false)

    fun loanApplySubmit() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val applyResponse: ApplyResponse = networkService.requestApply(
                    addr1 = loanApply.addr1, addr2 = loanApply.addr2,
                    buildingCode = loanApply.buildingCode, buildingName = loanApply.buildingName,
                    price = loanApply.price,
                    comment = loanApply.comment
                )
//                Timber.e(applyResponse.toString())
                if (applyResponse.resultCode == "0000") {
                    lastApply = applyResponse.resultData
                    priceComma = comma(loanApply.price.toLong())
                    submitSuccess.postValue(true)
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun applyCancel(seq: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val applyCancelResponse = networkService.requestApplyCancel(
                    apply_seq = seq
                )
                if (applyCancelResponse.resultCode == "0000") {
                    applyCancelCheck.postValue(true)
                }

            } catch (e: Exception) {

            }
        }
    }


}