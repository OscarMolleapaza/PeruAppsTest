package com.omolleapaza.peruapps.peruappstest.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast




inline fun <reified T : Activity> Context.startNewActivityClearStack() {
    this.startActivity(Intent(this, T::class.java).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    })
}


fun Activity.showToastLong(message: Any?) {
    val textToShow = when (message) {
        is Int -> getString(message)
        is String -> message
        else -> {
            ""
        }
    }
    Toast.makeText(this, textToShow, Toast.LENGTH_LONG).show()
}

fun Activity.showToastShort(message: Any?) {
    val textToShow = when (message) {
        is Int -> getString(message)
        is String -> message
        else -> {
            ""
        }
    }
    Toast.makeText(this, textToShow, Toast.LENGTH_SHORT).show()
}
