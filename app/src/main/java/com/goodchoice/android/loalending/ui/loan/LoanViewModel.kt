package com.goodchoice.android.loalending.ui.loan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goodchoice.android.loalending.model.Apply
import com.goodchoice.android.loalending.model.Loan
import com.goodchoice.android.loalending.network.service.NetworkService
import com.goodchoice.android.loalending.util.comma
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import java.lang.Exception

class LoanViewModel(private val networkService: NetworkService) : ViewModel() {

    // view 에서 데이터를 변경할 필요없을경우 MutableLiveData, LiveData 분리해서사용
//    private var _applyLiveData=MutableLiveData<ArrayList<Apply>>()
//    var applyLiveData :LiveData<ArrayList<Apply>> = _applyLiveData

    var loanList = ArrayList<Loan>()
    val loanListChange = MutableLiveData(false)
    var applyList = ArrayList<Apply>()
    val applyListChange = MutableLiveData(false)
//    lateinit var selectedLoan:MutableLiveData<Loan>

    // loanList 가져오기
    fun getLoanList() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val loanListResponse = networkService.requestLoanList()
                if (loanListResponse.resultCode == "0000") {
                    loanList = ArrayList(loanListResponse.resultData)
                    loanListChange.postValue(true)
                }
            } catch (e: Exception) {
                Timber.e(e.toString())
            }

        }
    }


    // applyList 가져오기
    fun getApplyList() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val applyListResponse = networkService.requestApplyList()
                if (applyListResponse.resultCode == "0000") {
                    applyList = ArrayList(applyListResponse.resultData)
                    applyListChange.postValue(true)
                }

            } catch (e: Exception) {
                Timber.e(e.toString())
            }
        }
    }


}