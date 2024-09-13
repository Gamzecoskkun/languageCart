package com.gamzecoskun.duocards.di

import android.content.Context
import com.gamzecoskun.duocards.data.WordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWordRepository(@ApplicationContext context: Context): WordRepository {
        return WordRepository(context)
    }
}