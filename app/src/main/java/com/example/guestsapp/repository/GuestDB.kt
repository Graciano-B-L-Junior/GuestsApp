package com.example.guestsapp.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.guestsapp.Guests.Guest
import com.example.guestsapp.Guests.GuestDAO

@Database(entities = [Guest::class], version = 1)
abstract class GuestDB:RoomDatabase() {
    abstract fun guestDAO():GuestDAO

    companion object {
        private lateinit var INSTANCE:GuestDB
        private val NAME = "guest"
        private val VERSION = 1

        fun getInstance(context:Context): GuestDB{
            if (!::INSTANCE.isInitialized){
                synchronized(GuestDB::class){
                    INSTANCE = Room.databaseBuilder(context,GuestDB::class.java,"guest")
                        .allowMainThreadQueries()
                        .addMigrations(MIGRATION_1_2)
                        .build()
                }
            }
            return INSTANCE
        }
        private val MIGRATION_1_2: Migration = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DELETE FROM Guest")
            }

        }
    }

}