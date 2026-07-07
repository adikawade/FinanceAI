package com.financeai.app.sms.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.financeai.app.sms.worker.SmsProcessingWorker

/**
 * Receives incoming SMS and forwards them
 * to the background worker.
 *
 * Responsibilities:
 * - Listen for SMS_RECEIVED
 * - Extract sender and message
 * - Queue background processing
 *
 * Future:
 * - Notification permission handling
 * - Duplicate SMS protection
 * - Boot persistence
 * - Delivery reports
 */
class SmsReceiver : BroadcastReceiver() {

    override fun onReceive(
        context: Context,
        intent: Intent
    ) {

        if (intent.action != Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            return
        }

        val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)

        messages.forEach { sms ->

            val sender = sms.displayOriginatingAddress ?: return@forEach
            val message = sms.displayMessageBody ?: return@forEach

            val inputData = Data.Builder()
                .putString(SmsProcessingWorker.KEY_SENDER, sender)
                .putString(SmsProcessingWorker.KEY_MESSAGE, message)
                .build()

            val request = OneTimeWorkRequestBuilder<SmsProcessingWorker>()
                .setInputData(inputData)
                .build()

            WorkManager
                .getInstance(context)
                .enqueue(request)
        }
    }
}