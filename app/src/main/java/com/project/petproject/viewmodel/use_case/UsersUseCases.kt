package com.project.petproject.viewmodel.use_case

import com.project.petproject.viewmodel.repository.deleteUser
import com.project.petproject.viewmodel.repository.getUsers
import com.project.petproject.viewmodel.repository.insertUser
import com.project.petproject.viewmodel.repository.updateUser
import com.project.petproject.viewmodel.use_case.util.GetRequest


data class UsersUseCases(
    val getRequest: GetRequest,
    val deleteUser: deleteUser,
    val insertUser: insertUser,
    val updateUser: updateUser,
    val getUser: getUsers
)