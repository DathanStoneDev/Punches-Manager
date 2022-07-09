package com.devstone.punchesmanager.data.repository

import com.devstone.punchesmanager.data.entities.ToolUser

interface UserRepository {

    suspend fun getUser(username: String, password: String): ToolUser

    suspend fun getUserForProfile(username: String): String

    suspend fun getUserPasswordForVerification(username: String): String
}