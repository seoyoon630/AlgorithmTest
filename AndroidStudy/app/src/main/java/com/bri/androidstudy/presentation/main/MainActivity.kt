package com.bri.androidstudy.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bri.androidstudy.R
import com.bri.androidstudy.core.platform.BaseActivity
import com.bri.androidstudy.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private val binding by lazy<ActivityMainBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
    override val vm by viewModels<MainViewModel>()
    private val adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = vm
        binding.list.adapter = adapter
        binding.list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    vm.getNextUsers()
                }
            }
        })
        binding.editQuery.requestFocus()

        vm.setUsers.observe(this) { users -> adapter.setUsers(users) }
        vm.addUsers.observe(this) { users -> adapter.addUsers(users) }
        vm.bitmaps.observe(this) { adapter.updateAvatars(it) }
    }

}