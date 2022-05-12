package com.omolleapaza.peruapps.data.di

import com.omolleapaza.peruapps.data.mapper.ListPostMapper
import com.omolleapaza.peruapps.data.mapper.ListPostMapperImpl
import org.koin.dsl.module


val mapperDataModule = module {
    single<ListPostMapper> { ListPostMapperImpl() }
}