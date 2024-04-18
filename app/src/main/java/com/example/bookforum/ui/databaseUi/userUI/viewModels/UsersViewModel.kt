package com.example.bookforum.ui.databaseUi.userUI.viewModels

import androidx.lifecycle.ViewModel
import com.example.bookforum.data.repositories.UsersRepository

class UsersViewModel(private val usersRepository: UsersRepository): ViewModel() {
    val userRegistrationViewModel = UserRegistrationViewModel(usersRepository)
    val userLoginViewModel = UserLoginViewModel(usersRepository)
}