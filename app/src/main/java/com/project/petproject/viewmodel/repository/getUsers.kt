package com.project.petproject.viewmodel.repository

import com.project.petproject.viewmodel.repository.userRepository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class getUsers(
    private val repository: UserRepository
) {
    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        repository.getUsers()
    }
}