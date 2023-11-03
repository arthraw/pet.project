package com.project.petproject.viewmodel.repository

import com.project.petproject.model.usermodel.InvalidNoteException
import com.project.petproject.model.usermodel.UserData
import com.project.petproject.viewmodel.repository.userRepository.UserRepository

class insertUser(
    private val repository: UserRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(userData: UserData) {
        if(userData.name.isBlank()) {
            throw InvalidNoteException("O nome não pode estar vazio.")
        }
        if(userData.email.isBlank()) {
            throw InvalidNoteException("O email não pode estar vazio.")
        }
        if(userData.number.isBlank()) {
            throw InvalidNoteException("O número de telefone não pode estar vazio.")
        }
        repository.insertUser(userData)
    }

}