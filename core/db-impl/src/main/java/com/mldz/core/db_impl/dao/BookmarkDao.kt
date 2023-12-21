package com.mldz.core.db_impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mldz.core.db_impl.entity.Bookmark
import kotlinx.coroutines.flow.Flow


@Dao
interface BookmarkDao {

    @Query("SELECT * FROM bookmark")
    fun getAll(): Flow<Bookmark>

    @Query("SELECT * FROM bookmark WHERE id = :id")
    suspend fun getById(id: String): Bookmark?

    @Insert
    suspend fun insert(bookmark: Bookmark): Long

    @Query("DELETE FROM bookmark WHERE id = :id")
    suspend fun delete(id: String): Int

}