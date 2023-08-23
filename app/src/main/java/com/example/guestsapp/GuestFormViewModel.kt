package com.example.guestsapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guestsapp.Guests.Guest
import com.example.guestsapp.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {
    private val repository:GuestRepository = GuestRepository.getInstance(application.applicationContext)
    private val _guestModel = MutableLiveData<Guest>()
    val guest: LiveData<Guest> = _guestModel

    init {
        _guestModel.value=Guest(0,"",false)
    }
    fun insert_guest(guest: Guest){
        if (_guestModel.isInitialized){
            _guestModel.value=guest
            repository.insert(_guestModel.value!!)
        }
    }
    fun update_guest(guest:Guest){
        repository.updateGuest(guest)
    }

    fun current_guest(guest:Guest){
        _guestModel.value=guest
    }
}