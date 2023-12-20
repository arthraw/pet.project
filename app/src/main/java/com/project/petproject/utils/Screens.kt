package com.project.petproject.utils

sealed class Screens(val route : String) {
    object WelcomeScreen : Screens("welcome")
    object FormsScreen : Screens("form")
    object ContactScreen : Screens("contact")
    object DescriptionScreen : Screens("description")

    fun withArgs(vararg args : String?): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}