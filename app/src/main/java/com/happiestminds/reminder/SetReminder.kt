package com.happiestminds.reminder

import android.app.*
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.util.Calendar
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.CalendarContract.Events
import android.provider.Settings
import android.provider.SyncStateContract.Helpers.insert
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import java.text.SimpleDateFormat

class SetReminder : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {

    lateinit var timeTextView: TextView
    lateinit var dateTextView: TextView

    lateinit var timeBtn: Button
    lateinit var dateBtn: Button

    lateinit var titleeditText: EditText
    lateinit var descriptionEditText: EditText





    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_reminder)

        timeTextView = findViewById(R.id.timeT)
        dateTextView = findViewById(R.id.dateT)

        timeBtn = findViewById(R.id.timeB)
        dateBtn = findViewById(R.id.dateB)

        titleeditText = findViewById(R.id.titleE)
        descriptionEditText = findViewById(R.id.descE)

    }



    fun dateClick(view: View) {

        val dlg = DatePickerDialog(this)
        dlg.setOnDateSetListener { dPicker, year, month, day ->
            dateTextView.text = "$day-${month + 1}-$year"
            dateBtn.isEnabled = false
        }
        dlg.show()

        //val intent = Intent(Intent.ACTION_INSERT, Events.CONTENT_URI)
        //val data = Events.CONTENT_URI
        // val begin : Long = 12
//        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, 1)
//
//
//        intent.getStringExtra(CalendarContract.Events.DESCRIPTION)

       // startActivity(intent)

    }



    fun timeClick(view: View) {

        val dlg = TimePickerDialog(
            this, this,
            10, 0, true
        )
        timeBtn.isEnabled = false
        //val dlg = TimePickerDialog(this)

        dlg.show()
    }

    override fun onTimeSet(tPicker: TimePicker?, hh: Int, mm: Int) {
        //timeTextView.text = "$hh : $mm"
        timeTextView.text = "$hh:$mm"

    }

    fun addClick(view: View) {
        var title = titleeditText.text.toString()
        var des = descriptionEditText.text.toString()
        val dates = dateTextView.text.toString()
        var time = timeTextView.text.toString()
        var cr : ContentResolver = contentResolver


        val dlg = MyDialog()
        dlg.isCancelable = false //on clicking back button, it does not go back
        //passing data from activity
        val dataBundle = Bundle()

        dataBundle.putString("msg", "Date: ${dateTextView.text}, Time: ${timeTextView.text}")

        dlg.arguments = dataBundle
        dlg.show(supportFragmentManager, null)

        var reminderOb =
            Reminder(titleeditText.text.toString(), descriptionEditText.text.toString() , dateTextView.text.toString(), timeTextView.text.toString())
        reminderList.add(reminderOb)


        when {
            title.isEmpty() -> titleeditText.error = "title is mandatory"
            des.isEmpty() -> descriptionEditText.error = "Description is mandatory"

            else -> {

                val rem = Reminder(title, des, dates, time)
                if (DBWrapper(this).addReminder(rem)) {

                    Toast.makeText(this, "Reminder added", Toast.LENGTH_LONG).show()

                } else {
                    Toast.makeText(this, "Reminder not added", Toast.LENGTH_LONG).show()
                }

                val intent = Intent(this, ExistingReminder::class.java)
                intent.putExtra("title", titleeditText.text.toString())

                val dateStr = dateTextView.text.toString()
                val timeStr = timeTextView.text.toString()
                val dateTime = "$dateStr $timeStr"
                val format = SimpleDateFormat("dd-MM-yyyy HH:mm")
                val date = format.parse(dateTime)
                val cal = Calendar.getInstance()
                cal.time = date
                Log.d("Add reminder", "milli: ${cal.timeInMillis}")




                var value =  ContentValues()


                value.put(Events.DTSTART, cal.timeInMillis)
                value.put(Events.DTEND, cal.timeInMillis + 60*1000);
                value.put(Events.TITLE, titleeditText.text.toString());
                value.put(Events.DESCRIPTION, descriptionEditText.text.toString());
                value.put(Events.CALENDAR_ID, 1);
                value.put(Events.EVENT_TIMEZONE,"IST")
                value.put(Events.HAS_ALARM, 1)


                var uri1 = cr.insert(Events.CONTENT_URI, value);
                Log.d("SetReminder", "addBtnClick:  $uri1")
                val evenID = uri1?.lastPathSegment?.toInt()


                Toast.makeText(this, "Task Scheduled Successfully", Toast.LENGTH_SHORT).show()



                var  values =  ContentValues();
                values.put( CalendarContract.Reminders.EVENT_ID, evenID );
                values.put( CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_DEFAULT )
                values.put( CalendarContract.Reminders.MINUTES, CalendarContract.Reminders.MINUTES_DEFAULT )
                val reminderUri = cr.insert( CalendarContract.Reminders.CONTENT_URI, values )

                Log.d("Add reminder", "reminder uri: $reminderUri")




            }
        }

    }

}








