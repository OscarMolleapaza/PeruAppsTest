package com.omolleapaza.peruapps.peruappstest.ui.features.post_detail




sealed class PostDetailViewState  {

    /**
     * Refreshing post list.
     */
    object Refreshing :PostDetailViewState()

    /**
     * Loaded post list.
     */
    object Loaded :PostDetailViewState()

    /**
     * Loading post list.
     */
    object Loading :PostDetailViewState()


    /**
     * Error on loading post list.
     */
    object Error :PostDetailViewState()




    fun isRefreshing() = this is Refreshing

    fun isLoaded() = this is Loaded

    fun isLoading() = this is Loading

    fun isError() = this is Error
}
