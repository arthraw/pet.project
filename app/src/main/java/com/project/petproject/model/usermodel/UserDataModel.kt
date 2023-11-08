package com.project.petproject.model.usermodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserData(
    @PrimaryKey val email: String,
    val number: String,
    val name: String,
)

class InvalidNoteException(message: String): Exception(message)