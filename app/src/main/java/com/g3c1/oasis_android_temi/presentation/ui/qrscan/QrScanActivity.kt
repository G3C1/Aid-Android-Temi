package com.g3c1.oasis_android_temi.presentation.ui.qrscan

import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.*
import com.g3c1.oasis_android_temi.R
import com.g3c1.oasis_android_temi.databinding.ActivityQrScanBinding
import com.g3c1.oasis_android_temi.di.TemiApplication
import com.g3c1.oasis_android_temi.presentation.base.BaseActivity
import com.g3c1.oasis_android_temi.presentation.ui.orderinfo.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QrScanActivity : BaseActivity<ActivityQrScanBinding>(R.layout.activity_qr_scan) {
    private lateinit var codeScanner: CodeScanner

    override fun init() {
        setupPermissions()
        codeScanner()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            101 -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "앱을 사용하기 위해서는 카메라 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun codeScanner() {
        codeScanner = CodeScanner(this, binding.scanner)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread {
                    Log.d("QRCODE", it.text)
                    binding.registrationBtn.setEnabled(true)
                    binding.registrationBtn.background = getDrawable(R.drawable.enable_true_btn_bg)
                    onClick()
                    CoroutineScope(Dispatchers.Main).launch {
                        TemiApplication.getInstance().getSearialNumberManager()
                            .setSearialNumber(it.text.toInt())
                    }
                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                    Log.e("QRCODE", it.message!!)
                }
            }

            codeScanner.startPreview()
        }
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CAMERA
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            permissionRequest()
        }
    }

    private fun permissionRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.CAMERA), 101
        )
    }

    private fun onClick() {
        binding.registrationBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}