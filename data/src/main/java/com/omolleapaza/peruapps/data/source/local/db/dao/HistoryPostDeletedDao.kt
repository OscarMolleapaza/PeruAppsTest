package com.omolleapaza.peruapps.data.source.local.db.dao

import androidx.room.*
import com.omolleapaza.peruapps.data.source.local.db.base.BaseDao
import com.omolleapaza.peruapps.data.source.local.db.entity.PostDeletedEntity


@Dao
interface HistoryPostDeletedDao : BaseDao<PostDeletedEntity> {

    @Query("SELECT EXISTS(SELECT * FROM post_deleted_table WHERE id = :id)")
    suspend fun isPostDeleted(id:String):Boolean

    @Query("DELETE FROM post_deleted_table WHERE idPrimary IN  (SELECT idPrimary from post_deleted_table ORDER BY idPrimary DESC LIMIT 10)")
    suspend fun removeOldData()

}