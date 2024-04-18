package com.example.bookforum.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bookforum.data.daos.BookDao
import com.example.bookforum.data.daos.CommentDao
import com.example.bookforum.data.daos.LikedBookDao
import com.example.bookforum.data.daos.UserDao
import com.example.bookforum.data.entities.Book
import com.example.bookforum.data.entities.Comment
import com.example.bookforum.data.entities.LikedBook
import com.example.bookforum.data.entities.User

@Database(
    entities = [Book::class, User::class, Comment::class, LikedBook::class],
    version = 1,
    exportSchema = false
)
abstract class ForumDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun bookDao(): BookDao
    abstract fun commentDao(): CommentDao
    abstract fun likedBookDao(): LikedBookDao

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