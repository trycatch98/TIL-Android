package com.trycatch.til.repository

import android.text.format.DateUtils
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.trycatch.til.dto.PostDTO
import com.trycatch.til.vo.Post
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

class PostRepository {
    private val db = FirebaseFirestore.getInstance()

    @ExperimentalCoroutinesApi
    fun getPosts(userID: String): Flow<List<Post>> = callbackFlow {
        var eventCollection: CollectionReference? = null
        val gson = Gson()

        try {
            eventCollection = db.collection("user")
                .document(userID)
                .collection("posts")
        } catch (e: Throwable) {
            close(e)
        }

        val subscription = eventCollection?.orderBy("date", Query.Direction.DESCENDING)?.addSnapshotListener { value, error ->
            if (error != null)
                return@addSnapshotListener

            val list = mutableListOf<Post>()

            val parse = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREAN)
            val format = SimpleDateFormat("yyyy.MM.dd", Locale.KOREAN)

            value?.documents?.forEach {
                val data = it.data?.toMutableMap()?.let { map ->
                    map["id"] = it.id
                    val dateText = parse.parse(map["date"] as String)?.run {
                        when {
                            DateUtils.isToday(time) -> "오늘"
                            DateUtils.isToday(time + (1000 * 60 * 60 * 24)) -> "어제"
                            else -> format.format(time)
                        }
                    }

                    map["date"] = dateText
                    gson.toJson(map)
                }

                val type: Type = object : TypeToken<Post>() {}.type

                val post: Post = Gson().fromJson(data, type)

                list.add(post)
            }
            try {
                offer(list)
            } catch (e: Throwable) { }
        }

        awaitClose{ subscription?.remove() }
    }

    fun writePost(userID: String, post: PostDTO) {
        db.collection("user")
            .document(userID)
            .collection("posts")
            .add(post)
    }

    fun updatePost(userID: String, postID: String, post: PostDTO) {
        db.collection("user")
            .document(userID)
            .collection("posts")
            .document(postID)
            .set(post)
    }

    fun deletePost(userID: String, postID: String) {
        db.collection("user")
            .document(userID)
            .collection("posts")
            .document(postID)
            .delete()
    }
}