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
import com.example.hackathon.util.ui.UIUtil
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.android.synthetic.main.activity_qr_scanner.*
import org.koin.android.viewmodel.ext.android.viewModel

class QRScannerActivity : BaseActivity(), QRCodeReaderView.OnQRCodeReadListener {

    companion object {
        fun startActivity(activity: Activity, requestCode: Int) {
            val intent = Intent(activity, QRScannerActivity::class.java)
            activity.startActivityForResult(intent, requestCode)
        }
    }

    private val qrScannerViewModel: QRScannerViewModel by viewModel()
    private var isFlashEnabled = false
    private var isFrontCamera = false
    private var isProcessing = false
    private var hackathonId: Int? = null
    private lateinit var progressDialog: ProgressDialog

    override fun layoutId(): Int {
        return R.layout.activity_qr_scanner
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
            if (dataState.isLoading) showProgressDialog() else hideProgressDialog()
            dataState.result?.let {
                ParticipationConfirmedActivity.startActivity(this, hackathonId!!,
                    Constants.PARTICIPATION_CONFIRMED_REQUEST_CODE)
                setResult(Activity.RESULT_OK)
                finish()
            }
            dataState.message?.let { message ->
                UIUtil.showErrorMessage(this, message)
                isProcessing = true
                qrScannerView.startCamera()
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

    private fun showProgressDialog() {
        progressDialog.setMessage(getString(R.string.qr_scanner_loading))
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
                hackathonId = qrParams.hackathonId
                qrScannerViewModel.confirmParticipation(qrParams.hackathonId, qrParams.userId)
            }
        } catch (e: JsonSyntaxException) {
            tvQRError.visibility = View.VISIBLE
        }
    }
}