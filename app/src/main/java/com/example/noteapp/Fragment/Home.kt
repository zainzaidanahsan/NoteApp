package com.example.noteapp.Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.Adapter.NoteAdapter
import com.example.noteapp.R
import com.example.noteapp.Room.DataNote
import com.example.noteapp.Room.NoteDatabase
import com.example.noteapp.ViewModel.NoteViewModel
import com.example.noteapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Home : Fragment() {
    lateinit var binding : FragmentHomeBinding
    lateinit var sharedprefs : SharedPreferences
    var DbNote : NoteDatabase? = null
    lateinit var adapterNote : NoteAdapter
    lateinit var noteVm : NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterNote = NoteAdapter(ArrayList())
        binding.rvHome.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvHome.adapter = adapterNote

        noteVm = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteVm.getAllNoteObservers().observe(viewLifecycleOwner, Observer {
            adapterNote.setData(it as ArrayList<DataNote>)
        })

        sharedprefs = requireContext().getSharedPreferences("data", Context.MODE_PRIVATE)
        binding.btnLogout.setOnClickListener(){
            val clear = sharedprefs.edit()
            clear.clear()
            clear.apply()
            Navigation.findNavController(requireView()).navigate(R.id.action_home2_to_login)
        }
        binding.btnAdd.setOnClickListener{
            Navigation.findNavController(requireView()).navigate(R.id.action_home2_to_add2)
        }

    }
    fun getAllNote(){
        GlobalScope.launch {
            var data = DbNote?.noteDao()?.getDataNote()
            run{
                adapterNote = NoteAdapter(data!!)
                binding.rvHome.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvHome.adapter = adapterNote
            }
        }
    }
}