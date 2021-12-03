package com.bri.androidstudy.core.platform

import android.R
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class BaseActivity : AppCompatActivity() {
    abstract val vm: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.message.observe(this) { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
        vm.progress.observe(this) { if (it) showProgress() else dismissProgress() }
    }


    val progress: AppCompatDialog by lazy { createProgress() }
    protected open fun createProgress(): AppCompatDialog {
        val builder = AlertDialog.Builder(this)
        builder.setView(ProgressBar(this, null, R.attr.progressBarStyleLarge))
        val dlg = builder.create()
        dlg.window!!.setBackgroundDrawable(ColorDrawable(0x00ff0000))
        dlg.setCanceledOnTouchOutside(false)
        dlg.setCancelable(true)
        return dlg
    }

    open fun showProgress() {
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) return
        if (isFinishing) return
        if (progress.isShowing) return
        progress.show()
    }

    open fun dismissProgress() {
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) return
        if (isFinishing) return

        if (progress.isShowing) {
            lifecycleScope.launch {
                delay(200)
                progress.dismiss()
            }
        }
    }
}