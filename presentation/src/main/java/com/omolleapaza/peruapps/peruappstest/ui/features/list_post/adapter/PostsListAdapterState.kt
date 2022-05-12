package com.omolleapaza.peruapps.peruappstest.ui.features.list_post.adapter





sealed class PostsListAdapterState(
    val hasExtraRow: Boolean
) {

    /**
     * Listed the added characters into list.
     */
    object Added : PostsListAdapterState(hasExtraRow = true)

    /**
     * Loading for new characters to add into list.
     */
    object AddLoading : PostsListAdapterState(hasExtraRow = true)

    /**
     * Error on add new characters into list.
     */
    object AddError : PostsListAdapterState(hasExtraRow = true)

    /**
     * No more characters to add into list.
     */
    object NoMore : PostsListAdapterState(hasExtraRow = false)



    fun isAdded() = this is Added

    fun isAddLoading() = this is AddLoading

    fun isAddError() = this is AddError

    fun isNoMore() = this is NoMore
}
