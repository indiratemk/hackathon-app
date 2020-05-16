package space.platform.hackathon.util.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import space.platform.hackathon.R


class UIUtil {

    companion object {
        fun showErrorMessage(activity: Activity, message: String?) {
            val inflater = activity.layoutInflater
            val layout: View = inflater.inflate(R.layout.layout_error_toast, null, false)
            val text: TextView = layout.findViewById(R.id.text)
            text.text = message
            with (Toast(activity)) {
                setGravity(Gravity.BOTTOM, 0, dpToPx(16))
                duration = Toast.LENGTH_SHORT
                view = layout
                show()
            }
        }

        fun showSuccessMessage(activity: Activity, message: String?) {
            val inflater = activity.layoutInflater
            val layout: View = inflater.inflate(R.layout.layout_success_toast, null, false)
            val text: TextView = layout.findViewById(R.id.text)
            text.text = message
            with (Toast(activity)) {
                setGravity(Gravity.BOTTOM, 0, dpToPx(16))
                duration = Toast.LENGTH_SHORT
                view = layout
                show()
            }
        }

        fun dpToPx(dp: Int): Int {
            val density = Resources.getSystem().getDisplayMetrics().density
            return Math.round(dp * density)
        }

        fun hideKeyboard(activity: Activity) {
            val imm: InputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            var view: View? = activity.currentFocus
            if (view == null) {
                view = View(activity)
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }

        fun openSource(url: String, context: Context) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            context.startActivity(intent)
        }
    }
}