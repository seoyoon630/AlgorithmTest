package com.programmers.kmooc.activities.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.programmers.kmooc.KmoocApplication
import com.programmers.kmooc.databinding.ActivityKmookDetailBinding
import com.programmers.kmooc.utils.DateUtil
import com.programmers.kmooc.viewmodels.KmoocDetailViewModel
import com.programmers.kmooc.viewmodels.KmoocDetailViewModelFactory

class KmoocDetailActivity : AppCompatActivity() {

    companion object {
        const val INTENT_PARAM_COURSE_ID = "param_course_id"
    }

    private lateinit var binding: ActivityKmookDetailBinding
    private lateinit var viewModel: KmoocDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val kmoocRepository = (application as KmoocApplication).kmoocRepository
        viewModel = ViewModelProvider(this, KmoocDetailViewModelFactory(kmoocRepository)).get(
            KmoocDetailViewModel::class.java
        )

        binding = ActivityKmookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getStringExtra(INTENT_PARAM_COURSE_ID)?.let { courseId ->
            viewModel.detail(courseId)
        }

        viewModel.lecture.observe(this) {
            binding.apply {
                lecture = it
                lectureNumber.setDescription("강좌번호 : ", it.number)
                lectureType.setDescription("강좌분류 : ", it.classfyName)
                lectureOrg.setDescription("운영기관 : ", it.orgName)
                lectureTeachers.setDescription("교수정보 : ", it.teachers ?: "")
                lectureDue.setDescription(
                    "운영기관 : ",
                    "${DateUtil.formatDate(it.start)} ~ ${DateUtil.formatDate(it.end)}"
                )
                it.overview?.let { url -> webView.loadUrl(url) }
            }
        }
        viewModel.progress.observe(this) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
    }
}