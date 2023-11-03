package com.project.petproject.viewmodel.repository.userRepository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.project.petproject.model.usermodel.UserData
import kotlinx.coroutines.flow.Flow

@Dao
interface UserRepository {
    @Update
    suspend fun updateUsers(users: UserData)

    @Delete
    suspend fun deleteUsers(user: UserData)

    @Query("SELECT * FROM users")
    suspend fun getUsers(): Flow<List<UserData>>

    @Insert
    suspend fun insertUser(user: UserData)

}

