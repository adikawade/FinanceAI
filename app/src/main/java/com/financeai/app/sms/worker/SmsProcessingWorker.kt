package com.financeai.app.sms.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

/**
 * Background worker responsible for processing
 * financial SMS safely.
 *
 * Current Responsibilities:
 * - Receive queued SMS
 * - Placeholder for processing pipeline
 *
 * Future Responsibilities:
 * - AI Categorization
 * - Duplicate Detection
 * - Merchant Learning
 * - Cloud Sync
 * - Retry Failed Processing
 * - Analytics
 */
@HiltWorker
class SmsProcessingWorker @AssistedInject constructor(

    @Assisted
    context: Context,

    @Assisted
    params: WorkerParameters

) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {

        val sender = inputData.getString(KEY_SENDER)
        val message = inputData.getString(KEY_MESSAGE)

        if (sender.isNullOrBlank()) {
            return Result.failure()
        }

        if (message.isNullOrBlank()) {
            return Result.failure()
        }

        // TODO
        // Phase 2:
        // Call ProcessIncomingSmsUseCase
        //
        // Phase 3:
        // AI Categorization
        //
        // Phase 4:
        // Save Transaction
        //
        // Phase 5:
        // Cloud Sync

        return Result.success()
    }

    companion object {

        const val KEY_SENDER = "sender"

        const val KEY_MESSAGE = "message"

        const val WORK_NAME = "sms_processing_worker"
    }
}