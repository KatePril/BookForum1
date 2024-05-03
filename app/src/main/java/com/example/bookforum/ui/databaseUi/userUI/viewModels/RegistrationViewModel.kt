package com.example.bookforum.ui.databaseUi.userUI.viewModels

import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.userUI.states.UserUiState
import com.example.bookforum.ui.databaseUi.userUI.states.toUser
import com.example.bookforum.ui.databaseUi.userUI.viewModels.utils.getUserUiStateByUsername
import com.example.bookforum.utils.hashPassword
import kotlinx.coroutines.flow.StateFlow

class RegistrationViewModel(
    private val usersRepository: UsersRepository
) : ViewModelWithUsernameValidation(usersRepository) {

    val getUserUiStateByUsername: (String) -> StateFlow<UserUiState?> = {
        getUserUiStateByUsername(
            username = it,
            usersRepository = usersRepository,
            coroutineScope = viewModelScope
        )
    }

    suspend fun saveUser() {
        if (registrationUIState.userValidationDetails.areInputsValid) {
            if (userDetailsValidator.isUsernameUnique(registrationUIState.userDetails, usersListState.usersList)) {
                usersRepository.insertUser(
                    registrationUIState.userDetails.copy(
                        password = hashPassword(registrationUIState.userDetails.password)
                    ).toUser()
                )
            }
        }
    }
}