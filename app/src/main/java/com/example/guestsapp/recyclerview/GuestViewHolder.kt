package com.example.guestsapp.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.guestsapp.Guests.Guest
import com.example.guestsapp.Guests.GuestListener
import com.example.guestsapp.databinding.RowGuestBinding

class GuestViewHolder(var binding: RowGuestBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(guest:Guest,listener: GuestListener){
        binding.guestName.text=guest.name
        binding.guestName.setOnClickListener{
            listener.onclick(guest)
        }
        binding.guestName.setOnLongClickListener {
            listener.onLongClick(guest)
            true
        }
    }
}