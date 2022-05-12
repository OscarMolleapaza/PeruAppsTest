package com.omolleapaza.peruapps.peruappstest.ui.features.list_post.paging

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PageKeyedDataSource
import com.omolleapaza.peruapps.domain.model.Post
import com.omolleapaza.peruapps.domain.usecase.GetPostsUseCase
import com.omolleapaza.peruapps.peruappstest.ui.features.list_post.ListPostViewModel
import com.omolleapaza.peruapps.peruappstest.ui.features.list_post.state.NetworkState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch



class PostsPageDataSource(
    private val getPosts: GetPostsUseCase,
    private val viewModel: ListPostViewModel,
) : PageKeyedDataSource<Int, Post>() {

    val networkState = MutableLiveData<NetworkState>()

    var retry: (() -> Unit)? = null


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Post>
    ) {

        if (!viewModel.saveData) {
            networkState.postValue(NetworkState.Loading())
            viewModel.viewModelScope.launch(
                CoroutineExceptionHandler { _, error ->
                    retry = {
                        loadInitial(params, callback)
                    }
                    networkState.postValue(NetworkState.Error())
                }
            ) {

                getPosts.invoke(
                    viewModel.viewModelScope, GetPostsUseCase.Params(PAGE_INIT_ELEMENTS)
                ) {
                    viewModel.listTemp.clear()
                    it.either({ failure ->
                        networkState.postValue(NetworkState.Error())
                    }) { listPost ->

                        viewModel.listTemp.addAll(listPost.listPost)
                        callback.onResult(listPost.listPost, null, listPost.actualPage + 1)
                        networkState.postValue(NetworkState.Success(isEmptyResponse = listPost.listPost.isEmpty()))
                    }
                }

            }

        } else {

            try {

                callback.onResult(viewModel.listTemp, null, viewModel.actualPage+1)
                networkState.postValue(NetworkState.Success(isEmptyResponse = viewModel.listTemp.isEmpty()))

            } catch (e: Exception) {

            }
        }

    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Post>
    ) {


            networkState.postValue(NetworkState.Loading(true))
            viewModel.viewModelScope.launch(
                CoroutineExceptionHandler { _, error ->
                    retry = {
                        loadAfter(params, callback)
                    }
                    networkState.postValue(NetworkState.Error(true))
                }
            ) {

                getPosts.invoke(
                    viewModel.viewModelScope, GetPostsUseCase.Params(params.key)
                ) {
                    it.either({ failure ->
                        retry = {
                            loadAfter(params, callback)
                        }
                        networkState.postValue(NetworkState.Error(true))
                    }) { listPost ->
                        viewModel.listTemp.addAll(listPost.listPost)
                        viewModel.actualPage = params.key + 1
                        callback.onResult(listPost.listPost, viewModel.actualPage)
                        networkState.postValue(
                            NetworkState.Success(
                                true,
                                listPost.listPost.isEmpty()
                            )
                        )
                    }
                }

            }


    }
    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Post>
    ) {
        // Ignored, since we only ever append to our initial load
    }

    fun retry() {
        retry?.invoke()
    }

    companion object {
        const val PAGE_INIT_ELEMENTS = 0
        const val PAGE_MAX_ELEMENTS = 20
    }
}
