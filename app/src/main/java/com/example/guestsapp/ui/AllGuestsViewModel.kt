package com.example.guestsapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guestsapp.Guests.Guest
import com.example.guestsapp.recyclerview.GuestAdapter
import com.example.guestsapp.repository.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository:GuestRepository = GuestRepository.getInstance(application.applicationContext)
    private val _allguests = MutableLiveData<List<Guest>>()
    val allguests: LiveData<List<Guest>> = _allguests
    fun getAllGuests(){
        _allguests.value = repository.getAll()
    }
    fun removeGuest(guest: Guest){
        repository.removeGuest(guest)
        getAllGuests()
    }
}