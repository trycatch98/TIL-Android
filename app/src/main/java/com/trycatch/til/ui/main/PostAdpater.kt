package com.trycatch.til.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.trycatch.til.databinding.ItemPostBinding
import com.trycatch.til.vo.Post

class PostAdapter : ListAdapter<Post, PostAdapter.PostViewHolder>(DiffCallback()) {

    interface ItemClickListener {
        fun onClickItem(post: Post)
    }

    var itemClickListener : ItemClickListener? = null

    class DiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
        PostViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.apply {
            val item = getItem(position)
            bind(item, itemClickListener)
        }
    }

    class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post, itemClickListener: ItemClickListener?) {
            with(itemView) {
                binding.post = post
                setOnClickListener {
                    itemClickListener?.onClickItem(post)
                }
            }
        }
    }
}