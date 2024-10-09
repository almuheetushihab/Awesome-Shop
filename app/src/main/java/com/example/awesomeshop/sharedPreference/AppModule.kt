package com.example.awesomeshop.di

import android.app.Application
import android.content.Context
import com.example.awesomeshop.sharedPreference.SharedPreferenceHelper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    fun provideSharedPreferenceHelper(context: Context): SharedPreferenceHelper {
        return SharedPreferenceHelper(context)
    }
}
