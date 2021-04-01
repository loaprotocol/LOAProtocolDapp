package com.goodchoice.android.loalending.util.constant

object Code {
    // 신청완료
    const val APPLY_STATUS_COMPLETE = "0100"

    // 검토중
    const val APPLY_STATUS_ING = "0200"

    // 대출진행완료
    const val APPLY_STATUS_OK = "0300"

    // 대출불가
    const val APPLY_STATUS_NO = "0400"

    // 신청취소
    const val APPLY_STATUS_CANCEL = "0900"


    // 상환중
    const val LOAN_STATUS_ING = "0100"

    // 상환완료
    const val LOAN_STATUS_END = "0200"

    // 미납
    const val LOAN_STATUS_UNPAID = "0300"


    // 페이지
    const val TITLE_LOAN="200"                  // 대출현황
    const val TITLE_LOAN_DETAIL="210"
    const val TITLE_LOAN_DETAIL_REPAY="211"
    const val TITLE_APPLY = "002"               // 대출 신청
    const val TITLE_MY_ASSET="003"
    const val TITLE_COMPANY_INFO = "042"        // 회사 소개
    const val TITLE_TERM = "043"                // 이용 약관
    const val TITLE_PRIVACY_POLICY = "044"      // 개인정보 처리방침
    const val TITLE_LOGIN = "200"               // 로그인
    const val TITLE_SIGN_UP = "201"             // 회원 가입
    const val TITLE_MYPAGE = "040"              // 마이페이지
    const val TITLE_DAUM_ADDRESS = "011"        // 주소 검색
    const val TITLE_DEPOSIT_KRW="111"
    const val TITLE_DEPOSIT_LOA="112"
    const val TITLE_WITHDRAW_KRW="121"
    const val TITLE_WITHDRAW_LOA="122"
    const val TITLE_EXCHANGE="131"
    const val TITLE_MY_ASSET_REPAY="132"

    //로그인
    const val LOGIN_SUCCESS="0000"
    const val LOGIN_FAIL="9000"
    const val LOGIN_NOT_REQUIRE_VALUE="0001"


    //로그아웃
    const val LOGOUT_SUCCESS = "0000"

    // 비밀번호 변경
    const val PWD_CHANGE_SUCCESS = "0000"
    const val PWD_CHANGE_LOGOUT = "0001"
    const val PWD_CHANGE_TYPE = "0002"
    const val PWD_CHANGE_RE = "0003"
    const val PWD_CHANGE_OLD_X = "1000"

    // 회원 탈퇴
    const val SECESSION_SUCCESS = "0000"
    const val SECESSION_FAIL = "0100"

    const val MEMBER_PROC_ERROR = "9999"

}