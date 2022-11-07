package com.happiestminds.reminder

import android.app.Dialog
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class MyDialog : DialogFragment() {
    lateinit var reminderList: ListView
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var dlg : Dialog? = null


        //retrieve bundle
        val message = arguments?.getString("msg")

        //create dislog
        context?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Confirmation")
            builder.setMessage(message)
            builder.setPositiveButton("Confirm") { dialog, i ->
                //executed button clicking
                activity?.finish()

            }

            builder.setNeutralButton("Cancel"){dialog, i ->
                dialog.cancel()

            }


            dlg = builder.create()
        }


        return dlg!!
    }

}
