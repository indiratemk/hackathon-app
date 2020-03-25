package com.example.hackathon.presentation.qr_scanner

import android.app.Activity
import android.content.Intent
import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import android.view.View
import com.dlazaro66.qrcodereaderview.QRCodeReaderView
import com.example.hackathon.R
import com.example.hackathon.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_qr_scanner.*

// TODO может сделать расширение для QRCodeReaderView чтобы не тащить весь класс
class QRScannerActivity : BaseActivity(), QRCodeReaderView.OnQRCodeReadListener {

    private var isFlashEnabled = false
    private var isFrontCamera = false

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
        initUI()
    }

    private fun initUI() {
        qrScannerView.setOnQRCodeReadListener(this)
        qrScannerView.setAutofocusInterval(1000L)

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

    override fun onResume() {
        super.onResume()
        qrScannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        qrScannerView.stopCamera()
    }

    override fun onQRCodeRead(text: String?, points: Array<out PointF>?) {
        Log.d("taaaaag", "text: ${text}")
//        finish()
    }
}