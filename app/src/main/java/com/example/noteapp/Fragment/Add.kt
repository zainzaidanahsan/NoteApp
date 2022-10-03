package com.example.noteapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.noteapp.R
import com.example.noteapp.Room.DataNote
import com.example.noteapp.Room.NoteDatabase
import com.example.noteapp.ViewModel.NoteViewModel
import com.example.noteapp.databinding.FragmentAddBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class Add : Fragment() {
    lateinit var binding : FragmentAddBinding
    var dbNote : NoteDatabase? = null
    lateinit var noteVm : NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater,container,false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteVm = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java)
        binding.btnSaveNote.setOnClickListener{
            addNote()
            Navigation.findNavController(requireView()).navigate(R.id.action_add2_to_home2)
        }
    }
    fun addNote(){
        GlobalScope.async {
            var title = binding.noteTitle.text.toString()
            var note = binding.noteContent.text.toString()

            dbNote!!.noteDao().insertNote(DataNote(0,title, note))
        }
    }
}