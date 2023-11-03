package com.project.petproject.viewmodel.repository

import com.project.petproject.model.usermodel.UserData
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun update(userData: UserData)

    suspend fun deleteUser(userData: UserData)

    suspend fun getUser() : Flow<List<UserData>>

    suspend fun insertUser(userData: UserData)

}