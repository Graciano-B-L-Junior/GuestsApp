package com.example.guestsapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guestsapp.Guests.Guest
import com.example.guestsapp.repository.GuestRepository

class AbsentsViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = GuestRepository(application.applicationContext)
    private var _guest = MutableLiveData<List<Guest>>()
    val guest: LiveData<List<Guest>> = _guest

    fun getAll(){
        _guest.value = repository.getAbsents()
    }
    fun removeGuest(guest:Guest){
        repository.delete(guest.id)
        getAll()
    }
}