package com.trycatch.til.ui.login

import androidx.lifecycle.*
import com.trycatch.til.repository.UserAuthRepository
import com.trycatch.til.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userAuthRepository: UserAuthRepository
) : ViewModel() {

    private val _loginEvent: MutableLiveData<Unit> = MutableLiveData()
    val loginEvent: LiveData<Event<Unit>> = _loginEvent.map {
        Event(it)
    }

    fun login() {
        _loginEvent.postValue(Unit)
    }
}