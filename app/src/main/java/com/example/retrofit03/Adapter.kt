package com.example.retrofit03

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit03.databinding.ItempostBinding

class Adapter(private var User : List<personRespon>, val vvv : Adapter.Click):RecyclerView.Adapter<Adapter.UserViewHolder> (){
    inner class UserViewHolder (val bin : ItempostBinding):RecyclerView.ViewHolder(bin.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ItempostBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int {
        return User.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bin.apply {
            TVText.text = User[position].first_name
            TVText.setOnClickListener{
                vvv.click(User[position])
            }
            edit.setOnClickListener {
                vvv.Edit(User[position])
            }
        }
    }
    interface Click{
        fun click (post : personRespon)
        fun Edit (post : personRespon)
    }


}