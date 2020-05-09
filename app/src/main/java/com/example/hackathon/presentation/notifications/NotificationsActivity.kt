package com.example.hackathon.presentation.notifications

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.WhichButton
import com.afollestad.materialdialogs.actions.getActionButton
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.example.hackathon.R
import com.example.hackathon.presentation.base.BaseActivity
import com.example.hackathon.util.ui.UIUtil
import kotlinx.android.synthetic.main.activity_notifications.*
import kotlinx.android.synthetic.main.layout_empty_list.*
import kotlinx.android.synthetic.main.layout_review_form.*
import kotlinx.android.synthetic.main.layout_review_form.view.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.koin.android.viewmodel.ext.android.viewModel

class NotificationsActivity : BaseActivity(),
    NotificationClickListener {

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, NotificationsActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val notificationsViewModel: NotificationsViewModel by viewModel()
    private val notificationsAdapter = NotificationsAdapter(this)
    private var positionForUpdate: Int = -1
    private var rating: Int = -1

    override fun layoutId() = R.layout.activity_notifications

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
        initUI()
    }

    private fun subscribeObservers() {
        notificationsViewModel.notifications.observe(this, Observer { dataState ->
            progressBar.visibility = if (dataState.isLoading) View.VISIBLE else View.GONE
            onStateChange(dataState)
            dataState.result?.let { result ->
                if (result.data.isEmpty()) {
                    llEmptyList.visibility = View.VISIBLE
                    tvEmptyListMessage.text = getString(R.string.notifications_empty_list)
                    rvNotifications.visibility = View.GONE
                }
                notificationsAdapter.setNotifications(result.data)
            }
        })

        notificationsViewModel.isRemoved.observe(this, Observer { dataState ->
            onStateChange(dataState)
            dataState.result?.let {
                notificationsAdapter.removeNotification(positionForUpdate)
                if (notificationsAdapter.isEmpty()) {
                    llEmptyList.visibility = View.VISIBLE
                    tvEmptyListMessage.text = getString(R.string.notifications_empty_list)
                    rvNotifications.visibility = View.GONE
                }
            }
        })

        notificationsViewModel.isAccepted.observe(this, Observer { dataState ->
            onStateChange(dataState)
            dataState.result?.let {
                notificationsAdapter.updateNotification(positionForUpdate)
                UIUtil.showSuccessMessage(this, getString(R.string.notifications_invitation_accepted))
            }
        })

        notificationsViewModel.isSent.observe(this, Observer { dataState ->
            rating = -1
            onStateChange(dataState)
            dataState.result?.let {
                UIUtil.showSuccessMessage(this, getString(R.string.notifications_review_form_success))
            }
        })
    }

    private fun initUI() {
        initToolbar(toolbar, getString(R.string.notifications_title), false)
        initRV()
    }

    private fun initRV() {
        rvNotifications.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = notificationsAdapter
        }
    }

    override fun onRemoveClick(id: Int, position: Int) {
        positionForUpdate = position
        notificationsViewModel.removeNotification(id)
    }

    override fun onAcceptClick(code: String, detailsId: Int, teamId: Int, position: Int) {
        positionForUpdate = position
        notificationsViewModel.acceptInvite(code, detailsId, teamId)
    }

    override fun onGiveFeedBackClick(hackathonId: Int, title: String) {
        MaterialDialog(this).show {
            customView(R.layout.layout_review_form)
            tvTitle.text = getString(R.string.notifications_review_form_title, title)
            tvRatingFive.text = String(Character.toChars(0x1F60D))
            tvRatingFour.text = String(Character.toChars(0x1F604))
            tvRatingThree.text = String(Character.toChars(0x1F612))
            tvRatingTwo.text = String(Character.toChars(0x1F620))
            tvRatingOne.text = String(Character.toChars(0x1F621))

            tvRatingFive.setOnClickListener {
                updateRating(getCustomView(), tvRatingFive)
                rating = 5
            }

            tvRatingFour.setOnClickListener {
                updateRating(getCustomView(), tvRatingFour)
                rating = 4
            }

            tvRatingThree.setOnClickListener {
                updateRating(getCustomView(), tvRatingThree)
                rating = 3
            }

            tvRatingTwo.setOnClickListener {
                updateRating(getCustomView(), tvRatingTwo)
                rating = 2
            }

            tvRatingOne.setOnClickListener {
                updateRating(getCustomView(), tvRatingOne)
                rating = 1
            }
            positiveButton(R.string.notifications_review_form_send)
            positiveButton {
                if (rating != -1) {
                    notificationsViewModel.sendFeedback(hackathonId,
                        etReview.text.toString().trim(), rating)
                    dismiss()
                } else {
                    UIUtil.showErrorMessage(this@NotificationsActivity,
                        getString(R.string.notifications_review_form_error))
                }
            }
            negativeButton(R.string.notifications_review_form_cancel)
            negativeButton { dismiss() }
            getActionButton(WhichButton.NEGATIVE).updateTextColor(R.color.colorGray400)
            noAutoDismiss()
        }
    }

    private fun updateRating(view: View, tvRating: TextView) {
        view.tvRatingFive.background = ContextCompat.getDrawable(this,
            R.drawable.shape_rating_inactive_bg)
        view.tvRatingFour.background = ContextCompat.getDrawable(this,
            R.drawable.shape_rating_inactive_bg)
        view.tvRatingThree.background = ContextCompat.getDrawable(this,
            R.drawable.shape_rating_inactive_bg)
        view.tvRatingTwo.background = ContextCompat.getDrawable(this,
            R.drawable.shape_rating_inactive_bg)
        view.tvRatingOne.background = ContextCompat.getDrawable(this,
            R.drawable.shape_rating_inactive_bg)

        tvRating.background = ContextCompat.getDrawable(this,
            R.drawable.shape_rating_active_bg)
    }
}
