package com.goodchoice.android.loalending.util

import android.app.Activity
import android.content.Context
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.goodchoice.android.loalending.App
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun comma(number: Long): String {
    val formatter = DecimalFormat("###,###")
    return formatter.format(number)
}

fun phoneInsertHyphen(phone: String): String {
    return phone.substring(0, 3) + "-" + phone.substring(3, 7) + "-" + phone.substring(7)
}

fun Int.dpToPx(): Int {
    val metrics = App.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), metrics)
        .toInt()
}

fun hideKeyboard(activity: Activity) {
    val inputManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(
        activity.currentFocus?.windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
    )
}

fun monthCal(sDate: String, eDate: String): Int {
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val startDate = format.parse(sDate)!!
    val endDate = format.parse(eDate)!!

    return ((endDate.time - startDate.time) / (60 * 60 * 24 * 1000 * 30L)).toInt()
}
