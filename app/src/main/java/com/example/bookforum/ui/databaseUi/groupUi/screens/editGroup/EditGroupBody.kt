package com.example.bookforum.ui.databaseUi.groupUi.screens.editGroup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.bookforum.R
import com.example.bookforum.data.entities.GroupMember
import com.example.bookforum.data.entities.User
import com.example.bookforum.ui.screenParts.EmptyListMsg
import kotlinx.coroutines.launch

@Composable
fun EditGroupBody(
    membersList: List<GroupMember>,
    currentUserRights: Int,
    getUser: (Int) -> Unit,
    deleteMember: (GroupMember) -> Unit,
    updateRights: (GroupMember) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(contentPadding)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(dimensionResource(R.dimen.padding_large)),
        ) {
            if (membersList.isEmpty()) {
                EmptyListMsg(msgId = R.string.no_members_msg)
            } else {

            }
        }
    }
}

@Composable
private fun GroupMembersList(
    membersList: List<GroupMember>,
    currentUserRights: Int,
    getUser: (Int) -> User?,
    deleteMember: (GroupMember) -> Unit,
    updateRights: (GroupMember) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
        modifier = modifier
    ) {
        items(membersList) { groupMember ->
            val member = getUser(groupMember.userId)
            if (member != null) {
                MemberItem(
                    username = member.username,
                    currentUserRights = currentUserRights,
                    memberRights = groupMember.isAdmin,
                    deleteMember = {
                        deleteMember(groupMember)
                    },
                    updateRights = {
                        updateRights(groupMember)
                    }
                )
            }
        }
    }
}