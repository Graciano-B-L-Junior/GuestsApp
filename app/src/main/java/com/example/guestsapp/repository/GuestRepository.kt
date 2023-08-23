package com.example.guestsapp.repository

import android.content.ContentValues
import android.content.Context
import com.example.guestsapp.Guests.Guest

class GuestRepository private constructor(){
    private lateinit var db: GuestDB

    companion object{
        private var repository:GuestRepository? = null
        fun getInstance(context: Context): GuestRepository {
            if(repository==null){
                repository= GuestRepository()
            }
            repository!!.db = GuestDB(context)
            return repository as GuestRepository
        }
    }

    fun insert(guest: Guest){
        if(db!=null){
            val _db = db.writableDatabase
            val values = ContentValues().apply {
                put(GuestDBInfos.INFO.DB.COLUMN_NAME,guest.name)
                put(GuestDBInfos.INFO.DB.COLUMN_PRESENCE, if(guest.presence) 1 else 0)
            }
            val newRowId = _db.insert(GuestDBInfos.INFO.DB.TABLE_NAME, null, values)
        }
    }
    fun getAll():List<Guest>{
        val guests = mutableListOf<Guest>()
        val _db = db.readableDatabase
        val cursor = _db.query(GuestDBInfos.INFO.DB.TABLE_NAME,null,null,null,null,null,null)

        with(cursor) {
            while (moveToNext()) {
                val guest_id = getInt(getColumnIndexOrThrow(GuestDBInfos.INFO.DB.COLUMN_ID))
                val guest_name = getString(getColumnIndexOrThrow(GuestDBInfos.INFO.DB.COLUMN_NAME))
                val guest_presence = getInt(getColumnIndexOrThrow(GuestDBInfos.INFO.DB.COLUMN_PRESENCE))
                guests.add(Guest(guest_id,guest_name,guest_presence == 1))
            }
        }
        cursor.close()

        return guests
    }
    fun getAllPresents():List<Guest>{
        val guests = mutableListOf<Guest>()
        val selection = "${GuestDBInfos.INFO.DB.COLUMN_PRESENCE} = ?"
        val selectionArgs = arrayOf("1")
        val _db = db.readableDatabase
        val cursor = _db.query(GuestDBInfos.INFO.DB.TABLE_NAME,null,selection,selectionArgs,null,null,null)

        with(cursor) {
            while (moveToNext()) {
                val guest_id = getInt(getColumnIndexOrThrow(GuestDBInfos.INFO.DB.COLUMN_ID))
                val guest_name = getString(getColumnIndexOrThrow(GuestDBInfos.INFO.DB.COLUMN_NAME))
                val guest_presence = getInt(getColumnIndexOrThrow(GuestDBInfos.INFO.DB.COLUMN_PRESENCE))
                guests.add(Guest(guest_id,guest_name,guest_presence == 1))
            }
        }
        cursor.close()

        return guests
    }
    fun getAllAbsents():List<Guest>{
        val guests = mutableListOf<Guest>()
        val selection = "${GuestDBInfos.INFO.DB.COLUMN_PRESENCE} = ?"
        val selectionArgs = arrayOf("0")
        val _db = db.readableDatabase
        val cursor = _db.query(GuestDBInfos.INFO.DB.TABLE_NAME,null,selection,selectionArgs,null,null,null)

        with(cursor) {
            while (moveToNext()) {
                val guest_id = getInt(getColumnIndexOrThrow(GuestDBInfos.INFO.DB.COLUMN_ID))
                val guest_name = getString(getColumnIndexOrThrow(GuestDBInfos.INFO.DB.COLUMN_NAME))
                val guest_presence = getInt(getColumnIndexOrThrow(GuestDBInfos.INFO.DB.COLUMN_PRESENCE))
                guests.add(Guest(guest_id,guest_name,guest_presence == 1))
            }
        }
        cursor.close()

        return guests
    }
    fun removeGuest(guest:Guest){
        val _db = db.writableDatabase
        val selection = "${GuestDBInfos.INFO.DB.COLUMN_ID} = ?"
        val selectionArgs = arrayOf(guest.id.toString())
        _db.delete(GuestDBInfos.INFO.DB.TABLE_NAME,selection, selectionArgs)

    }
    fun updateGuest(guest: Guest){
        val _db = db.writableDatabase
        val values = ContentValues().apply {
            put(GuestDBInfos.INFO.DB.COLUMN_NAME, guest.name)
            put(GuestDBInfos.INFO.DB.COLUMN_PRESENCE,guest.presence)
        }
        val selection = "${GuestDBInfos.INFO.DB.COLUMN_ID} = ?"
        val selectionArgs = arrayOf(guest.id.toString())
        _db.update(
            GuestDBInfos.INFO.DB.TABLE_NAME,
            values,
            selection,
            selectionArgs)
    }
}