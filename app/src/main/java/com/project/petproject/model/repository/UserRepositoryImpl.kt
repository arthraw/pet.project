package com.project.petproject.model.repository

import com.project.petproject.model.usermodel.UserData
import com.project.petproject.viewmodel.repository.userRepository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val dao: UserRepository
): UserRepository {

    override suspend fun updateUsers(users: UserData) {
        return dao.updateUsers(users)
    }

    override suspend fun deleteUsers(user: UserData) {
        return dao.deleteUsers(user)
    }

    override suspend fun getUsers(): Flow<List<UserData>> {
        return dao.getUsers()
    }

    override suspend fun insertUser(user: UserData) {
        return dao.insertUser(user)
    }

}