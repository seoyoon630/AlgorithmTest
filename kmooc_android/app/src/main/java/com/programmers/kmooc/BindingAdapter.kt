package com.programmers.kmooc

import android.annotation.SuppressLint
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.programmers.kmooc.models.Lecture
import com.programmers.kmooc.network.ImageLoader
import com.programmers.kmooc.utils.DateUtil
import java.util.*

@SuppressLint("SetTextI18n")
@BindingAdapter("app:start", "app:end", requireAll = true)
fun showPeriod(v: TextView, start: Date, end: Date) {
    v.text = "${DateUtil.formatDate(start)} ~ ${DateUtil.formatDate(end)}"
}

@BindingAdapter("app:url_to_bitmap")
fun urlToBitmap(v: ImageView, url: String?) {
    url?.let { ImageLoader.loadImage(url) { bitmap -> v.setImageBitmap(bitmap) } }
}

@BindingAdapter("app:data")
fun <T> setData(v : RecyclerView, data : List<T>){
    (v.adapter as? ListAdapter<T, RecyclerView.ViewHolder>)?.submitList(data)
    Log.w("adapter", "${data.size}")
    Log.w("adapter", data.joinToString{(it as Lecture).name})
}