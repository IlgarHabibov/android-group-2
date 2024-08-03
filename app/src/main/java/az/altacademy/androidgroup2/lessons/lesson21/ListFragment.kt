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
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentListBinding
import az.altacademy.androidgroup2.databinding.FragmentMyDialogBinding
import az.altacademy.androidgroup2.lessons.lesson22.PagerFragment
import az.altacademy.androidgroup2.lessons.lesson23.MyDialogFragment


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
            val args = bundleOf(
                "name" to "Kotlin"
            )
            findNavController().navigate(R.id.action_list_to_addNote, args)

        }

        binding?.nextButton?.setOnClickListener {
//            findNavController().navigate(R.id.action_list_to_sub_main_graph)
//            showAlert()

//            MyDialogFragment().show(childFragmentManager, "MyDialogFragment")

            findNavController().navigate(R.id.action_list_to_myDialog)

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

    private fun showAlert(){

        val alert = AlertDialog.Builder(requireContext())
//        val inflater = LayoutInflater.from(requireContext())
//        val b = FragmentMyDialogBinding.inflate(inflater)
//
//        alert.create().apply {
//            b.close.setOnClickListener {
//                dismiss()
//            }
//            setView(b.root)
//        }.show()


        val positiveDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_home)
        alert.setPositiveButtonIcon(positiveDrawable)
        alert.setTitle("My alert title")
        alert.setPositiveButton("Yes"){ dialog, _ ->
            Toast.makeText(requireContext(), "Positive button clicked", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }


        val negativeDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_profile)
        alert.setNegativeButtonIcon(negativeDrawable)

        alert.setNegativeButton("No"){ dialog, _ ->
            Toast.makeText(requireContext(), "Negative button clicked", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        val neutralDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_close)
        alert.setNeutralButtonIcon(neutralDrawable)
        alert.setNeutralButton("Not sure"){ dialog, _ ->
            Toast.makeText(requireContext(), "Neutral button clicked", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        alert.show()

    }

}