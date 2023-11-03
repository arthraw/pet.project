package com.project.petproject.viewmodel.use_case.util

sealed class AcceptanceRequest(val requestType: AcceptanceRequestType) {
    class Send (requestType: AcceptanceRequestType): AcceptanceRequest(requestType)
    class NotSend (requestType: AcceptanceRequestType): AcceptanceRequest(requestType)
}
