package com.omolleapaza.peruapps.peruappstest.ui.base


import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar


abstract class BaseFragment : Fragment() {

    private var isLoadingShow: Boolean = false

    //region SnackBar
    protected fun showSnackBar(
        rootView: View,
        contentText: Any?,
        duration: Int = Snackbar.LENGTH_LONG
    ) {
        val text = when (contentText) {
            is String -> contentText
            is Int -> getString(contentText)
            else -> ""
        }
        Snackbar.make(rootView, text, duration).show()
    }


    //endregion

    private fun getDynamicMessage(messageToShow: Any?): String? {
        return if (messageToShow != null) {
            when (messageToShow) {
                is Int -> {
                    getString(messageToShow)
                }
                is String -> {
                    messageToShow
                }
                else -> {
                    null
                }
            }
        } else {
            messageToShow
        }
    }






    //endregion



    private fun removeErrorDialogFromBackStack() {
        if (activity != null) {
            val oldErrorDialog: DialogFragment? =
                childFragmentManager.findFragmentByTag("ErrorDialog") as DialogFragment?
            if (oldErrorDialog != null) {
                if (!oldErrorDialog.isVisible) {
                    childFragmentManager.beginTransaction()
                        .remove(oldErrorDialog)
                        .addToBackStack(null)
                } else {
                    oldErrorDialog.dismiss()
                    childFragmentManager.beginTransaction()
                        .remove(oldErrorDialog)
                        .addToBackStack(null)
                }
            }
        }
    }
    //endregion

    protected fun showToast(message: Any?) {
        val textToShow = when (message) {
            is Int -> getString(message)
            is String -> message
            else -> {
                ""
            }
        }
        Toast.makeText(requireContext(), textToShow, Toast.LENGTH_LONG).show()
    }

    protected fun logError(message: Any?) {
        Log.e(this.javaClass.simpleName, "LOG ERROR: $message")
    }

    protected fun logDebug(message: Any?) {
        Log.d(this.javaClass.simpleName, "LOG DEBUG: $message")
    }
}