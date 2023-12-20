package com.project.petproject.viewmodel.repository

import com.project.petproject.model.usermodel.UserData
import com.project.petproject.viewmodel.repository.userRepository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class deleteUser(
    private val repository: UserRepository
) {
    suspend operator fun invoke(userData: UserData) = withContext(Dispatchers.IO) {
        repository.deleteUsers(userData)
    }
}