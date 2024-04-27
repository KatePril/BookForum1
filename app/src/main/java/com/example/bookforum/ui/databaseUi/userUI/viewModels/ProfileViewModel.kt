package com.example.bookforum.ui.databaseUi.userUI.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.entities.User
import com.example.bookforum.data.entities.toDetails
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.postsUI.screens.profile.ProfileDestination
import com.example.bookforum.ui.databaseUi.userUI.states.AllUsersUIState
import com.example.bookforum.ui.databaseUi.userUI.states.UserDetails
import com.example.bookforum.ui.databaseUi.userUI.states.UserRegistrationUIState
import com.example.bookforum.ui.databaseUi.userUI.states.UserValidationDetails
import com.example.bookforum.ui.databaseUi.userUI.states.toUser
import com.example.bookforum.ui.databaseUi.userUI.viewModels.utils.UserDetailsValidator
import com.example.bookforum.utils.TIMEOUT_MILLS
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel(
    savedStateHandle: SavedStateHandle,
    private val usersRepository: UsersRepository
) : ViewModel() {
    var registrationUIState by mutableStateOf(UserRegistrationUIState())

    private val userDetailsValidator = UserDetailsValidator()

    var usersListUIState: AllUsersUIState =
        usersRepository.getAllUsernames()
            .map { AllUsersUIState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLS),
                initialValue = AllUsersUIState()
            ).value


    private val userId: Int = checkNotNull(savedStateHandle[ProfileDestination.userIdArg])

    init {
        viewModelScope.launch {
            registrationUIState = usersRepository
                .getUserById(userId)
                .filterNotNull()
                .map { UserRegistrationUIState(it.toDetails()) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(TIMEOUT_MILLS),
                    initialValue = UserRegistrationUIState()
                ).value
        }
    }

    fun updateUiState(userDetails: UserDetails, usersList: List<User>) {
        registrationUIState =
            UserRegistrationUIState(
                userDetails = userDetails,
                userValidationDetails = UserValidationDetails(
                    isUsernameValid = userDetailsValidator.isUsernameUnique(userDetails, usersList),
                    isPasswordValid = userDetailsValidator.isPasswordValid(userDetails),
                    isEmailValid = userDetailsValidator.isEmailValid(userDetails)
                ),
                usersList = usersList
            )
    }

    suspend fun updateUser() {
        if (registrationUIState.userValidationDetails.areInputsValid) {
            if (userDetailsValidator.isUsernameUnique(registrationUIState.userDetails, registrationUIState.usersList)) {
                usersRepository.updateUser(registrationUIState.userDetails.toUser())
            }
        }
    }

    suspend fun deleteUser() {
        usersRepository.deleteUser(registrationUIState.userDetails.toUser())
    }

}