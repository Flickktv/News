package com.example.newsfetcher.feature.bookmarks.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.newsfetcher.feature.bookmarks.data.local.model.BookmarkEntity
import com.example.newsfetcher.feature.bookmarks.di.BOOKMARKS_TABLE


@Dao

interface BookmarksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(entity: BookmarkEntity)
    @Query("SELECT * FROM $BOOKMARKS_TABLE")
    suspend fun read(): List<BookmarkEntity>
    @Update
    suspend fun update(entity: BookmarkEntity)
    @Delete
    suspend fun delete(entity: BookmarkEntity)
}