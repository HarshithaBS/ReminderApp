package com.happiestminds.reminder

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class DBWrapper(ctx : Context) {

    val helper = DBHelper(ctx) //tables are created
    val db = helper.writableDatabase
    fun addReminder(rem : Reminder): Boolean {
        //insert
        val values = ContentValues()
        //values.put(DBHelper.CLM_NUM, rem.row)
        values.put(DBHelper.CLM_TITLE, rem.title)
        values.put(DBHelper.CLM_DESCRIPTION, rem.description)
        values.put(DBHelper.CLM_DATE, rem.date)
        values.put(DBHelper.CLM_TIME, rem.time)

        val rowid = db.insert(DBHelper.TABLE_NAME, null, values)
        if (rowid.toInt() == -1){
            return false
        }
        return true

        //db.insert(DBHelper.TABLE_NAME ,null, values)
    }

    fun getAllReminders() : Cursor {
        //query
        val clms = arrayOf(DBHelper.CLM_TITLE, DBHelper.CLM_DESCRIPTION, DBHelper.CLM_DATE, DBHelper.CLM_TIME)
        return db.query(DBHelper.TABLE_NAME, clms, null, null, null, null, null) //query is a cursor type

    }

    fun deleteStudent(std : Reminder){
        //delete
        db.delete(DBHelper.TABLE_NAME, "${DBHelper.CLM_TITLE} = ?", arrayOf(std.title.toString())) //? will be replaced with element passes in array
    }
}

//data class ReminderClass(val row : Int, val titleName: String, val desc : String, val time : String, val date : String){
//    override fun toString(): String {
//        return """
//            Row : $row
//            Name : $titleName
//            Description : $desc
//            Date : $date
//            Time: $time
//            """.trimIndent()
//
//    }
//}
