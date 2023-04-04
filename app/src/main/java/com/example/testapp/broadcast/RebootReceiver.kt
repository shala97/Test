package com.example.testapp.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.testapp.database.DBManager
import java.util.*

class RebootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val thread = Thread {
            val requiredContext = context ?: return@Thread
            val databaseManager = DBManager(requiredContext)
            databaseManager.saveLastBootTime(Date().time)
            databaseManager.closeDB()
        }
        thread.start()
        thread.join()
    }
}
