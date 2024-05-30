package com.example.bookforum.ui.databaseUi.userUI.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.userUI.states.toUser
import com.example.bookforum.utils.generateRandomSalt
import com.example.bookforum.utils.hashPassword

class RegistrationViewModel(
    private val usersRepository: UsersRepository
) : ViewModelWithUsernameValidation(usersRepository) {

    var userId by mutableIntStateOf(0)
        private set

    suspend fun saveUser() {
        if (registrationUIState.userValidationDetails.areInputsValid) {
            if (userDetailsValidator.isUsernameUnique(registrationUIState.userDetails, usersList)) {
                val salt = generateRandomSalt()
                userId = usersRepository.insertUser(
                    registrationUIState.userDetails.copy(
                        password = hashPassword(
                            password = registrationUIState.userDetails.password,
                            salt = salt
                        ),
                        salt = salt
                    ).toUser()
                ).toInt()
            }
        }
    }
}