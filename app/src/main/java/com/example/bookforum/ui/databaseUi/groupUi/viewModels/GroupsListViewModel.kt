package com.example.bookforum.ui.databaseUi.groupUi.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.entities.Group
import com.example.bookforum.data.repositories.GroupsRepository
import com.example.bookforum.ui.navigation.destinations.groupDestination.GroupsListDestination
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GroupsListViewModel(
    savedStateHandle: SavedStateHandle,
    private val groupsRepository: GroupsRepository
): ViewModel() {

    val userId: Int = checkNotNull(savedStateHandle[GroupsListDestination.userIdArg])

    var groupsList = emptyList<Group>()

    init {
        viewModelScope.launch {
            groupsList = groupsRepository
                .getGroupsByUser(userId)
                .filterNotNull()
                .stateIn(scope = viewModelScope)
                .value
        }
    }

}