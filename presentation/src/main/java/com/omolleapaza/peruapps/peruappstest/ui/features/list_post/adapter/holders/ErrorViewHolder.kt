package com.omolleapaza.peruapps.peruappstest.ui.features.list_post.adapter.holders

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.omolleapaza.peruapps.peruappstest.databinding.ListItemErrorBinding
import com.omolleapaza.peruapps.peruappstest.ui.base.BaseViewHolder
import com.omolleapaza.peruapps.peruappstest.ui.features.list_post.ListPostViewModel


class ErrorViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemErrorBinding>(
    binding = ListItemErrorBinding.inflate(inflater)
) {

    fun bind(viewModel: ListPostViewModel) {
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }
}
