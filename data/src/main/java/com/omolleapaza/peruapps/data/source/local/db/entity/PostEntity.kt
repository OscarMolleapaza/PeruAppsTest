package com.omolleapaza.peruapps.data.source.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "post_table")
data class PostEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey val id: String,
    @ColumnInfo(name = "story_title")
    val storyTitle: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "story_url")
    val storyUrl: String,
    @ColumnInfo(name = "comment_text")
    val commentText: String,
    var visible: Boolean=true,

)
