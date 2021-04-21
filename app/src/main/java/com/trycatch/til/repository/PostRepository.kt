package com.trycatch.til.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.trycatch.til.dto.PostDTO
import com.trycatch.til.vo.Post
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class PostRepository {
    private val db = FirebaseFirestore.getInstance()

    fun getPosts(userID: String): Flow<List<Post>> = callbackFlow {
        db.collection("user")
            .document(userID)
            .collection("posts")
            .addSnapshotListener { value, error ->
                if (error != null)
                    return@addSnapshotListener

                val list = mutableListOf<Post>()

                value?.documents?.forEach {
                    list.add(
                        Post(
                            it.id,
                            it.data?.get("content") as? String ?: return@forEach,
                            it.data?.get("date") as? String ?: return@forEach,
                            it.data?.get("images") as? List<String> ?: return@forEach,
                        )
                    )
                }
                sendBlocking(list)
            }

        awaitClose()
    }

    fun writePost(userID: String, post: PostDTO) {
        db.collection("user")
            .document(userID)
            .collection("posts")
            .add(post)
            .addOnSuccessListener {}
            .addOnFailureListener {}
    }

    fun updatePost(userID: String, postID: String, post: PostDTO) {
        db.collection("user")
            .document(userID)
            .collection("posts")
            .document(postID)
            .set(post)
            .addOnSuccessListener {}
            .addOnFailureListener {}
    }

    fun deletePost(userID: String, postID: String) {
        db.collection("user")
            .document(userID)
            .collection("posts")
            .document(postID)
            .delete()
            .addOnSuccessListener {}
            .addOnFailureListener {}
    }
}