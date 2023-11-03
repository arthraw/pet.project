package com.project.petproject.presentation.add_edit_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditUserViewModelFactory @Inject constructor(
//    private val usersUseCases: UsersUseCases
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddEditUserViewModel::class.java)) {
            return AddEditUserViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
