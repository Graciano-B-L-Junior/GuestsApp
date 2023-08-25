package com.example.guestsapp.Guests

import androidx.room.*
import com.example.guestsapp.repository.GuestDBInfos

@Dao
interface GuestDAO {
    @Query("SELECT * FROM ${GuestDBInfos.INFO.DB.TABLE_NAME} WHERE ${GuestDBInfos.INFO.DB.COLUMN_ID} = :id")
    fun get(id: Int):Guest

    @Query("SELECT * FROM ${GuestDBInfos.INFO.DB.TABLE_NAME}")
    fun getAll():List<Guest>

    @Query("SELECT * FROM ${GuestDBInfos.INFO.DB.TABLE_NAME} WHERE ${GuestDBInfos.INFO.DB.COLUMN_PRESENCE} = 1")
    fun getPresents():List<Guest>

    @Query("SELECT * FROM ${GuestDBInfos.INFO.DB.TABLE_NAME} WHERE ${GuestDBInfos.INFO.DB.COLUMN_PRESENCE} = 0")
    fun getAbsents():List<Guest>

    @Insert
    fun insert(guest: Guest):Long

    @Delete
    fun delete(guest:Guest):Int

    @Update
    fun update(guest: Guest):Int


}