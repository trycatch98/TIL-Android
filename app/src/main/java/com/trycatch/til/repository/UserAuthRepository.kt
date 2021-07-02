package com.trycatch.til.repository

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserAuthRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun isLogin(): Flow<Boolean> = flow {
        emit(firebaseAuth.currentUser != null)
    }

    fun logout() {
        firebaseAuth.signOut()
    }

    fun getUserID(): Flow<String> = flow {
        emit(firebaseAuth.currentUser?.uid!!)
    }

    fun getUserProfileImage() = firebaseAuth.currentUser?.photoUrl!!
}