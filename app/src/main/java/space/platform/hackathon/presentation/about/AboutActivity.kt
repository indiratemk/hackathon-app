package space.platform.hackathon.presentation.about

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import space.platform.hackathon.R
import space.platform.hackathon.presentation.base.BaseActivity
import space.platform.hackathon.util.Constants
import space.platform.hackathon.util.ui.UIUtil
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class AboutActivity : BaseActivity() {

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, AboutActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var reviewManager: ReviewManager
    private var reviewInfo: ReviewInfo? = null

    override fun layoutId() = R.layout.activity_about

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        initReviews()
    }

    private fun initUI() {
        initToolbar(toolbar, getString(R.string.about_title), false)
        tvAntDesign.setOnClickListener { UIUtil.openSource(Constants.ANT_DESIGN_URL, this) }
        tvAntDesignSite.setOnClickListener { UIUtil.openSource(Constants.ANT_DESIGN_URL, this) }
        tvFreepik.setOnClickListener { UIUtil.openSource(Constants.FREEPIK_URL, this) }
        tvFreepikSite.setOnClickListener { UIUtil.openSource(Constants.FLATICONS_URL, this) }
        tvPixelPerfect.setOnClickListener { UIUtil.openSource(Constants.PIXEL_PERFECT_URL, this) }
        tvPixelPerfectSite.setOnClickListener { UIUtil.openSource(Constants.FLATICONS_URL, this) }
        btnLeaveReview.setOnClickListener {
            reviewInfo?.let {
                reviewManager.launchReviewFlow(this, it)
                    .addOnFailureListener {
                        Log.d("taaaaaaag", "Что-то пошло совсем не так(((")
                    }
                    .addOnCompleteListener {
                        Log.d("taaaaaaag", "Успешно отправлено!)")
                    }
            }
        }
    }

    private fun initReviews() {
        reviewManager = ReviewManagerFactory.create(this)
        reviewManager.requestReviewFlow().addOnCompleteListener { request ->
            if (request.isSuccessful) {
                reviewInfo = request.result
            } else {
                Log.d("taaaaaaag", "Что-то пошло не так(")
            }
        }
    }
}
