package com.trycatch.til.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.trycatch.til.repository.PostRepository
import com.trycatch.til.repository.UserAuthRepository
import com.trycatch.til.vo.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userAuthRepository: UserAuthRepository,
    private val postRepository: PostRepository
) : ViewModel() {
    val isLogin: LiveData<Boolean> = userAuthRepository.isLogin().asLiveData()

    val posts: LiveData<List<Post>> = userAuthRepository.getUserID().asLiveData().switchMap {
        postRepository.getPosts(it).asLiveData()
    }
}