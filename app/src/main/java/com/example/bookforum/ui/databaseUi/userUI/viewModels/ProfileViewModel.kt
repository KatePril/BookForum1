package com.example.bookforum.ui.databaseUi.userUI.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.entities.toDetails
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.userUI.screens.profile.ProfileDestination
import com.example.bookforum.ui.databaseUi.userUI.states.AllUsersUIState
import com.example.bookforum.ui.databaseUi.userUI.states.UserDetails
import com.example.bookforum.ui.databaseUi.userUI.states.UserRegistrationUIState
import com.example.bookforum.ui.databaseUi.userUI.states.UserValidationDetails
import com.example.bookforum.ui.databaseUi.userUI.states.toUser
import com.example.bookforum.ui.databaseUi.userUI.viewModels.utils.UserDetailsValidator
import com.example.bookforum.utils.TIMEOUT_MILLS
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
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

    private var usersUIState by mutableStateOf(AllUsersUIState())

//    : StateFlow<AllUsersUIState> =


    private val userId: Int = checkNotNull(savedStateHandle[ProfileDestination.userIdArg])

    init {
        viewModelScope.launch {
            registrationUIState = usersRepository
                .getUserById(userId)
                .filterNotNull()
                .map {
                    UserRegistrationUIState(
                        userDetails = it.toDetails(),
                        userValidationDetails = UserValidationDetails(
                            isUsernameValid = true,
                            isPasswordValid = true,
                            isEmailValid = true
                        ),
                        usersList = usersUIState.usernameList
                    )
                }
                .stateIn(
                    scope = viewModelScope
                ).value
            usersUIState = usersRepository.getAllUsernames()
                .map { AllUsersUIState(it) }
                .stateIn(
                    scope = viewModelScope
                ).value
        }
    }

    fun updateUiState(userDetails: UserDetails) {
        Log.i("USERNAMES_LIST", usersUIState.usernameList.toString())
        registrationUIState =
            UserRegistrationUIState(
                userDetails = userDetails,
                userValidationDetails = UserValidationDetails(
                    isUsernameValid = userDetailsValidator.isUsernameUnique(userDetails, usersUIState.usernameList),
                    isPasswordValid = userDetailsValidator.isPasswordValid(userDetails),
                    isEmailValid = userDetailsValidator.isEmailValid(userDetails)
                ),
                usersList = usersUIState.usernameList
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