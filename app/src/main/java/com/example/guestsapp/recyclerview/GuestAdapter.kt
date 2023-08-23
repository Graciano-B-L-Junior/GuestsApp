package com.example.guestsapp.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.guestsapp.Guests.Guest
import com.example.guestsapp.Guests.GuestListener
import com.example.guestsapp.R
import com.example.guestsapp.databinding.RowGuestBinding

class GuestAdapter():RecyclerView.Adapter<GuestViewHolder>() {
    private var listener:GuestListener? =null
    var dataSet:List<Guest> = mutableListOf<Guest>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val binding = RowGuestBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GuestViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataSet.count()
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(dataSet[position],listener!!)
    }

    fun updateGuestList(list:List<Guest>,listener: GuestListener){
        dataSet=list
        this.listener=listener
        notifyDataSetChanged()
    }
}