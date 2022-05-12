package com.omolleapaza.peruapps.peruappstest.ui.features.list_post




sealed class ListPostViewState  {

    /**
     * Refreshing post list.
     */
    object Refreshing :ListPostViewState()

    /**
     * Loaded post list.
     */
    object Loaded :ListPostViewState()

    /**
     * Loading post list.
     */
    object Loading :ListPostViewState()

    /**
     * Loading on add more elements into post list.
     */
    object AddLoading :ListPostViewState()

    /**
     * Empty post list.
     */
    object Empty :ListPostViewState()

    /**
     * Error on loading post list.
     */
    object Error :ListPostViewState()

    /**
     * Error on add more elements into post list.
     */
    object AddError :ListPostViewState()

    /**
     * No more elements for adding into post list.
     */
    object NoMoreElements :ListPostViewState()


    fun isRefreshing() = this is Refreshing

    fun isLoaded() = this is Loaded

    fun isLoading() = this is Loading

    fun isAddLoading() = this is AddLoading

    fun isEmpty() = this is Empty

    fun isError() = this is Error

    fun isAddError() = this is AddError

    fun isNoMoreElements() = this is NoMoreElements
}
