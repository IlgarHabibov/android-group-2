package az.altacademy.androidgroup2.lessons.lesson21

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentListBinding
import az.altacademy.androidgroup2.lessons.lesson22.PagerFragment


class ListFragment : Fragment() {

    private var binding: FragmentListBinding? = null
    private val textAdapter = TextListAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.recyclerView?.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        val decor = SpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.dimen_10dp))
        binding?.recyclerView?.addItemDecoration(decor)
        binding?.recyclerView?.adapter = textAdapter

        binding?.addButton?.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.listContainer, AddNoteFragment())
                .addToBackStack("ListFragment")
                .commit()
        }

        textAdapter.onRemoveClickLister { position ->
            val alert = AlertDialog.Builder(requireContext())
                .setTitle("Silmeye eminsiniz")
                .setPositiveButton(
                    "Beli"
                ) { dialog, which ->
                    textAdapter.removeItem(position)
                }
                .setNegativeButton("Xeyr") { dialog, which ->
                    dialog.dismiss()
                }

            alert.show()

        }

        parentFragmentManager.setFragmentResultListener(
            AddNoteFragment.NOTE_RESULT,
            viewLifecycleOwner
        ) { key, args ->
            val title = args.getString(AddNoteFragment.TITLE)
            val noteText = args.getString(AddNoteFragment.NOTE)
            val note = NoteModel(title, noteText)
            textAdapter.addItem(note)
        }


    }

}