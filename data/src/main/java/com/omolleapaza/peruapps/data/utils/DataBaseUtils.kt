package com.omolleapaza.peruapps.data.utils

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import com.omolleapaza.peruapps.data.constants.ConstantsDB
import com.omolleapaza.peruapps.data.source.local.db.PeruAppsDataBase
import com.omolleapaza.peruapps.data.source.local.db.dao.HistoryPostDeletedDao
import com.omolleapaza.peruapps.data.source.local.db.dao.PostDao


object DataBaseUtils {

    fun provideLocalDataBase(applicationContext: Application, migrations: Set<Migration>): PeruAppsDataBase {
        return Room.databaseBuilder(applicationContext, PeruAppsDataBase::class.java, ConstantsDB.DATA_BASE_NAME)
            .addMigrations(*migrations.toTypedArray())
            .build()
    }

    fun providesPostDao(mistiDataBase: PeruAppsDataBase): PostDao{
        return mistiDataBase.getPostDao()
    }

    fun providesPostDeletedDao(mistiDataBase: PeruAppsDataBase): HistoryPostDeletedDao{
        return mistiDataBase.getPostDeletedDao()
    }


}