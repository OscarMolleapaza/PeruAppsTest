package com.omolleapaza.peruapps.peruappstest.ui.databinding

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.omolleapaza.peruapps.peruappstest.R
import com.omolleapaza.peruapps.peruappstest.enums.ConnectionStatusEnum
import com.omolleapaza.peruapps.peruappstest.ui.custom_view.ConnectionStatusCustomView
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


@BindingAdapter("connectionStatusCustomSetup")
fun connectionStatusCustomSetup(view: ConnectionStatusCustomView, connectionStatusEnum: ConnectionStatusEnum?){
    connectionStatusEnum?.let {
        view.setConnectionStatusEnum(connectionStatusEnum)

    }
}

@SuppressLint("SimpleDateFormat", "SetTextI18n")
@BindingAdapter("timeAgoText")
fun timeAgoText(view: TextView, value: String){


    val suffix = view.context.getString(R.string.tv_ago)
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val pasTime = dateFormat.parse(value)
    val nowTime = Date()
    val dateDiff = nowTime.time - pasTime.time
    val second: Long = TimeUnit.MILLISECONDS.toSeconds(dateDiff)
    val minute: Long = TimeUnit.MILLISECONDS.toMinutes(dateDiff)
    val hour: Long = TimeUnit.MILLISECONDS.toHours(dateDiff)
    val day: Long = TimeUnit.MILLISECONDS.toDays(dateDiff)
    when {
        second < 1 -> {
            view.text = view.context.getString(R.string.tv_recently_ago)
        }
        second < 60 -> {
            view.text = "$second ${view.context.getString(R.string.tv_seconds)} $suffix"
        }
        minute < 60 -> {
            view.text = "$minute ${view.context.getString(R.string.tv_minutes)} $suffix"
        }
        hour < 24 -> {
            view.text = "$hour ${view.context.getString(R.string.tv_hour)} $suffix"
        }
        day >= 7 -> {
            view.text = when {
                day > 360 -> {
                    (day / 360).toString() + " ${view.context.getString(R.string.tv_year)} " + suffix
                }
                day > 30 -> {
                    (day / 30).toString() + " ${view.context.getString(R.string.tv_month)} " + suffix
                }
                else -> {
                    (day / 7).toString() + " ${view.context.getString(R.string.tv_week)} " + suffix
                }
            }
        }
        day < 7 -> {
            view.text = "$day ${view.context.getString(R.string.tv_day)} $suffix"
        }
    }

}
