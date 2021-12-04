package com.oguzdogdu.recipes.dependencyinjection

import com.oguzdogdu.recipes.remote.RecipeInterface
import com.oguzdogdu.recipes.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModules {

    @Singleton
    @Provides
    fun provideRetrofit(): RecipeInterface {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(RecipeInterface::class.java)
    }

}