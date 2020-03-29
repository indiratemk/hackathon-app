package com.example.hackathon.presentation.qr_scanner

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.PointF
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.dlazaro66.qrcodereaderview.QRCodeReaderView
import com.example.hackathon.R
import com.example.hackathon.data.hackathon.model.QRParams
import com.example.hackathon.presentation.base.BaseActivity
import com.example.hackathon.util.Constants
import com.example.hackathon.util.state.State
import com.example.hackathon.util.ui.UIUtil
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.android.synthetic.main.activity_qr_scanner.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

// TODO может сделать расширение для QRCodeReaderView чтобы не тащить весь класс
class QRScannerActivity : BaseActivity(), QRCodeReaderView.OnQRCodeReadListener, CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    private val qrScannerViewModel: QRScannerViewModel by viewModel()
    private var isFlashEnabled = false
    private var isFrontCamera = false
    private var isProcessing = false
    private lateinit var progressDialog: ProgressDialog

    override fun layoutId(): Int {
        return R.layout.activity_qr_scanner
    }

    companion object {
        fun startActivity(activity: Activity, requestCode: Int) {
            val intent = Intent(activity, QRScannerActivity::class.java)
            activity.startActivityForResult(intent, requestCode)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.DarkTheme)
        super.onCreate(savedInstanceState)
        subscribeObservers()
        initUI()
    }

    private fun initUI() {
        progressDialog = ProgressDialog(this)
        qrScannerView.setOnQRCodeReadListener(this)
        qrScannerView.setAutofocusInterval(2000L)

        ivClose.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
        ivFlash.setOnClickListener {
            isFlashEnabled = !isFlashEnabled
            ivFlash.setImageResource(if (isFlashEnabled) R.drawable.ic_flash else R.drawable.ic_flash_off)
            qrScannerView.setTorchEnabled(isFlashEnabled)
        }
        ivCameraRotation.setOnClickListener {
            isFrontCamera = !isFrontCamera
            qrScannerView.switchCameraFace()
            ivFlash.visibility = if (isFrontCamera) View.GONE else View.VISIBLE
        }
    }

    private fun subscribeObservers() {
        qrScannerViewModel.qrParams.observe(this, Observer { dataState ->
            when (dataState) {
                is State.Loading -> {
                    if (dataState.isLoading) showProgressDialog() else hideProgressDialog()
                }
                is State.Success -> {
                    ParticipationConfirmedActivity.startActivity(this, Constants.PARTICIPATION_CONFIRMED_REQUEST_CODE)
                    setResult(Activity.RESULT_OK)
                    finish()
                }
                else -> {
                    UIUtil.showErrorMessage(this, dataState.message)
                    isProcessing = true
                    qrScannerView.startCamera()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        qrScannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        qrScannerView.stopCamera()
    }

    private fun initLoadingDialog() {

    }
    
    private fun showProgressDialog() {
        progressDialog.setMessage("Пожалуйста подождите...")
        progressDialog.show()
    }

    private fun hideProgressDialog() {
        progressDialog.dismiss()
    }

    override fun onQRCodeRead(result: String?, points: Array<out PointF>?) {
        try {
            val gson = Gson()
            val qrParams: QRParams = gson.fromJson(result, QRParams::class.java)
            qrScannerView.stopCamera()
            if (!isProcessing) {
                isProcessing = true
                tvQRError.visibility = View.GONE
                qrScannerViewModel.confirmParticipation(qrParams.hackathonId, qrParams.userId)
            }
        } catch (e: JsonSyntaxException) {
            tvQRError.visibility = View.VISIBLE
        }
    }
}