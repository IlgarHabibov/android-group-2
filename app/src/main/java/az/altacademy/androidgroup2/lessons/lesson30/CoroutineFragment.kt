package az.altacademy.androidgroup2.lessons.lesson30

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentCoroutineBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text


class CoroutineFragment : Fragment() {

    private lateinit var binding: FragmentCoroutineBinding

    private var textJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoroutineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startButton.setOnClickListener {
            getData()
        }

        binding.cancelButton.setOnClickListener {
            cancelGettingData()
        }

        binding.statusButton.setOnClickListener {
            if ( textJob?.isActive == true){
                binding.statusText.text = "Is Active"
            }else if (textJob?.isCancelled == true){
                binding.statusText.text = "Is Canceled"
            }else if (textJob?.isCompleted == true){
                binding.statusText.text = "Is Completed"
            }else{
                binding.statusText.text = ""
            }

        }
    }

    private fun cancelGettingData() {
        textJob?.cancel()
        binding.loading.isVisible = false
    }

    private fun getData() {
        binding.resultText.text = ""
        textJob = lifecycleScope.launch(Dispatchers.Main) {
            binding.loading.isVisible = true
            val text = withContext(Dispatchers.IO + SupervisorJob()){
                delay(5000)
                "Success"
            }

            binding.loading.isVisible = false
            binding.resultText.text = text
        }



    }



    private fun cancelExample(){
        val parentJob = lifecycleScope.launch {

            val childJob1 = launch {

                val child1ChildJob1 = launch {

                }

                val child1ChildJob2 = launch {

                }

            }

            val childJob2 =  launch {

                val child2ChildJob2 = launch {

                }
            }

        }
    }


    private fun printText(text: String){
        Log.d("CoroutineFragmentTAG", text)
    }


}