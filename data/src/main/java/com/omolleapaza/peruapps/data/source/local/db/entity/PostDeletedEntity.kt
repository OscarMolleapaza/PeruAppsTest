package com.omolleapaza.peruapps.data.source.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "post_deleted_table")
data class PostDeletedEntity(

    @ColumnInfo(name = "id")
     val id: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @PrimaryKey(autoGenerate = true) val idPrimary:Int=0
)
