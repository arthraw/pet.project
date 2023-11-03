package com.project.petproject.presentation.add_edit_user

sealed class AddEditUserEvent {
    data class EnteredName(val name: String): AddEditUserEvent()
    data class EnteredEmail(val email: String): AddEditUserEvent()
    data class EnteredPhone(val phone: String): AddEditUserEvent()
    data class AboutUser(val content: String): AddEditUserEvent()
    object SendForm: AddEditUserEvent()
}