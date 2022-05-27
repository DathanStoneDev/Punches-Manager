package com.devstone.punchesmanager.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devstone.punchesmanager.data.dao.ToolSetDao
import com.devstone.punchesmanager.data.entities.product.Product
import com.devstone.punchesmanager.data.entities.tool.ToolSet

@Database(entities = [ToolSet::class, Product::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val toolSetDao: ToolSetDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "tool_set_database"
                    )   //For the scope of this project, if the schema changes, erase database
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}