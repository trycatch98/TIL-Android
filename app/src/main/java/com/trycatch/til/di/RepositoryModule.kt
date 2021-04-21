package com.trycatch.til.di

import com.trycatch.til.repository.PostRepository
import com.trycatch.til.repository.UserAuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideUserAuthRepository() = UserAuthRepository()

    @Provides
    fun providePostRepository() = PostRepository()
}