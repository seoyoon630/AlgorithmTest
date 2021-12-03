package com.programmers.kmooc.activities.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.programmers.kmooc.R
import com.programmers.kmooc.databinding.ViewKmookListItemBinding
import com.programmers.kmooc.models.Lecture

class LecturesAdapter : ListAdapter<Lecture, LecturesAdapter.LectureViewHolder>(diffCallback) {
    var onClick: (Lecture) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LectureViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_kmook_list_item, parent, false)
        val binding = ViewKmookListItemBinding.bind(view)
        return LectureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LectureViewHolder, position: Int) {
        val lecture = getItem(position)
        holder.itemView.setOnClickListener { onClick(lecture) }
        holder.onBind(lecture)
    }

    override fun submitList(list: MutableList<Lecture>?) {
        super.submitList(ArrayList(list ?: listOf()))
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Lecture>() {
            override fun areItemsTheSame(oldItem: Lecture, newItem: Lecture): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Lecture, newItem: Lecture): Boolean =
                oldItem.id == newItem.id
        }
    }

    inner class LectureViewHolder(private val binding: ViewKmookListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(lecture: Lecture) {
            binding.lecture = lecture
        }
    }
}
