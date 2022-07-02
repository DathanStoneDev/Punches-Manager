package com.devstone.punchesmanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class ToolUser(
    @PrimaryKey
    val username: String,
    val password: String,
)
