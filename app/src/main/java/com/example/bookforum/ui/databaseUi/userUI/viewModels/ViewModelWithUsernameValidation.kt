package com.example.bookforum.ui.databaseUi.userUI.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.entities.User
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.userUI.states.UserDetails
import com.example.bookforum.ui.databaseUi.userUI.states.UserRegistrationUIState
import com.example.bookforum.ui.databaseUi.userUI.states.UserValidationDetails
import com.example.bookforum.ui.databaseUi.userUI.viewModels.utils.UserDetailsValidator
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

open class ViewModelWithUsernameValidation(private val usersRepository: UsersRepository) : ViewModel() {
    protected val userDetailsValidator = UserDetailsValidator()

    protected var usersList by mutableStateOf(emptyList<User>())

    var registrationUIState by mutableStateOf(UserRegistrationUIState())
        protected set

    init {
        viewModelScope.launch {
            usersList = usersRepository.getAllUsernames()
                .stateIn(
                    scope = viewModelScope
                ).value
        }
    }

    fun updateUiState(userDetails: UserDetails) {
        val isUsernameValid = userDetailsValidator.isUsernameUnique(userDetails, usersList)
        val isPasswordValid = userDetailsValidator.isPasswordValid(userDetails)
        val isEmailValid = userDetailsValidator.isEmailValid(userDetails)

        registrationUIState =
            UserRegistrationUIState(
                userDetails = userDetails,
                userValidationDetails = UserValidationDetails(
                    isUsernameValid = isUsernameValid,
                    isPasswordValid = isPasswordValid,
                    isEmailValid = isEmailValid,
                    areInputsValid = isUsernameValid && isPasswordValid && isEmailValid
                )
            )
    }

}