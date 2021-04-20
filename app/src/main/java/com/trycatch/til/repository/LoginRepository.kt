package com.trycatch.til.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.trycatch.til.vo.AuthStatus

class LoginRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun login() : LiveData<AuthStatus> {
        val result = MutableLiveData<AuthStatus>()
        val pendingResultTask: Task<AuthResult>? = firebaseAuth.pendingAuthResult

        if (pendingResultTask != null) {
            pendingResultTask
                .addOnSuccessListener {
                    result.postValue(AuthStatus.PendingAuth)
                }.addOnFailureListener {
                    result.postValue(AuthStatus.FailedAuth)
                }
        } else {
            result.postValue(AuthStatus.SignIn)
        }

        return result
    }

}