package az.altacademy.androidgroup2.lessons.lesson37

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class MyFirstWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    private var count = 0

    @SuppressLint("RestrictedApi")
    override suspend fun doWork(): Result {
        count++

        val data = inputData.getString("name")

        Log.d("MyFirstWorkerTAG", "Starting work ... $data")
        delay(2000)
        Log.d("MyFirstWorkerTAG", "Finishing work ... ")

        return Result.Success()
    }


}