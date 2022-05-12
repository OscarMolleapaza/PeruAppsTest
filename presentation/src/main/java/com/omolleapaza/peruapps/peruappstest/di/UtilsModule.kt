package com.omolleapaza.peruapps.peruappstest.di

import com.omolleapaza.peruapps.peruappstest.utils_class.ConnectionLiveData
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module



val utilsModule = module{
    factory { ConnectionLiveData(androidContext()) }
}