package com.codegalaxy.testingpracticeimpl

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codegalaxy.testingpracticeimpl.databinding.ItemUserBinding
import com.codegalaxy.testingpracticeimpl.model.entity.User

class UserAdapter(
    private var users: List<User> = emptyList(),
    private val onUserClick: (User) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
        holder.itemView.setOnClickListener{
            onUserClick(users[position])
        }
    }

    override fun getItemCount(): Int = users.size

    inner class UserViewHolder(

        private val binding: ItemUserBinding,

    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.apply {
                tvId.text="Id: ${user.id.toString()}"
                tvName.text = user.name
                tvEmail.text = user.email
                tvPhone.text = user.phone
                tvUsername.text = user.username
                tvWebsite.text = user.website

            }
        }
    }
}