package com.example.bookforum.ui.databaseScreens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bookforum.data.repositories.UsersRepository

class UserRegistrationViewModel(private val usersRepository: UsersRepository) : ViewModel() {
    var userUIState by mutableStateOf(UserUIState())

    fun updateUiState(userDetails: UserDetails) {
        userUIState =
            UserUIState(userDetails = userDetails, isInputValid = validateInputs(userDetails))
    }

    suspend fun saveUser() {
        if (validateInputs()) {
            usersRepository.insertUser(userUIState.userDetails.toUser())
        }
    }
    private fun validateInputs(userDetails: UserDetails = userUIState.userDetails): Boolean {
        val emailRegex = Regex("""^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$""")
        return with(userDetails) {
            username.isNotBlank() && password.isNotBlank() && emailRegex.matches(email)
        }
    }
}