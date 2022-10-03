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
import com.example.noteapp.databinding.FragmentEditBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class Edit : Fragment() {
    lateinit var binding : FragmentEditBinding
    lateinit var vmNotes : NoteViewModel
    var DbNote: NoteDatabase? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DbNote = NoteDatabase.getInstance(requireContext())
        vmNotes = ViewModelProvider(this).get(NoteViewModel::class.java)
        var getData = arguments?.getSerializable("data") as DataNote
        binding.edtNewJudulEd.setText(getData.tittle)
        binding.edtNewNoteEd.setText(getData.note)

    binding.btnSubEd.setOnClickListener{
        GlobalScope.async {
            var getData = arguments?.getSerializable("note") as DataNote

            var title = binding.edtNewJudulEd.text.toString()
            var note = binding.edtNewNoteEd.text.toString()

            val editNote = DataNote(getData.id, title, note)
            vmNotes.updateNote(editNote)

            Navigation.findNavController(requireView()).navigate(R.id.action_edit_to_home2)
        }
    }
    }

}