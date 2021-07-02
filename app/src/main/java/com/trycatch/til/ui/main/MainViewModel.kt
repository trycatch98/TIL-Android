package com.trycatch.til.ui.main

import android.net.Uri
import androidx.lifecycle.*
import com.trycatch.til.repository.PostRepository
import com.trycatch.til.repository.UserAuthRepository
import com.trycatch.til.ui.base.BaseViewModel
import com.trycatch.til.util.Event
import com.trycatch.til.vo.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    userAuthRepository: UserAuthRepository,
    private val postRepository: PostRepository
) : BaseViewModel(userAuthRepository) {

    val posts: LiveData<List<Post>> = userID.switchMap {
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