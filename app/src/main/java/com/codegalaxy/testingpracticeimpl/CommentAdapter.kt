package com.codegalaxy.testingpracticeimpl

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codegalaxy.testingpracticeimpl.databinding.ItemCommentBinding
import com.codegalaxy.testingpracticeimpl.model.entity.Comment

class CommentAdapter(private var comments:List<Comment> = emptyList()) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    override fun getItemCount(): Int = comments.size

    class CommentViewHolder(private val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment) {
            binding.apply {
                tvId.text="Id: ${comment.id.toString()}"
                tvPostId.text="PostId: ${comment.postId.toString()}"
                tvName.text = comment.name
                tvEmail.text = comment.email
                tvBody.text = comment.body
            }
        }
    }
}