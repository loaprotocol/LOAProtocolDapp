package com.goodchoice.android.loalending.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.webkit.*
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.base.BaseActivity
import com.goodchoice.android.loalending.databinding.ActivityWebviewBinding
import com.goodchoice.android.loalending.util.CustomToast
import com.goodchoice.android.loalending.util.constant.Code

class WebViewActivity : BaseActivity<ActivityWebviewBinding>(R.layout.activity_webview) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webViewSetting()
//        webViewFunction()

        binding.webViewHeader.headerBackTitle.setOnClickListener {
            finish()
        }
        binding.webViewHeader.headerBackIcon.setOnClickListener {
            finish()
        }
    }


    @SuppressLint("setJavaScriptEnabled")
    fun webViewSetting() {
        // 디버깅 막기
        WebView.setWebContentsDebuggingEnabled(true)
        binding.webView.clearCache(true)
        binding.webView.clearHistory()

        val settings = binding.webView.settings

        // 자바스크립트 사용여부
        settings.javaScriptEnabled = true
        // 멀티윈도우 사용 여부
        settings.setSupportMultipleWindows(true)
        // 혼합컨텐츠 허용(https,http), 본인인증 받을때 필요
        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        // javascript의 window.open 허용
        settings.javaScriptCanOpenWindowsAutomatically = true
        // JavaScript이벤트에 대응할 함수를 정의 한 클래스를 붙여줌
        binding.webView.addJavascriptInterface(WebViewBridge(), "android")

        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        cookieManager.setAcceptThirdPartyCookies(binding.webView, true)  //쿠키 허용


        val intent = intent
        when (intent.getStringExtra("title")) {
            Code.TITLE_SIGN_UP -> {
                binding.webViewHeader.headerBackTitle.text = "회원가입"
            }
            Code.TITLE_DAUM_ADDRESS -> {
                binding.webViewHeader.headerBackTitle.text = "주소검색"
            }
        }
        // 가져온 url 연결
        binding.webView.loadUrl(intent.getStringExtra("url"))
    }

    private inner class WebViewBridge() {
        @JavascriptInterface
        fun setAddress(addr: String, buildingCode: String, buildingName: String) {
            val handler = Handler()
            handler.post {
                val intent = Intent()
                intent.putExtra("addr", addr)
                intent.putExtra("buildingCode", buildingCode)
                intent.putExtra("buildingName", buildingName)
                this@WebViewActivity.setResult(RESULT_OK, intent)
                this@WebViewActivity.finish()
            }
        }

        @JavascriptInterface
        fun registerSubmit(status:Int){
            // 성공
            if(status==0){
                CustomToast(applicationContext,"정상적으로 회원가입이 완료 되었습니다.").successToast().show()
                finish()
            }
            else{

            }
        }

    }

}