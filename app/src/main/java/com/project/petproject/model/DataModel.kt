package com.project.petproject.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.petproject.model.usermodel.UserData
import com.project.petproject.viewmodel.repository.userRepository.UserRepository

@Database(
    version = 1,
    entities = [UserData::class]
)
abstract class DataModel: RoomDatabase() {
    abstract fun userDao(): UserRepository

    companion object {
        const val DATABASE_NAME = "maria_pets_db"
    }
}
