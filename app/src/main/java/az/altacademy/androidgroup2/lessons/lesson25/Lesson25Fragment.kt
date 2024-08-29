package az.altacademy.androidgroup2.lessons.lesson25

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.room.Room
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentLesson25Binding
import az.altacademy.androidgroup2.lessons.lesson26.MyFirstViewModel

class Lesson25Fragment : Fragment() {

    private lateinit var binding: FragmentLesson25Binding
    private val adapter = Lesson23Adapter()
    private val viewModel by viewModels<MyFirstViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.createDb(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLesson25Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter

        viewModel.getAllPersons()?.observe(viewLifecycleOwner){ persons ->
            adapter.addList(persons)
        }

        binding.save.setOnClickListener {
            save()
        }
        adapter.onDeleteClickListener { person ->
            delete(person)
        }
    }

    private fun save(){
        val name = binding.inputName.text.toString()
        val surname = binding.inputSurname.text.toString()
        viewModel.savePerson(name, surname)
    }

    private fun delete(person: PersonEntity){
        viewModel.delete(person)
    }

    override fun onDestroy() {
        viewModel.destroyDB()
        super.onDestroy()
    }
}