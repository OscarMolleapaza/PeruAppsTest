package com.omolleapaza.peruapps.peruappstest.ui.features.list_post.paging


import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.omolleapaza.peruapps.domain.model.Post
import com.omolleapaza.peruapps.domain.usecase.GetPostsUseCase
import com.omolleapaza.peruapps.peruappstest.ui.features.list_post.ListPostViewModel


class PostsPageDataSourceFactory(
    private val getUseCase: GetPostsUseCase,
    private val viewModel: ListPostViewModel
) : DataSource.Factory<Int, Post>() {

    var sourceLiveData = MutableLiveData<PostsPageDataSource>()

    override fun create(): DataSource<Int, Post> {
        val dataSource = PostsPageDataSource(getUseCase, viewModel)
        sourceLiveData.postValue(dataSource)
        return dataSource
    }

    fun refresh() {
        sourceLiveData.value?.invalidate()
    }

    fun retry() {
        sourceLiveData.value?.retry()
    }
}
