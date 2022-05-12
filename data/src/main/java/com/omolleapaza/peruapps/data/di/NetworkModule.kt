package com.omolleapaza.peruapps.data.di

import com.omolleapaza.peruapps.data.utils.ConnectionUtils
import com.omolleapaza.peruapps.data.utils.ConnectionUtilsImpl
import com.omolleapaza.peruapps.data.utils.RetrofitUtils
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext

val networkModule = module {
    single<ConnectionUtils> {
        ConnectionUtilsImpl(androidContext())
    }


    single { RetrofitUtils.provideOkHttpClient() }
    single { RetrofitUtils.provideApi(get()) }
    single { RetrofitUtils.provideRetrofit(get()) }
}



