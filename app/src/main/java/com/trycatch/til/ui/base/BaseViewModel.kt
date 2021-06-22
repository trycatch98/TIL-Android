package com.trycatch.til.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.trycatch.til.repository.UserAuthRepository
import javax.inject.Inject

open class BaseViewModel @Inject constructor(
    private val userAuthRepository: UserAuthRepository
) : ViewModel() {
    val isLogin: LiveData<Boolean> get() = userAuthRepository.isLogin().asLiveData()

    val userID: LiveData<String> get() = userAuthRepository.getUserID().asLiveData()
}