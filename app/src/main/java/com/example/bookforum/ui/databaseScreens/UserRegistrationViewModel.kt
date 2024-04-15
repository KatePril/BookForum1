package com.example.bookforum.ui.databaseScreens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bookforum.data.entities.User
import com.example.bookforum.data.repositories.UsersRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UserRegistrationViewModel(private val usersRepository: UsersRepository) : ViewModel() {
    var userUIState by mutableStateOf(UserUIState())

    fun updateUiState(userDetails: UserDetails) {
        userUIState =
            UserUIState(
                userDetails = userDetails,
                userValidationDetails = UserValidationDetails(
                    isUsernameValid = isUsernameUnique(userDetails),
                    isPasswordValid = isPasswordValid(userDetails),
                    isEmailValid = isEmailValid(userDetails)
                )
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



    @OptIn(DelicateCoroutinesApi::class)
    private fun isUsernameUnique(userDetails: UserDetails = userUIState.userDetails): Boolean {
        return with(userDetails) {
            var isUnique = username.isNotBlank()
            Log.i("USERNAME", "came")
            runBlocking {
                GlobalScope.launch {
                    Log.i("USERNAME", "came 2")
                    val users = usersRepository.getAllUsernames()
                    Log.i("USERNAME", "came 3:${users.toList()}")
                    /*TODO error is caused by .toList(). FIX IT*/
                    for (userUsername in users.toList()) {
                        Log.i("USERNAME", userUsername.toString())
                        if (userUsername.toString() == username) {
                            isUnique = false
                            break
                        }
                    }
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