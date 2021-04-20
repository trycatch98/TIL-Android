package com.trycatch.til.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.trycatch.til.repository.UserAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userAuthRepository: UserAuthRepository
) : ViewModel() {
    val isLogin: LiveData<Boolean> = userAuthRepository.isLogin().asLiveData()
}