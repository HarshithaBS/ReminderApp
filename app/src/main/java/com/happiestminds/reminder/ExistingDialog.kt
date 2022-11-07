package com.happiestminds.reminder

import android.app.Dialog
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ExistingDialog: DialogFragment() {

    lateinit var reminderList: ListView
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var dlg1 : Dialog? = null


        //retrieve bundle
        val message1 = arguments?.getString("msg")

        //create dislog
        context?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Confirmation")
            builder.setMessage(message1)
            builder.setPositiveButton("Delete") { dialog, i ->
                //executed button clicking


                activity?.finish()

            }
//            builder.setNegativeButton("NO"){dialog, i ->
//                dialog.cancel()
            //}
            builder.setNeutralButton("OK"){dialog, i ->
                dialog.cancel()

            }


            dlg1 = builder.create()
        }


        return dlg1!!
    }
}