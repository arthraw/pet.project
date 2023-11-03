package com.project.petproject.viewmodel.use_case.util

sealed class AcceptanceRequestType {
    object isAccepted: AcceptanceRequestType()
    object isSend: AcceptanceRequestType()
    object isNotSend: AcceptanceRequestType()
    object isNotAccepted: AcceptanceRequestType()
}
