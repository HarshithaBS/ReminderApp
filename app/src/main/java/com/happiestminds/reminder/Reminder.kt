package com.happiestminds.reminder

data class Reminder(var title : String, var description : String, var date : String, var time: String) {
    //lateinit var row: String

    override fun toString(): String {
        return "$title\n "
                //$description"

    }

}

var reminderList = mutableListOf<Reminder>()
