package com.trycatch.til.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.trycatch.til.repository.UserAuthRepository
import com.trycatch.til.ui.base.BaseViewModel
import com.trycatch.til.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    userAuthRepository: UserAuthRepository
) : BaseViewModel(userAuthRepository) {

    private val _loginEvent: MutableLiveData<Unit> = MutableLiveData()
    val loginEvent: LiveData<Event<Unit>> = _loginEvent.map {
        Event(it)
    }

    fun login() {
        _loginEvent.postValue(Unit)
    }
}