package com.codegalaxy.testingpracticeimpl.model.di

import android.content.Context
import androidx.room.Room
import com.codegalaxy.testingpracticeimpl.model.network.ApiService
import com.codegalaxy.testingpracticeimpl.model.repository.IUserRepository
import com.codegalaxy.testingpracticeimpl.model.dao.UserDao
import com.codegalaxy.testingpracticeimpl.model.database.UserDatabase
import com.codegalaxy.testingpracticeimpl.model.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "user_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: UserDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(apiService: ApiService, userDao: UserDao): IUserRepository {
        return UserRepository(apiService, userDao)
    }
}