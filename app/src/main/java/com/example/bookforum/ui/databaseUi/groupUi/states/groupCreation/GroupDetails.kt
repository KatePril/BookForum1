package com.example.bookforum.ui.databaseUi.groupUi.states.groupCreation

import com.example.bookforum.data.entities.Group

data class GroupDetails(
    val id: Int = 0,
    val title: String = ""
)
fun GroupDetails.toGroup(): Group = Group(
    id = id,
    title = title
)