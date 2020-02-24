package com.example.hackathon.util.ui

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.hackathon.R

class UIUtil {

    companion object {
        fun showErrorMessage(activity: Activity, message: String?) {
            val inflater = activity.layoutInflater
            val layout: View = inflater.inflate(R.layout.layout_error_toast, null, false)
            val text: TextView = layout.findViewById(R.id.text)
            text.text = message
            with (Toast(activity)) {
                setGravity(Gravity.TOP, 0, convertDpToPx(activity, 16))
                duration = Toast.LENGTH_SHORT
                view = layout
                show()
            }
        }

        fun convertDpToPx(context: Context, dp: Int): Int {
            return (dp * context.resources.displayMetrics.density).toInt()
        }
    }
}