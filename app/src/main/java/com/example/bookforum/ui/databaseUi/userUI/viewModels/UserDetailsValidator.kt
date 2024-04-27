package com.example.bookforum.ui.databaseUi.userUI.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.entities.User
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.userUI.states.AllUsersUIState
import com.example.bookforum.ui.databaseUi.userUI.states.UserDetails
import com.example.bookforum.ui.databaseUi.userUI.states.UserRegistrationUIState
import com.example.bookforum.ui.databaseUi.userUI.states.UserValidationDetails
import com.example.bookforum.utils.TIMEOUT_MILLS
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class UserDetailsValidator {
//    var  by mutableStateOf(UserRegistrationUIState())

//    var usersUIState: StateFlow<AllUsersUIState> =
//        usersRepository.getAllUsernames()
//            .map { AllUsersUIState(it) }
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLS),
//                initialValue = AllUsersUIState()
//            )
//
//    fun updateUiState(userDetails: UserDetails, usersList: List<User>) {
//        userRegistrationUIState =
//            UserRegistrationUIState(
//                userDetails = userDetails,
//                userValidationDetails = UserValidationDetails(
//                    isUsernameValid = isUsernameUnique(userDetails),
//                    isPasswordValid = isPasswordValid(userDetails),
//                    isEmailValid = isEmailValid(userDetails)
//                ),
//                usersList = usersList
//            )
//    }

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