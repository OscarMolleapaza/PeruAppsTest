package com.omolleapaza.peruapps.peruappstest.ui.features.list_post.adapter.holders

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.omolleapaza.peruapps.domain.model.Post
import com.omolleapaza.peruapps.peruappstest.databinding.ListItemPostBinding
import com.omolleapaza.peruapps.peruappstest.ui.base.BaseViewHolder

class PostsViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemPostBinding>(
    binding = ListItemPostBinding.inflate(inflater)
) {


    fun bind( item: Post, onClick:()-> Unit) {
        if(!item.visible){
            binding.root.visibility = View.GONE
            binding.root.layoutParams = RecyclerView.LayoutParams(0, 0)
        }

        binding.root.setOnClickListener {
           onClick()
        }
        binding.model = item
        binding.executePendingBindings()
    }
}
