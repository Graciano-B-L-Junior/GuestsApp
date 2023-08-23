package com.example.guestsapp.Guests

import android.content.Context

interface GuestListener {
    fun onclick(guest: Guest)
    fun onLongClick(guest: Guest)
}