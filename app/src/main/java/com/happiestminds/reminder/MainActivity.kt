package com.happiestminds.reminder

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    lateinit var existing : Button

    private val reminderaResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            Toast.makeText(this, "permission granted", Toast.LENGTH_LONG).show()
        }
        else if (it.resultCode == RESULT_CANCELED){
            Toast.makeText(this, "permission ", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.reminderB)
        existing = findViewById(R.id.existingRemB)

        if(checkSelfPermission(android.Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_DENIED){
            Log.d("MainActivity", "Permission not granted")

            requestPermissions(arrayOf(android.Manifest.permission.READ_CALENDAR), 1) //first permission must be requested in manifest only then it can be requested to the user
        }else{
            Log.d("MainActivity", "Permission granted")
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1){
            if (permissions.isNotEmpty()){
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.d("MainActivity","User accepted the permission")

                }else{
                    Log.d("MainActivity","User denied the permission")

                }
            }
        }
    }



    fun reminderClick(view: View) {

        val remindIntent = Intent(this,SetReminder().javaClass)
        reminderaResult.launch(remindIntent)
        //startActivity(remindIntent)

    }

    fun remindersClick(view: View) {
        val existingRem = Intent(this, ExistingReminder().javaClass)
        startActivity(existingRem)


    }

}