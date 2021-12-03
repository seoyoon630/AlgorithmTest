package com.bri.androidstudy.presentation.main

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bri.androidstudy.databinding.RowUserBinding
import com.bri.androidstudy.domain.model.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserHolder>() {
    private val data = ArrayList<User>()
    private val avatarMap = HashMap<Int, Bitmap>()

    fun addUsers(users: Collection<User>) {
        val prev = data.clone() as List<*>
        data.addAll(users)
        DiffUtil.calculateDiff(getDiffUtilCallback(prev, data, avatarMap, avatarMap))
            .dispatchUpdatesTo(this)
    }

    fun setUsers(users: Collection<User>) {
        val prev = data.clone() as List<*>
        data.clear()
        data.addAll(users)
        DiffUtil.calculateDiff(getDiffUtilCallback(prev, data, avatarMap, avatarMap))
            .dispatchUpdatesTo(this)
    }

    fun updateAvatars(avatars: List<Pair<Int, Bitmap?>>?) {
        val prev = avatarMap.clone() as HashMap<*, *>
        avatars?.forEach { avatar ->
            avatar.second?.let { bitmap -> avatarMap[avatar.first] = bitmap }
        }
        DiffUtil.calculateDiff(getDiffUtilCallback(data, data, prev, avatarMap))
            .dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserHolder(RowUserBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class UserHolder(private val binding: RowUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.user = user
            binding.bitmap = avatarMap[user.id]
        }
    }

    private fun getDiffUtilCallback(
        prev: List<*>,
        next: List<*>,
        prevMap: HashMap<*, *>,
        nextMap: HashMap<*, *>
    ) = object : DiffUtil.Callback() {
        override fun getOldListSize(): Int = prev.size

        override fun getNewListSize(): Int = next.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val prevId = (prev[oldItemPosition] as User).id
            val nextId = (next[newItemPosition] as User).id
            return prev[oldItemPosition] == next[newItemPosition] && prevMap[prevId] == nextMap[nextId]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return prev[oldItemPosition] == next[newItemPosition]
        }
    }
}
