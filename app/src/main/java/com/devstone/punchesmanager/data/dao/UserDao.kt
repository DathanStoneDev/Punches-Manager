package com.devstone.punchesmanager.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.devstone.punchesmanager.data.entities.ToolUser

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE username =:username AND password =:password")
    suspend fun getUser(username: String, password: String): ToolUser
}