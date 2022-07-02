package com.devstone.punchesmanager.data.repository

import com.devstone.punchesmanager.data.dao.UserDao
import com.devstone.punchesmanager.data.entities.ToolUser

class UserRepositoryImpl (
    private val dao: UserDao
        ): UserRepository {
    override suspend fun getUser(username: String, password: String): ToolUser {
        return dao.getUser(username, password)
    }

    override suspend fun getUserForProfile(username: String): String {
        return dao.getUserWithUsernameForProfile(username)
    }
}