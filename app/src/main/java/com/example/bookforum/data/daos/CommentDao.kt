package com.example.bookforum.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bookforum.data.entities.Comment
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(comment: Comment) // tested

    @Update
    suspend fun update(comment: Comment) // tested

    @Delete
    suspend fun delete(comment: Comment) // tested

    @Query("SELECT * from comments WHERE book_id = :id ORDER BY id ASC")
    fun getBookComments(id: Int): Flow<List<Comment>> // tested

}