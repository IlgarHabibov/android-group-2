package az.altacademy.androidgroup2.lessons.lesson25

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentLesson25Binding

class Lesson25Fragment : Fragment() {

    private lateinit var binding: FragmentLesson25Binding
    private val adapter = Lesson23Adapter()

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
        binding.save.setOnClickListener {
            save()
        }
        adapter.onDeleteClickListener { person ->
            delete(person)
        }
        getAllPersons()
    }

    private fun getAllPersons(){
        val persons: List<PersonEntity> = getDbInstance().getPersonDAO().getAllPersons()
        adapter.addList(persons)
    }

    private fun save(){
        val name = binding.inputName.text.toString()
        val surname = binding.inputSurname.text.toString()
        binding.inputName.text = null
        binding.inputSurname.text = null

        val person = PersonEntity(name = name, surname = surname)
        getDbInstance().getPersonDAO().addPerson(person)
        getAllPersons()
    }

    private fun delete(person: PersonEntity){
        getDbInstance().getPersonDAO().deletePerson(person)
        getAllPersons()
    }

    private fun getDbInstance(): AppDatabase{
        val db: AppDatabase =  Room.databaseBuilder(
            context = requireContext(),
            AppDatabase::class.java,
            "app-database"
        )
            .allowMainThreadQueries()
            .build()
        return db
    }
}