package com.bri.androidstudy.presentation.main

import android.content.Context
import android.graphics.Bitmap
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("bitmap")
fun bitmap(v: ImageView, bitmap: Bitmap?) {
    v.setImageBitmap(bitmap)
}

@BindingAdapter("onEnter")
fun onEnter(v: EditText, function: () -> Unit) {
    v.setOnKeyListener { _, i, keyEvent ->
        if (i == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_DOWN) {
            function.invoke()
            val imm: InputMethodManager? =
                v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
            true
        } else false
    }
}