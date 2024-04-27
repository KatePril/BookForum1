package com.example.bookforum.ui.databaseUi.userUI.viewModels.utils

import android.util.Log
import com.example.bookforum.data.entities.User
import com.example.bookforum.ui.databaseUi.userUI.states.UserDetails

class UserDetailsValidator {
    fun isUsernameUnique(userDetails: UserDetails,  usersList: List<User>): Boolean {
        return with(userDetails) {
            var isUnique = username.isNotBlank()

            for (user in usersList) {
                Log.i("USERNAME", user.username)
                if (user.username == username) {
                    isUnique = false
                    break
                }
            }

            isUnique
        }
    }

    fun isPasswordValid(userDetails: UserDetails): Boolean {
        return with(userDetails) {
            password.isNotBlank()
        }
    }

    fun isEmailValid(userDetails: UserDetails): Boolean {
        val emailRegex = Regex("""^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$""")
        return with(userDetails) {
            emailRegex.matches(email)
        }
    }
}