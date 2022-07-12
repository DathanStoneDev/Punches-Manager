package com.devstone.punchesmanager.di

import android.app.Application
import androidx.room.Room
import com.devstone.punchesmanager.data.AppDatabase
import com.devstone.punchesmanager.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger Hilt dependency injection. Class contains only singletons.
 * Singletons include the Database and every repository.
 */
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
            .createFromAsset("database/punches_db.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideToolSetRepository(db: AppDatabase): ToolSetRepository {
        return ToolSetRepositoryImpl(db.toolSetDao())
    }

    @Provides
    @Singleton
    fun provideProductRepository(db: AppDatabase): ProductRepository {
        return ProductRepositoryImpl(db.productDao())
    }

    @Provides
    @Singleton
    fun provideRecordRepository(db: AppDatabase):RecordRepository {
        return RecordRepositoryImpl(db.recordDao())
    }

    @Provides
    @Singleton
    fun provideUserRepository(db: AppDatabase): UserRepository {
        return UserRepositoryImpl(db.userDao())
    }
}