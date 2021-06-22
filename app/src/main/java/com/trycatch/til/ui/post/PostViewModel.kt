package com.trycatch.til.ui.post

import androidx.lifecycle.*
import com.trycatch.til.dto.PostDTO
import com.trycatch.til.repository.PostRepository
import com.trycatch.til.repository.UserAuthRepository
import com.trycatch.til.ui.base.BaseViewModel
import com.trycatch.til.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    userAuthRepository: UserAuthRepository,
    private val postRepository: PostRepository
) : BaseViewModel(userAuthRepository) {
    val content: MutableLiveData<String> = MutableLiveData()

    private val _writeEvent: MutableLiveData<Unit> = MutableLiveData()
    val writeEvent: LiveData<Event<Unit>> = _writeEvent.switchMap {
        userID.map {
            Event(
                postRepository.writePost(
                        it,
                        PostDTO(
                                content.value!!,
                                Date().toString(),
                                listOf()
                        )
                )
            )
        }
    }

    fun write() {
        _writeEvent.postValue(Unit)
    }
}