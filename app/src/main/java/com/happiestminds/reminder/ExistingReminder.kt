package com.happiestminds.reminder

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class ExistingReminder : AppCompatActivity() {

    lateinit var reminderListView: ListView

    lateinit var adapter : ArrayAdapter<Reminder>
    //val list = Reminder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_existing_reminder)

        reminderListView = findViewById(R.id.listV)

        adapter = ArrayAdapter<Reminder>(this, android.R.layout.simple_list_item_1, reminderList)
        reminderListView.adapter = adapter

        reminderListView.setOnItemClickListener { adapterView, view, index, l ->
            val selectedRem = reminderList[index]
            val dlg = ExistingDialog()
            dlg.isCancelable = false //on clicking back button, it does not go back
            //passing data from activity
            val dataBundle = Bundle()

            dataBundle.putString("msg", "${selectedRem.time}")
            dataBundle.putString("msg", "${selectedRem.date}")

            dlg.arguments = dataBundle
            dlg.show(supportFragmentManager, null)
            reminderList.removeAt(index)



        }

    }
//    private fun setUpData() {
//        val cursor = DBWrapper(this).getAllReminders()
//        if (cursor.count > 0) {
//
//            val idx_title = cursor.getColumnIndexOrThrow(DBHelper.CLM_TITLE)
//            val idx_desc = cursor.getColumnIndexOrThrow(DBHelper.CLM_DESCRIPTION)
//            val idx_date = cursor.getColumnIndexOrThrow(DBHelper.CLM_DATE)
//            val idx_time = cursor.getColumnIndexOrThrow(DBHelper.CLM_TIME)
//            cursor.moveToFirst()
//
//            do {
//
////                val row = cursor.getInt(idx_row)
//                val title = cursor.getString(idx_title)
//                val descr = cursor.getString(idx_desc)
//                val dates = cursor.getString(idx_date)
//                val time = cursor.getString(idx_time)
//
//                val rem = Reminder(title, descr, dates, time)
//                reminderList.add(rem)
//            } while (cursor.moveToNext())
//            adapter.notifyDataSetChanged()//listview will be refreshed with new data
//
//
//
//
//
//            Toast.makeText(this, "Found : ${reminderList.count()}", Toast.LENGTH_LONG).show()
//
//
//        }
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//        setUpData()
//        adapter.notifyDataSetChanged()
//    }

}