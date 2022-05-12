package com.omolleapaza.peruapps.peruappstest.ui.features.list_post


import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import com.omolleapaza.peruapps.domain.model.Post
import com.omolleapaza.peruapps.domain.usecase.DeletePostUseCase
import com.omolleapaza.peruapps.domain.usecase.GetPostsUseCase
import com.omolleapaza.peruapps.peruappstest.ui.base.BaseViewModel
import com.omolleapaza.peruapps.peruappstest.ui.features.list_post.paging.PostsPageDataSource.Companion.PAGE_MAX_ELEMENTS
import com.omolleapaza.peruapps.peruappstest.ui.features.list_post.paging.PostsPageDataSourceFactory
import com.omolleapaza.peruapps.peruappstest.ui.features.list_post.state.NetworkState



class ListPostViewModel(
    getPostsUseCase: GetPostsUseCase,
    private val deletePost: DeletePostUseCase,
) : BaseViewModel() {
    private val dataSourceFactory: PostsPageDataSourceFactory =
        PostsPageDataSourceFactory(getPostsUseCase, this)

    private val networkState = Transformations.switchMap(dataSourceFactory.sourceLiveData) {
        it.networkState
    }

    val data = LivePagedListBuilder(
        dataSourceFactory,
        PAGE_MAX_ELEMENTS
    ).build()

    var actualPage = 0
    var saveData = false
    val listTemp = mutableListOf<Post>()

    var actualPageTemp = 0


    @Deprecated("No use")
    fun getListTempSplip(): MutableList<Post> {
        return if (saveData) {
            if (actualPage > actualPageTemp) {
                listTemp.let { lista->
                    val data = lista.subList(
                        PAGE_MAX_ELEMENTS * actualPageTemp,
                        PAGE_MAX_ELEMENTS * (actualPageTemp + 1)
                    )
                    actualPageTemp += 1
                    return data
                }
            } else {
                actualPage += 1
                saveData = false
                actualPageTemp = 0
                mutableListOf()
            }
        } else {
            mutableListOf()
        }


    }

    val state = Transformations.map(networkState) {
        when (it) {
            is NetworkState.Success ->
                if (it.isAdditional && it.isEmptyResponse) {
                    ListPostViewState.NoMoreElements
                } else if (it.isEmptyResponse) {
                    ListPostViewState.Empty
                } else {
                    ListPostViewState.Loaded
                }
            is NetworkState.Loading ->
                if (it.isAdditional) {
                    ListPostViewState.AddLoading
                } else {
                    ListPostViewState.Loading
                }
            is NetworkState.Error ->
                if (it.isAdditional) {
                    ListPostViewState.AddError
                } else {
                    ListPostViewState.Error
                }
        }
    }

    /**
     * Refresh characters fetch them again and update the list.
     */
    fun refreshLoadedPostList() {
        saveData = false
        dataSourceFactory.refresh()
    }

    /**
     * Retry last fetch operation to add characters into list.
     */
    fun retryAddPostList() {
        saveData = false
        dataSourceFactory.retry()
    }

    /**
     * OnClickItem post
     */
    fun onclickPost(model: Post) {
        setNavigateWhitDirection(
            ListPostFragmentDirections.actionListPostFragmentToPostDetailFragment(
                model.story_url
            )
        )
    }

    fun onclickDeletedPost(model: Post, position: Int) {

        saveData = true
        deletePost.invoke(
            viewModelScope, DeletePostUseCase.Params(
                model.story_id,
                model.created_at
            )
        )
        listTemp[position].visible = false

        dataSourceFactory.refresh()
    }


}