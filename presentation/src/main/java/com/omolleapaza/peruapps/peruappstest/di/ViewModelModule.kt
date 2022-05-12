package com.omolleapaza.peruapps.peruappstest.di

import com.omolleapaza.peruapps.peruappstest.ui.features.list_post.ListPostViewModel
import com.omolleapaza.peruapps.peruappstest.ui.features.main.MainViewModel
import com.omolleapaza.peruapps.peruappstest.ui.features.post_detail.PostDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ListPostViewModel(get(), get()) }
    viewModel { MainViewModel() }
    viewModel { PostDetailViewModel() }


}