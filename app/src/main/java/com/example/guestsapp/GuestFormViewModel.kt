package com.example.guestsapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guestsapp.Guests.Guest
import com.example.guestsapp.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {
    private val repository:GuestRepository = GuestRepository(application.applicationContext)
    private val _guestModel = MutableLiveData<Guest>()
    val guest: LiveData<Guest> = _guestModel


    fun insert_guest(guest: Guest){
        repository.insert(guest)

    }
    fun update_guest(guest:Guest){
        repository.update(guest)
    }

    fun current_guest(guest:Guest){
        _guestModel.value=guest
    }
}