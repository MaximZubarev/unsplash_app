package com.mldz.core.db_impl.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Bookmark(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "bookmark") val bookmark: Boolean = true
)
