package com.omolleapaza.peruapps.peruappstest.ui.features.list_post

import android.view.View
import androidx.paging.PagedList
import androidx.recyclerview.widget.ItemTouchHelper
import com.omolleapaza.peruapps.domain.model.Post
import com.omolleapaza.peruapps.peruappstest.BR
import com.omolleapaza.peruapps.peruappstest.R
import com.omolleapaza.peruapps.peruappstest.databinding.FragmentListPostBinding
import com.omolleapaza.peruapps.peruappstest.ui.base.BaseFragmentWithViewModel
import com.omolleapaza.peruapps.peruappstest.ui.features.list_post.adapter.PostsListAdapter
import com.omolleapaza.peruapps.peruappstest.ui.features.list_post.adapter.PostsListAdapterState


class ListPostFragment :
    BaseFragmentWithViewModel<FragmentListPostBinding, ListPostViewModel>(ListPostViewModel::class) {

    override val getLayoutId: Int
        get() = R.layout.fragment_list_post

    override val getBindingVariable: Int
        get() = BR.listPostVM

    lateinit var viewAdapter: PostsListAdapter

    override fun onFragmentViewReady(view: View) {
        super.onFragmentViewReady(view)
        with(viewDataBinding) {
            viewAdapter = PostsListAdapter(myViewModel)
            includeList.postList.adapter = viewAdapter

        }


    }

    override fun onSetupListeners() {
        super.onSetupListeners()
        with(myViewModel) {
            state.observe(viewLifecycleOwner, ::onViewStateChange)
            data.observe(viewLifecycleOwner, ::onViewDataChange)

            navigateToNextViewWhitDirection.observe(viewLifecycleOwner){
                navigateTo(it)
            }
        }

    }

    private fun onViewDataChange(viewData: PagedList<Post>) {
        viewAdapter.submitList(viewData)
    }

    private fun onViewStateChange(viewState: ListPostViewState) {
        when (viewState) {
            is ListPostViewState.Loaded ->
                viewAdapter.submitState(PostsListAdapterState.Added)
            is ListPostViewState.AddLoading ->
                viewAdapter.submitState(PostsListAdapterState.AddLoading)
            is ListPostViewState.AddError ->
                viewAdapter.submitState(PostsListAdapterState.AddError)
            is ListPostViewState.NoMoreElements ->
                viewAdapter.submitState(PostsListAdapterState.NoMore)
        }
    }


}
