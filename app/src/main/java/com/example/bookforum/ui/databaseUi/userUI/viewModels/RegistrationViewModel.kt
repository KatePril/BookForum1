package com.example.bookforum.ui.databaseUi.userUI.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.userUI.states.toUser
import com.example.bookforum.utils.hashPassword

class RegistrationViewModel(
    private val usersRepository: UsersRepository
) : ViewModelWithUsernameValidation(usersRepository) {

    var userId by mutableIntStateOf(0)

    suspend fun saveUser() {
        if (registrationUIState.userValidationDetails.areInputsValid) {
            if (userDetailsValidator.isUsernameUnique(registrationUIState.userDetails, usersList)) {
                userId = usersRepository.insertUser(
                    registrationUIState.userDetails.copy(
                        password = hashPassword(registrationUIState.userDetails.password)
                    ).toUser()
                ).toInt()
            }
        }
    }
}