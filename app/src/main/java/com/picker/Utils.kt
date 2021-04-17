package com.picker

import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

object Utils {


    fun View.showSnackBar(
        message: String,
        duration: Int = Snackbar.LENGTH_LONG,
        actionTitle: String? = null,
        action: View.OnClickListener? = null
    ) = Snackbar.make(this, message, duration).setAction(actionTitle, action).show()


    fun getCurrentUTCDateTime() = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US)
        .apply { timeZone = TimeZone.getTimeZone("UTC") }.format(Date()) + "Z"


}