package com.omolleapaza.peruapps.data.di


import com.omolleapaza.peruapps.data.repository.ListPostRepositoryImpl
import com.omolleapaza.peruapps.domain.repository.ListPostRepository
import org.koin.dsl.module


val repositoryModule = module {
    single<ListPostRepository> { ListPostRepositoryImpl(get(), get(), get(), get()) }

}