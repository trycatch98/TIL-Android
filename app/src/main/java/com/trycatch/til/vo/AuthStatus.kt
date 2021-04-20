package com.trycatch.til.vo

sealed class AuthStatus {
    object PendingAuth: AuthStatus()
    object SignIn: AuthStatus()
    object FailedAuth: AuthStatus()
}