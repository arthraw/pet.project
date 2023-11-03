package com.project.petproject.viewmodel.use_case.util

import com.project.petproject.model.usermodel.UserData
import com.project.petproject.viewmodel.repository.userRepository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton

@Singleton
class GetRequest(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        request: AcceptanceRequest = AcceptanceRequest.NotSend(AcceptanceRequestType.isNotSend)
    ): Flow<List<UserData>> {
        return repository.getUsers().map { users ->
            when(request.requestType) {
                is AcceptanceRequestType.isAccepted -> {
                    when(request) {
                        is AcceptanceRequest.Send -> users.filter { !it.itRequestAccept }
                        is AcceptanceRequest.NotSend -> users.filter { it.itRequestAccept }
                    }
                }
                is AcceptanceRequestType.isNotAccepted -> {
                    when(request) {
                        is AcceptanceRequest.Send -> users.filter { it.itRequestAccept }
                        is AcceptanceRequest.NotSend -> users.filter { !it.itRequestAccept }
                    }
                }
                is AcceptanceRequestType.isSend -> {
                    when(request) {
                        is AcceptanceRequest.Send -> users.filter { !it.itRequestAccept }
                        is AcceptanceRequest.NotSend -> users.filter { it.itRequestAccept }
                    }
                }
                is AcceptanceRequestType.isNotSend -> {
                    when(request) {
                        is AcceptanceRequest.Send -> users.filter { it.itRequestAccept }
                        is AcceptanceRequest.NotSend -> users.filter { !it.itRequestAccept }
                    }
                }
            }
        }
    }

}