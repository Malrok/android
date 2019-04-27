package com.moventes.moventest.android.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.moventes.moventest.android.R

class UserViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    var avatar: ImageView? = null
    var name: TextView? = null

    init {
        avatar = itemView?.findViewById(R.id.avatar)
        name = itemView?.findViewById(R.id.name)
    }

}