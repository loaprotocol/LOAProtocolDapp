package com.goodchoice.android.loalending.ui.apply

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.base.BaseFragment
import com.goodchoice.android.loalending.databinding.FragmentApplyBinding
import com.goodchoice.android.loalending.model.LoanApply
import com.goodchoice.android.loalending.ui.WebViewActivity
import com.goodchoice.android.loalending.util.CustomToast
import com.goodchoice.android.loalending.util.comma
import com.goodchoice.android.loalending.util.constant.BaseUrl
import com.goodchoice.android.loalending.util.constant.Code
import com.goodchoice.android.loalending.util.hideKeyboard
import org.koin.android.ext.android.inject
import timber.log.Timber
import kotlin.math.pow

class ApplyFragment : BaseFragment<FragmentApplyBinding>(R.layout.fragment_apply) {

    private lateinit var buildingCode: String
    private lateinit var buildingName: String

    private val applyViewModel: ApplyViewModel by inject()

    // 액티비티를 띄운뒤 콜백을 받는 함수
    private val registerForActivityResult =
        registerForActivityResult(StartActivityForResult()) {
            when (it.resultCode) {
                Activity.RESULT_OK -> {
                    val addr = it.data!!.getStringExtra("addr")
                    buildingCode = it.data!!.getStringExtra("buildingCode")!!
                    buildingName = it.data!!.getStringExtra("buildingName")!!
                    binding.applyAddr.text = addr
                    binding.applyAddrDetail.requestFocus()
//                binding.applyAddrDetail.post {
//                    val imm : InputMethodManager =binding.root.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//                    imm.showSoftInput(binding.applyAddrDetail,0)
//                }
                }
                Activity.RESULT_CANCELED -> {

                }

            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // 주소검색 클릭 -> daum address
        binding.applyAddr.setOnClickListener {


            val intent = Intent(binding.root.context, WebViewActivity::class.java)
            intent.putExtra("title", Code.TITLE_DAUM_ADDRESS)
            intent.putExtra("url", BaseUrl.DAUM_ADDRESS)
            registerForActivityResult.launch(intent)
        }


        // 검색 선택버튼 기능
        for (i in 0 until binding.applyRg.childCount) {
            val amount: Int = 100000 * 10.0.pow(i).toInt()
            binding.applyRg.getChildAt(i).setOnClickListener {
                if (i == binding.applyRg.childCount - 1) {
                    binding.applyAmount.setText("0")
                } else {
                    binding.applyAmount.setText(
                        (binding.applyAmount.text.toString().replace(",", "")
                            .toInt() + amount).toString()
                    )
                }
            }
        }

        // 희망대출금액 comma 넣기
        var result = ""
        binding.applyAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!TextUtils.isEmpty(s.toString())) {

                    if (s.toString() != result) {
                        result =
                            if (s.toString().replace(",", "").length > 15) {
                                comma(s.toString().replace(",", "").dropLast(1).toLong())
                            } else {
                                comma(s.toString().replace(",", "").toLong())
                            }
                        binding.applyAmount.setText(result)
                        // 커서를 마지막 글자뒤로 이동
                        binding.applyAmount.setSelection(result.length)
                    }
                } else {
                    binding.applyAmount.setText("0")
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        // 부동산 가격 조회 클릭
        binding.applyPriceInquiry.setOnClickListener {
            if (binding.applyAddr.text.isNullOrEmpty()) {
                CustomToast(requireContext(), "주소를 입력해 주세요").warningToast().show()
                return@setOnClickListener
            }
            if (binding.applyAddrDetail.text.isNullOrEmpty()) {
                CustomToast(requireContext(), "상세 주소를 입력해 주세요").warningToast().show()
                return@setOnClickListener
            }
            binding.applyMaxPriceLinear.visibility = View.VISIBLE
        }

        // 대출 신청 버튼 클릭
        binding.applySubmit.setOnClickListener {
            // 주소부분이 빈칸일때
            if (binding.applyAddr.text.isNullOrEmpty() || binding.applyMaxPriceLinear.visibility == View.GONE) {
                CustomToast(requireContext(), "부동산 가격조회를 진행하세요.").warningToast().show()
                return@setOnClickListener;
            }
            if (binding.applyAddrDetail.text.isNullOrEmpty() || binding.applyMaxPriceLinear.visibility == View.GONE) {
                CustomToast(requireContext(), "부동산 가격조회를 진행하세요.").warningToast().show()
                return@setOnClickListener
            }
            if (binding.applyAmount.text.isNullOrEmpty()) {
                CustomToast(requireContext(), "희망 대출금액을 입력하세요.").warningToast().show()
                return@setOnClickListener
            }
            applyViewModel.loanApply = LoanApply(
                binding.applyAddr.text.toString(),
                binding.applyAddrDetail.text.toString(),
                buildingCode = buildingCode,
                buildingName = buildingName,
                comment = binding.applyComment.text.toString(),
                price = binding.applyAmount.text.toString().replace(",", "")
            )
            CustomToast(requireContext(), "신청되었습니다.").successToast().show()
            applyViewModel.loanApplySubmit()

        }

        // 다른데 클릭시 키보드 내리기
        binding.fragmentApply.setOnClickListener {
            hideKeyboard(requireActivity())
        }

        //submit success observer
        applyViewModel.submitSuccess.observe(viewLifecycleOwner, Observer {
            if (it) {
//                Timber.e("성공")
                // 대출 성공 페이지로 전환
                applyViewModel.submitSuccess.postValue(false)
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.header_close_main_fragment, ApplyCompleteFragment()).commit()
            }
        })

        // 개인정보 수집 이용동의서 밑줄
        val termText = "개인정보 수집 이용 동의서의 내용을 확인하였으며,\n" +
                "이에 동의합니다."
        val ssb = SpannableStringBuilder(termText)
        ssb.setSpan(UnderlineSpan(), 0, 14, 0)
        ssb.setSpan(StyleSpan(Typeface.BOLD), 0, 14, 0)
        ssb.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                Timber.e("asdfsdaf")
                val dialog = ApplyTermDialog()
                val fragmentTransaction = this@ApplyFragment.childFragmentManager.beginTransaction()
                dialog.show(fragmentTransaction, "")
            }

        }, 0, 14, 0)
        binding.applyTerm.text = ssb
        binding.applyTerm.movementMethod = LinkMovementMethod.getInstance()
    }

}