package com.omolleapaza.peruapps.peruappstest

import android.app.Application
import com.omolleapaza.peruapps.data.di.*
import com.omolleapaza.peruapps.domain.di.useCasesModule
import com.omolleapaza.peruapps.peruappstest.di.pagedListModule
import com.omolleapaza.peruapps.peruappstest.di.utilsModule
import com.omolleapaza.peruapps.peruappstest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class PeruAppsApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@PeruAppsApplication)

            modules(listOf(
                utilsModule,
                viewModelModule,
                migrationsModule,
                pagedListModule,
                useCasesModule,
                networkModule,
                localDataBaseModule,
                repositoryModule,
                dataSourceModule,
                mapperDataModule,
            ))
        }
    }
}