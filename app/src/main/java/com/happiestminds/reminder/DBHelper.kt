package com.happiestminds.reminder

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "reminder.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(TABLE_QUERIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //
    }
    companion object {
        const val TABLE_NAME = "ReminderData"
        const val CLM_TITLE = "title"
        const val CLM_DESCRIPTION = "description"
        const val CLM_TIME = "time"
        const val CLM_DATE =  "date"
        const val TABLE_QUERIES = "create table $TABLE_NAME ($CLM_TITLE text, $CLM_DESCRIPTION text, $CLM_TIME time, $CLM_DATE date)"
    }
}