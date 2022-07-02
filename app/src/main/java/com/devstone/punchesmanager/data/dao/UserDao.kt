package com.devstone.punchesmanager.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.devstone.punchesmanager.data.entities.ToolUser

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE username =:username AND password =:password")
    suspend fun getUser(username: String, password: String): ToolUser

    @Query("SELECT username FROM users WHERE username =:username")
    suspend fun getUserWithUsernameForProfile(username: String): String
}