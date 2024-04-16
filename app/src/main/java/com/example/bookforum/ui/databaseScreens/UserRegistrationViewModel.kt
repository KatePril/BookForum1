package com.example.bookforum.ui.databaseScreens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.entities.User
import com.example.bookforum.data.repositories.UsersRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UserRegistrationViewModel(private val usersRepository: UsersRepository) : ViewModel() {
    var userUIState by mutableStateOf(UserUIState())
    var usersUIState: StateFlow<AllUsersUIState> =
        usersRepository.getAllUsernames()
            .map { AllUsersUIState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = AllUsersUIState()
            )

    fun updateUiState(userDetails: UserDetails, usersList: List<User>) {
        userUIState =
            UserUIState(
                userDetails = userDetails,
                userValidationDetails = UserValidationDetails(
                    isUsernameValid = isUsernameUnique(userDetails),
                    isPasswordValid = isPasswordValid(userDetails),
                    isEmailValid = isEmailValid(userDetails)
                ),
                usersList = usersList
            )
    }

    suspend fun saveUser() {
        if (userUIState.userValidationDetails.areInputsValid) {
            usersRepository.insertUser(userUIState.userDetails.toUser())
        }
    }

    suspend fun deleteUserById(id: Int) {
        usersRepository.deleteUserById(id)
    }




    private fun isUsernameUnique(userDetails: UserDetails = userUIState.userDetails): Boolean {
        return with(userDetails) {
            var isUnique = username.isNotBlank()

            for (user in userUIState.usersList) {
                Log.i("USERNAME", user.username)
                if (user.username == username) {
                    isUnique = false
                    break
                }
            }

            isUnique
        }
    }

    private fun isPasswordValid(userDetails: UserDetails = userUIState.userDetails): Boolean {
        return with(userDetails) {
            password.isNotBlank()
        }
    }

    private fun isEmailValid(userDetails: UserDetails = userUIState.userDetails): Boolean {
        val emailRegex = Regex("""^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$""")
        return with(userDetails) {
            emailRegex.matches(email)
        }
    }

    private fun validateInputs(userDetails: UserDetails = userUIState.userDetails): Boolean {
        val emailRegex = Regex("""^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$""")
        return with(userDetails) {
            username.isNotBlank() && password.isNotBlank() && emailRegex.matches(email)
        }
    }
}