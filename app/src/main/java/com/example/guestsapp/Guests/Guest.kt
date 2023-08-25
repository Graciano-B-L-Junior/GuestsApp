package com.example.guestsapp.Guests

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Guest(
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name = "name") var name:String,
    @ColumnInfo(name = "presence") var presence:Boolean) {
}