package com.programmers.kmooc.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

object ImageLoader {
    private val map = HashMap<String, Bitmap>()

    fun loadImage(url: String, completed: (Bitmap?) -> Unit) {
        map[url]?.let(completed) ?: run {
            GlobalScope.launch(Dispatchers.IO) {
                val stream = URL(url).openStream()
                val bitmap = BitmapFactory.decodeStream(stream)
                map[url] = bitmap
                withContext(Dispatchers.Main) {
                    completed(bitmap)
                }
            }
        }
    }
}