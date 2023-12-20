package com.project.petproject.viewmodel.add_edit_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditUserViewModelFactory @Inject constructor() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddEditUserViewModel::class.java)) {
            return AddEditUserViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}