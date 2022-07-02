package com.devstone.punchesmanager.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devstone.punchesmanager.data.dao.ProductDao
import com.devstone.punchesmanager.data.dao.RecordDao
import com.devstone.punchesmanager.data.dao.ToolSetDao
import com.devstone.punchesmanager.data.dao.UserDao
import com.devstone.punchesmanager.data.entities.Product
import com.devstone.punchesmanager.data.entities.ToolRecord
import com.devstone.punchesmanager.data.entities.ToolSet
import com.devstone.punchesmanager.data.entities.ToolUser

@Database(entities = [ToolSet::class, Product::class, ToolRecord::class, ToolUser::class], version = 11, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun toolSetDao(): ToolSetDao
    abstract fun productDao(): ProductDao
    abstract fun recordDao(): RecordDao
    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "punches_db"
    }

}