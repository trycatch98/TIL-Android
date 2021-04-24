package com.trycatch.til.ui.main

import androidx.lifecycle.*
import com.trycatch.til.repository.PostRepository
import com.trycatch.til.repository.UserAuthRepository
import com.trycatch.til.util.Event
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

    private val _writeEvent: MutableLiveData<Unit> = MutableLiveData()
    val writeEvent: LiveData<Event<Unit>> = _writeEvent.map {
        Event(it)
    }

    fun write() {
        _writeEvent.postValue(Unit)
    }
}