package com.example.bookforum.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bookforum.ui.databaseUi.groupUi.states.GroupDetails

@Entity(tableName = "groups")
data class Group (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String
)

fun Group.toDetails(): GroupDetails = GroupDetails(
    id = id,
    title = title
)