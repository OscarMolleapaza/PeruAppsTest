package com.omolleapaza.peruapps.peruappstest.ui.features.list_post.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omolleapaza.peruapps.domain.model.Post
import com.omolleapaza.peruapps.peruappstest.ui.base.BasePagedListAdapter
import com.omolleapaza.peruapps.peruappstest.ui.features.list_post.ListPostViewModel
import com.omolleapaza.peruapps.peruappstest.ui.features.list_post.adapter.holders.ErrorViewHolder
import com.omolleapaza.peruapps.peruappstest.ui.features.list_post.adapter.holders.LoadingViewHolder
import com.omolleapaza.peruapps.peruappstest.ui.features.list_post.adapter.holders.PostsViewHolder



internal enum class ItemView(val type: Int) {
    POSTLIST(type = 0),
    LOADING(type = 1),
    ERROR(type = 2);

    companion object {
        fun valueOf(type: Int): ItemView = values().first { it.type == type }
    }
}


class PostsListAdapter constructor(
    private val viewModel: ListPostViewModel
) : BasePagedListAdapter<Post>(
    itemsSame = { old, new -> old.story_id == new.story_id },
    contentsSame = { old, new -> old == new }
) {


    private var state: PostsListAdapterState = PostsListAdapterState.Added


    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder =
        when (ItemView.valueOf(viewType)) {
            ItemView.POSTLIST -> PostsViewHolder(inflater)
            ItemView.LOADING -> LoadingViewHolder(inflater)
            else -> ErrorViewHolder(inflater)
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemView(position)) {
            ItemView.POSTLIST ->
                getItem(position)?.let {
                    if (holder is PostsViewHolder) {
                        holder.bind(it){
                            viewModel.onclickDeletedPost(it,position)
                            deleteItem(position)
                            viewModel.onclickPost(it)
                        }
                    }
                }
            ItemView.ERROR ->
                if (holder is ErrorViewHolder) {
                    holder.bind(viewModel)
                }
            else -> {

            }
        }
    }

    override fun getItemId(position: Int): Long =
        when (getItemView(position)) {
            ItemView.POSTLIST ->  getItem(position)?.story_id?.toLong() ?:-1L
            ItemView.LOADING -> 0L
            ItemView.ERROR -> 1L
        }

    override fun getItemCount() =
        if (state.hasExtraRow) {
            super.getItemCount() + 1
        } else {
            super.getItemCount()
        }


    override fun getItemViewType(position: Int) = getItemView(position).type


    fun submitState(newState: PostsListAdapterState) {
        val oldState = state
        state = newState
        if (newState.hasExtraRow && oldState != newState) {
            notifyItemChanged(itemCount - 1)
        }
    }



    internal fun getItemView(position: Int) =
        if (state.hasExtraRow && position == itemCount - 1) {
            if (state.isAddError()) {
                ItemView.ERROR
            } else {
                ItemView.LOADING
            }
        } else {
            ItemView.POSTLIST
        }

    fun deleteItem(position: Int) {
        try{
            notifyItemChanged(position)
        }catch (e:Exception){

        }
    }


}
