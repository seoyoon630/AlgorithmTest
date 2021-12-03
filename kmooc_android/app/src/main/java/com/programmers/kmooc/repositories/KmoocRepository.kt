package com.programmers.kmooc.repositories

import android.util.Log
import com.programmers.kmooc.models.Lecture
import com.programmers.kmooc.models.LectureList
import com.programmers.kmooc.network.HttpClient
import com.programmers.kmooc.utils.DateUtil
import org.json.JSONObject

class KmoocRepository {

    /**
     * 국가평생교육진흥원_K-MOOC_강좌정보API
     * https://www.data.go.kr/data/15042355/openapi.do
     */

    private val httpClient = HttpClient("http://apis.data.go.kr/B552881/kmooc")
    private val serviceKey =
        "z%2Bnywv%2BJqGS7y0eE81pCLLZHXCMhfJa%2BCHuTaYVlzAo4avU3RY6MWrmer7BqXM4HlEij8PHcSXttUScZXsOjWA%3D%3D"

    fun list(completed: (LectureList) -> Unit) {
        httpClient.getJson(
            "/courseList",
            mapOf("serviceKey" to serviceKey, "Mobile" to 1)
        ) { result ->
            result.onSuccess {
                Log.w("Result", it)
                completed(parseLectureList(JSONObject(it)))
            }
        }
    }

    fun next(currentPage: LectureList, completed: (LectureList) -> Unit) {
        val nextPageUrl = currentPage.next
        httpClient.getJson(nextPageUrl, emptyMap()) { result ->
            result.onSuccess {
                completed(parseLectureList(JSONObject(it)))
            }
        }
    }

    fun detail(courseId: String, completed: (Lecture) -> Unit) {
        httpClient.getJson(
            "/courseDetail",
            mapOf("CourseId" to courseId, "serviceKey" to serviceKey)
        ) { result ->
            result.onSuccess {
                completed(parseLecture(JSONObject(it)))
            }
        }
    }

    private fun parseLectureList(jsonObject: JSONObject): LectureList {
        //TODO: JSONObject -> LectureList 를 구현하세요
        val pagination = jsonObject.get("pagination") as JSONObject
        val lectures = ArrayList<Lecture>()
        val array = jsonObject.getJSONArray("results")
        repeat(array.length()) {
            lectures.add(parseLecture(array.get(it) as JSONObject))
        }

        return LectureList(
            pagination.getInt("count"),
            pagination.getInt("num_pages"),
            pagination.getString("previous"),
            pagination.getString("next"),
            lectures
        )
    }

    private fun parseLecture(jsonObject: JSONObject): Lecture {
        //TODO: JSONObject -> Lecture 를 구현하세요
        val image = jsonObject.getJSONObject("media").getJSONObject("image")
        val id = jsonObject.getString("id")
        return Lecture(
            id,
            jsonObject.getString("number"),
            jsonObject.getString("name"),
            jsonObject.getString("classfy_name"),
            jsonObject.getString("middle_classfy_name"),
            image.getString("small"),
            image.getString("large"),
            jsonObject.getString("short_description"),
            jsonObject.getString("org_name"),
            DateUtil.parseDate(jsonObject.getString("start")),
            DateUtil.parseDate(jsonObject.getString("end")),
            jsonObject.getString("teachers"),
//            jsonObject.getString("blocks_url"),
            "http://www.kmooc.kr/courses/${id}/about"
        )
    }
}