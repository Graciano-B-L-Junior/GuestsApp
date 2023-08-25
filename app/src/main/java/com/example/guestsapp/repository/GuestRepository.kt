package com.example.guestsapp.repository

import android.content.ContentValues
import android.content.Context
import androidx.room.Room
import com.example.guestsapp.Guests.Guest

class GuestRepository(context: Context){
    private var guestDataBase = GuestDB.getInstance(context).guestDAO()



    fun insert(guest:Guest):Boolean{
        return guestDataBase.insert(guest) > 0

    }
    fun update(guest:Guest):Boolean{
        return guestDataBase.update(guest) > 0
    }

    fun delete(id:Int):Boolean{
        var guest = guestDataBase.get(id)
        return guestDataBase.delete(guest) > 0
    }
    fun getAll():List<Guest>{
        return guestDataBase.getAll()
    }
    fun get(id:Int):Guest{
        return guestDataBase.get(id)
    }
    fun getPresents():List<Guest>{
        return guestDataBase.getPresents()
    }
    fun getAbsents():List<Guest>{
        return guestDataBase.getAbsents()
    }
}