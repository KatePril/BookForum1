package com.example.bookforum.data

import android.content.Context
import com.example.bookforum.data.repositories.PostsRepository
import com.example.bookforum.data.repositories.CommentsRepository
import com.example.bookforum.data.repositories.GroupMembersRepository
import com.example.bookforum.data.repositories.GroupMessageRepository
import com.example.bookforum.data.repositories.GroupsRepository
import com.example.bookforum.data.repositories.LikedPostsRepository
import com.example.bookforum.data.repositories.MessagesRepository
import com.example.bookforum.data.repositories.UsersRepository

class ForumDataContainer(private val context: Context): ForumContainer {
    override val usersRepository: UsersRepository by lazy {
        UsersRepository(ForumDatabase.getDatabase(context).userDao())
    }
    override val postsRepository: PostsRepository by lazy {
        PostsRepository(ForumDatabase.getDatabase(context).postDao())
    }
    override val commentsRepository: CommentsRepository by lazy {
        CommentsRepository(ForumDatabase.getDatabase(context).commentDao())
    }
    override val likedPostsRepository: LikedPostsRepository by lazy {
        LikedPostsRepository(ForumDatabase.getDatabase(context).likedPostDao())
    }
    override val messagesRepository: MessagesRepository by  lazy {
        MessagesRepository(ForumDatabase.getDatabase(context).messageDao())
    }
    override val groupsRepository: GroupsRepository by lazy {
        GroupsRepository(ForumDatabase.getDatabase(context).groupDao())
    }
    override val groupMembersRepository: GroupMembersRepository by lazy {
        GroupMembersRepository(ForumDatabase.getDatabase(context).groupMemberDao())
    }
    override val groupMessageRepository: GroupMessageRepository by lazy {
        GroupMessageRepository(ForumDatabase.getDatabase(context).groupMessageDao())
    }
}