package com.project.petproject.viewmodel.repository

import com.project.petproject.model.usermodel.UserData
import com.project.petproject.viewmodel.repository.userRepository.UserRepository

class updateUser(
    private val repository: UserRepository
) {
    suspend operator fun invoke(userData: UserData) {
        repository.updateUsers(userData)
    }
}