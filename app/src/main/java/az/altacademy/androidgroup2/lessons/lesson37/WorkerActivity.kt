package az.altacademy.androidgroup2.lessons.lesson37

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.ActivityWorkerBinding
import java.time.Duration
import java.util.UUID
import java.util.concurrent.TimeUnit

class WorkerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWorkerBinding
    private lateinit var uuid: UUID

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        uuid = UUID.randomUUID()

        binding.startButton.setOnClickListener {
            uuid = UUID.randomUUID()


            val constraints = Constraints.Builder()
                .setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

//            val map = mapOf(
//                "name" to "Kotlin"
//            )
//
//            val data = Data(map)

            Log.d("MyFirstWorkerTAG", "Creating work ...")

//            val firstRequest = PeriodicWorkRequestBuilder<MyFirstWorker>(
//                10, TimeUnit.SECONDS
//            ).setId(uuid)
//                .setInputData(data)
//                .setConstraints(constraints)
//                .build()

            val firstRequest = OneTimeWorkRequestBuilder<MyFirstWorker>().setId(uuid)
                .setInputData(createWorkData("Task 1"))
                .setConstraints(constraints)
                .build()

            val secondRequest = OneTimeWorkRequestBuilder<MyFirstWorker>()
                .setInputData(createWorkData("Task 2"))
                .setConstraints(constraints)
                .build()

            val thirdRequest = OneTimeWorkRequestBuilder<MyFirstWorker>()
                .setInputData(createWorkData("Task 3"))
                .setConstraints(constraints)
                .build()


            WorkManager.getInstance(this)
                .beginWith(firstRequest)
                .then(thirdRequest)
                .then(secondRequest)
                .enqueue()
        }

        binding.cancelButton.setOnClickListener {
            WorkManager.getInstance(this)
                .cancelWorkById(uuid)
        }


        WorkManager.getInstance(this)
            .getWorkInfoByIdLiveData(uuid)
            .observe(this) { work ->
                val data = work.outputData.getString("result")
                val state = work.state
                Log.d("MyFirstWorkerTAG", "Result ... $data State: ${state.name} ")
            }


    }


    private fun createWorkData(text: String): Data{
        return Data(mapOf("name" to text))
    }
}