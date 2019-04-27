package com.moventes.moventest.android.adapters

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.moventes.moventest.android.R
import com.moventes.moventest.android.models.User
import com.moventes.moventest.android.viewholders.UserViewHolder
import com.nostra13.universalimageloader.core.ImageLoader

class UsersListAdapter(users: List<User>) : RecyclerView.Adapter<UserViewHolder>() {

    private var users: List<User>? = null
    private var imageLoader: ImageLoader? = null

    init {
        this.users = users
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_user_item, parent, false)

        imageLoader = ImageLoader.getInstance()

        return UserViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        imageLoader?.displayImage(users?.get(position)?.picture, holder.avatar)
        holder.name?.text = "${users?.get(position)?.first_name} ${users?.get(position)?.last_name}"
    }

    override fun getItemCount(): Int {
        return users?.size ?: 0
    }
}
