package com.goodchoice.android.loalending.network.service

import com.goodchoice.android.loalending.network.response.*
import retrofit2.http.*

interface NetworkService {

    //로그인
    @POST("api/login")
    @FormUrlEncoded
    suspend fun requestLogin(
        @Field("mem_id") memEmail: String,
        @Field("mem_pw") memPw: String
    ): LoginResponse

    //로그아웃
    @POST("api/logout")
    suspend fun requestLogout(
    ): NoDataResponse

    //대출 신청
    @POST("api/apply_proc")
    @FormUrlEncoded
    suspend fun requestApply(
        @Field("mode") mode: String = "write",
        @Field("addr1") addr1: String,
        @Field("addr2") addr2: String,
        @Field("buildName") buildingName: String,
        @Field("buildCode") buildingCode: String,
        @Field("price") price: String,
        @Field("comment") comment: String
    ): ApplyResponse

    //대출 취소
    @POST("api/apply_proc")
    @FormUrlEncoded
    suspend fun requestApplyCancel(
        @Field("mode") mode: String = "cancel",
        @Field("seq") apply_seq: String
    ): NoDataResponse

    // 대출 현황 리스트 가져오기
    @POST("api/loan_list")
    suspend fun requestLoanList(): LoanListResponse

    // 대출 신청 리스트 가져오기
    @POST("api/apply_list")
    suspend fun requestApplyList(): ApplyListResponse

    // 비밀번효 변경
    @POST("api/member_proc")
    @FormUrlEncoded
    suspend fun requestPwChange(
        @Field("mode") mode: String = "pwchange",
        @Field("pw_old") pwOld: String,
        @Field("pw_new") pwNew: String,
        @Field("pw_re") pwRe: String
    ): NoDataResponse

    // 회원 탈퇴
    @POST("api/member_proc")
    @FormUrlEncoded
    suspend fun requestSecession(
        @Field("mode") mode: String = "membersecession"
    ): NoDataResponse
}