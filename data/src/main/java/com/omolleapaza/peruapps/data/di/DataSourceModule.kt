package com.omolleapaza.peruapps.data.di

import com.omolleapaza.peruapps.data.source.local.ds.PostLocalDataSource
import com.omolleapaza.peruapps.data.source.local.ds.PostLocalDataSourceImpl
import com.omolleapaza.peruapps.data.source.remote.ds.PostRemoteDataSource
import com.omolleapaza.peruapps.data.source.remote.ds.PostRemoteDataSourceImpl
import org.koin.dsl.module



val dataSourceModule =  module{
    //Post
    single<PostRemoteDataSource> { PostRemoteDataSourceImpl(get(), get()) }
    single<PostLocalDataSource> { PostLocalDataSourceImpl(get(), get()) }


}