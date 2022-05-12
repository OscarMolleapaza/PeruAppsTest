package com.omolleapaza.peruapps.data.di

import com.omolleapaza.peruapps.data.constants.ConstantsDB
import com.omolleapaza.peruapps.data.utils.DataBaseUtils
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module


val localDataBaseModule =  module{
    single {
        DataBaseUtils.provideLocalDataBase(
            androidApplication(),
            get(named(ConstantsDB.GET_MIGRATIONS))
        )
    }
    //Dao
    single {
        DataBaseUtils.providesPostDeletedDao(get())
    }
    single {
        DataBaseUtils.providesPostDao(get())
    }
}