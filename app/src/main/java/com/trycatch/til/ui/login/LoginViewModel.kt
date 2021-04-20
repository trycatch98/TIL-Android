package com.trycatch.til.ui.login

import androidx.lifecycle.*
import com.trycatch.til.repository.LoginRepository
import com.trycatch.til.util.Event
import com.trycatch.til.vo.AuthStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _loginEvent: MutableLiveData<Unit> = MutableLiveData()
    val loginEvent: LiveData<Event<AuthStatus>> = _loginEvent.switchMap {
        loginRepository.login().map {
            Event(it)
        }
    }

    fun login() {
        _loginEvent.postValue(Unit)
    }
}