package com.omolleapaza.peruapps.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.omolleapaza.peruapps.data.source.local.db.dao.HistoryPostDeletedDao
import com.omolleapaza.peruapps.data.source.local.db.dao.PostDao
import com.omolleapaza.peruapps.data.source.local.db.entity.PostDeletedEntity
import com.omolleapaza.peruapps.data.source.local.db.entity.PostEntity


@Database(
    entities = [
        PostEntity::class,
        PostDeletedEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class PeruAppsDataBase : RoomDatabase() {

    abstract fun getPostDao(): PostDao

    abstract fun getPostDeletedDao(): HistoryPostDeletedDao

}