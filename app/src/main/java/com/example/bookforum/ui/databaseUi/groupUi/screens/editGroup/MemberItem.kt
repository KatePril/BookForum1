package com.example.bookforum.ui.databaseUi.groupUi.screens.editGroup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddModerator
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.bookforum.R
import com.example.bookforum.data.daos.MemberRight
import com.example.bookforum.data.entities.GroupMember
import com.example.bookforum.ui.screenParts.buttons.ButtonWithIcon
import com.example.bookforum.ui.screenParts.buttons.FilledButtonWithIcon

@Composable
internal fun MemberItem (
    username: String,
    currentUserRights: Int,
    memberRights: Int,
    deleteMember: () -> Unit,
    updateRights: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(R.dimen.default_elevation)
        ),
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = username,
                modifier = modifier.padding(dimensionResource(R.dimen.padding_large)),
                style = MaterialTheme.typography.labelLarge
            )
            if (currentUserRights != MemberRight.NOT_ADMIN.value) {
                Row {
                    if (currentUserRights == MemberRight.OWNER.value) {
                        FilledButtonWithIcon(
                            imageVector = Icons.Filled.AddModerator,
                            onClick = updateRights,
                            iconButtonColors = IconButtonDefaults.iconButtonColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                contentColor = MaterialTheme.colorScheme.secondary
                            ),
                            modifier = modifier
                        )
                    }
                    if (currentUserRights > memberRights) {
                        FilledButtonWithIcon(
                            imageVector = Icons.Filled.ExitToApp,
                            onClick = deleteMember,
                            iconButtonColors = IconButtonDefaults.iconButtonColors(
                                containerColor = MaterialTheme.colorScheme.errorContainer,
                                contentColor = MaterialTheme.colorScheme.error
                            ),
                            modifier = modifier
                        )
                    }
                }
            }
        }
    }
}