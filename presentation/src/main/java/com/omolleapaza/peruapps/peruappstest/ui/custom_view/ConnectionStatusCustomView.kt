package com.omolleapaza.peruapps.peruappstest.ui.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.omolleapaza.peruapps.peruappstest.R
import com.omolleapaza.peruapps.peruappstest.enums.ConnectionStatusEnum


class ConnectionStatusCustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val image: ImageView
    private val textView: TextView
    init{
         val view = LayoutInflater
             .from(context)
             .inflate(R.layout.view_connection_status, this , true)
        visibility = View.GONE
        image =  view.findViewById(R.id.ivIcon)
        textView = view.findViewById(R.id.tvMessage )
    }

    fun setMessage(message: String){
        textView.text = message
    }

    fun setIcon(@DrawableRes resId: Int){
        image.setImageResource(resId)
    }

    fun setColor(@ColorRes colorId: Int){
        setBackgroundColor(ContextCompat.getColor(context, colorId))
    }

    fun setConnectionStatusEnum(connectionStatusEnum: ConnectionStatusEnum){
        when(connectionStatusEnum){
            ConnectionStatusEnum.FAILED-> {
                setIcon(R.drawable.ic_failed_connection)
                setBackgroundColor(ContextCompat.getColor(context, R.color.colorError))
                setMessage(context.getString(R.string.tv_failed_connection))
                visibility = VISIBLE
            }

            ConnectionStatusEnum.NONE ->
                visibility = GONE
        }
    }

}