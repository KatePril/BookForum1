package com.example.bookforum.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bookforum.data.daos.PostDao
import com.example.bookforum.data.daos.CommentDao
import com.example.bookforum.data.daos.LikedPostDao
import com.example.bookforum.data.daos.UserDao
import com.example.bookforum.data.entities.Post
import com.example.bookforum.data.entities.Comment
import com.example.bookforum.data.entities.LikedPost
import com.example.bookforum.data.entities.User

@Database(
    entities = [Post::class, User::class, Comment::class, LikedPost::class],
    version = 1,
    exportSchema = false
)
abstract class ForumDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun bookDao(): PostDao
    abstract fun commentDao(): CommentDao
    abstract fun likedPostDao(): LikedPostDao

    companion object {
        @Volatile
        private var Instance: ForumDatabase? = null

        fun getDatabase(context: Context): ForumDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ForumDatabase::class.java, "forum_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}