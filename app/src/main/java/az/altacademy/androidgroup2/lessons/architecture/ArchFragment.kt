package az.altacademy.androidgroup2.lessons.architecture

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentArchBinding


class ArchFragment : Fragment() {

    private lateinit var binding: FragmentArchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentArchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.seasonalPay.setOnClickListener {

            val discount = SeasonalDiscount()
            val finallyPrice = discount.applyPrice(10.0)
            Toast.makeText(requireContext(), "Success price = $finallyPrice", Toast.LENGTH_SHORT).show()
        }

        binding.monthlyPay.setOnClickListener {
            val discount = MonthlyDiscount()
            val finallyPrice = discount.applyPrice( 10.0)
            Toast.makeText(requireContext(), "Success price = $finallyPrice", Toast.LENGTH_SHORT).show()

        }

        binding.yearlylPay.setOnClickListener {
            val discount = YearlyDiscount()
            val finallyPrice = discount.applyPrice(10.0)
            Toast.makeText(requireContext(), "Success price = $finallyPrice", Toast.LENGTH_SHORT).show()
        }

        binding.eat.setOnClickListener {
            val penguin = Penguin()
            birdEating(penguin)
        }

        binding.fly.setOnClickListener {
            val owl = Owl()
            birdFlying(owl)
        }

        binding.startWork.setOnClickListener {
            val programmer = Programmer()
            val designer = Designer()
            startWorking(programmer)
        }
    }

    private fun birdFlying(flyable: Flyable){
        Toast.makeText(requireContext(), "${flyable.fly()}", Toast.LENGTH_SHORT).show()
    }

    private fun birdEating(bird: Bird){
        Toast.makeText(requireContext(), "${bird.eat()}", Toast.LENGTH_SHORT).show()
    }

    private fun startWorking(worker: Worker){
        Toast.makeText(requireContext(), "Work started. ${worker.startWorking()}", Toast.LENGTH_SHORT).show()
    }

}