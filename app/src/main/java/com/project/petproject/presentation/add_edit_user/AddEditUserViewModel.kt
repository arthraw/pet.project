package com.project.petproject.presentation.add_edit_user

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.petproject.model.usermodel.InvalidNoteException
import com.project.petproject.model.usermodel.UserData
import com.project.petproject.viewmodel.use_case.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


@HiltViewModel
class AddEditUserViewModel(
    savedStateHandle: SavedStateHandle = SavedStateHandle()
) : ViewModel() {

    private lateinit var usersUseCases: UsersUseCases

    fun initUseCases() {
        usersUseCases = UsersUseCases(
            getRequest = usersUseCases.getRequest,
            deleteUser = usersUseCases.deleteUser,
            insertUser = usersUseCases.insertUser,
            updateUser = usersUseCases.updateUser,
            getUser = usersUseCases.getUser
        )
    }


    init {
        savedStateHandle.get<String>("email")?.let { userEmail ->
            if (userEmail != "") {
                viewModelScope.launch {
                    usersUseCases.getUser().also {
                        _userName.value = userName.value.copy(
                            text = userName.toString(),
                            isHintVisible = false
                        )
                        _userEmail.value = _userEmail.value.copy(
                            text = userEmail,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }


    private val _userName = mutableStateOf(
        UserTextFieldState(
            hint = "Escreva seu nome."
        )
    )
    val userName: State<UserTextFieldState> = _userName

    private val _userEmail = mutableStateOf(
        UserTextFieldState(
            hint = "Escreva seu email."
        )
    )
    val userEmail: State<UserTextFieldState> = _userEmail

    private val _userPhone = mutableStateOf(
        (UserTextFieldState(
            hint = "Escreva um numero de telefone para contato."
        ))
    )
    val userPhone: State<UserTextFieldState> = _userPhone

    private val _userAbout = mutableStateOf(
        (UserTextFieldState(
            hint = "Escreva um pequeno texto contando sobre você."
        ))
    )
    val userAbout: State<UserTextFieldState> = _userAbout

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    fun onEvent(event: AddEditUserEvent) {
        when (event) {
            is AddEditUserEvent.EnteredName -> {
                _userName.value = userName.value.copy(
                    text = event.name
                )
            }

            is AddEditUserEvent.EnteredEmail -> {
                _userEmail.value = userEmail.value.copy(
                    text = event.email
                )
            }

            is AddEditUserEvent.EnteredPhone -> {
                _userPhone.value = userPhone.value.copy(
                    text = event.phone
                )
            }

            is AddEditUserEvent.AboutUser -> {
                _userAbout.value = userAbout.value.copy(
                    text = event.content
                )
            }

            is AddEditUserEvent.SendForm -> {
                viewModelScope.launch {
                    try {
                        usersUseCases.insertUser(
                            UserData(
                                email = userEmail.value.text,
                                number = userPhone.value.text,
                                name = userName.value.text,
                                itRequestAccept = false
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveUser)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowMensage(
                                message = e.message ?: "Não foi possível criar este usuário."
                            )
                        )
                    }
                }
            }

            else -> {}
        }
    }

    sealed class UiEvent {
        data class ShowMensage(val message: String) : UiEvent()
        object SaveUser : UiEvent()
    }
}
