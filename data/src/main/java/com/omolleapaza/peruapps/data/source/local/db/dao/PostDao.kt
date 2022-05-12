package com.omolleapaza.peruapps.data.source.local.db.dao

import androidx.room.*
import com.omolleapaza.peruapps.data.source.local.db.base.BaseDao
import com.omolleapaza.peruapps.data.source.local.db.entity.PostEntity


@Dao
interface PostDao : BaseDao<PostEntity> {

    @Query("SELECT * from post_table")
    suspend fun getAllPost(): List<PostEntity>

    @Query("DELETE FROM post_table")
    suspend fun deleteAll()

}