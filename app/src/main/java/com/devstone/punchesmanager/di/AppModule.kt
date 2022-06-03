package com.devstone.punchesmanager.di

import android.app.Application
import androidx.room.Room
import com.devstone.punchesmanager.data.AppDatabase
import com.devstone.punchesmanager.data.repository.ToolSetRepository
import com.devstone.punchesmanager.data.repository.ToolSetRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME,
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideToolSetRepository(db: AppDatabase): ToolSetRepository {
        return ToolSetRepositoryImpl(db.toolSetDao())
    }
}