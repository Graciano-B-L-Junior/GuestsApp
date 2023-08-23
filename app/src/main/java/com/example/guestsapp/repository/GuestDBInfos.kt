package com.example.guestsapp.repository

class GuestDBInfos {
    companion object INFO{
        object DB{
            const val TABLE_NAME = "Guest"
            const val COLUMN_ID = "id"
            const val COLUMN_NAME = "name"
            const val COLUMN_PRESENCE = "presence"
            const val SQL_CREATE_ENTRIES =
                "CREATE TABLE ${TABLE_NAME} (" +
                        "${COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "${COLUMN_NAME} TEXT," +
                        "${COLUMN_PRESENCE} INTEGER)"

            const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${TABLE_NAME}"
        }
    }
}