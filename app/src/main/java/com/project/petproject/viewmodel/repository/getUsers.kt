package com.project.petproject.viewmodel.repository

import com.project.petproject.viewmodel.repository.userRepository.UserRepository

class getUsers(
    private val repository: UserRepository
) {
    suspend operator fun invoke() {
        repository.getUsers()
    }
}