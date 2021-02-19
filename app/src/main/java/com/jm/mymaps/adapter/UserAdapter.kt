package com.jm.mymaps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jm.mymaps.R
import com.jm.mymaps.UserHelperClass

class UserAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var items : List<UserHelperClass> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {

            is UserViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(userHelperClassList: List<UserHelperClass>){
        items = userHelperClassList
    }

    class UserViewHolder constructor(
            itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mapName = itemView.findViewById<TextView>(R.id.tv_mapName)
        val mapImage = itemView.findViewById<ImageView>(R.id.image)

        fun bind(userHelperClass: UserHelperClass){
            mapName.setText(userHelperClass.mapName)
        }


    }
}



