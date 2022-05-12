package com.omolleapaza.peruapps.peruappstest.di

import com.omolleapaza.peruapps.peruappstest.ui.features.list_post.paging.PostsPageDataSource
import com.omolleapaza.peruapps.peruappstest.ui.features.list_post.paging.PostsPageDataSourceFactory
import org.koin.dsl.module


val pagedListModule  = module {
    factory { PostsPageDataSource(get(), get() ) }
    factory { PostsPageDataSourceFactory(get(),get()) }
}