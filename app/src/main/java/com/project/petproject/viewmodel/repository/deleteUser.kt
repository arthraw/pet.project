package com.project.petproject.viewmodel.repository

import com.project.petproject.model.usermodel.UserData
import com.project.petproject.viewmodel.repository.userRepository.UserRepository

class deleteUser(
    private val repository: UserRepository
) {
    suspend operator fun invoke(userData: UserData) {
        repository.deleteUsers(userData)
    }
}