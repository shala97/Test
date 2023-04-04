package com.example.testapp.workers

import android.content.Context
import androidx.work.*
import com.example.testapp.database.DBManager
import com.example.testapp.manager.NotificationManager
import java.util.concurrent.TimeUnit


class AppActionWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    private var databaseManager: DBManager? = null

    override fun doWork(): Result {
        databaseManager = DBManager(applicationContext)
        NotificationManager.showNotification(
            applicationContext,
            "TestApp",
            buildNotificationMessage()
        )
        databaseManager?.closeDB()
        return Result.success()
    }

    private fun buildNotificationMessage(): String {
        val messages = databaseManager?.getLastBootTime() ?: arrayListOf()
        return if (messages.isEmpty()) {
            applicationContext.getString(com.example.testapp.R.string.no_boots_message)
        } else if (messages.size == 1) {
            applicationContext.getString(
                com.example.testapp.R.string.single_boot_message,
                (messages.first().time ?: 0L).toString()
            )
        } else {
            val lastItem = messages[messages.size - 1].time ?: 0L
            val preLastItem = messages[messages.size - 2].time ?: 0L
            applicationContext.getString(
                com.example.testapp.R.string.multiple_boot_message,
                (lastItem - preLastItem).toString()
            )
        }
    }


    companion object {
        const val WORK_ID = "AppActionWorker-22"

        fun startWorker(context: Context) {
            val work = PeriodicWorkRequest.Builder(
                AppActionWorker::class.java,
                15L, TimeUnit.MINUTES,
                5L, TimeUnit.MINUTES
            ).build()
            WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork(WORK_ID, ExistingPeriodicWorkPolicy.UPDATE, work)
        }
    }
}