package com.example.chiachen.ktmvp.presentation.view.adapters

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import com.example.chiachen.ktmvp.R
import com.example.chiachen.ktmvp.presentation.view.viewmodels.UserViewModel
import com.example.chiachen.ktmvp.utils.isLollipopOrAbove
import com.example.chiachen.ktmvp.utils.loadUrl
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListAdapter(private val users: MutableList<UserViewModel>,
                      private val listener: (UserViewModel) -> Unit) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    override fun getItemCount() = users.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_user, parent, false))
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) = holder.bind(users[position], listener)

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(user: UserViewModel, listener: (UserViewModel) -> Unit) = with(itemView) {
            name.text = user.displayName
            reputation.text = "${user.reputation}points"
            userAvatar.loadUrl(user.profileImage)
            setOnClickListener { listener(user) }
            isLollipopOrAbove { userAvatar.transitionName = "transition${user.userId}" }
        }
    }
}