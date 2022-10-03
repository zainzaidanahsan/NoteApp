package com.example.noteapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.noteapp.R
import com.example.noteapp.Room.DataNote
import com.example.noteapp.databinding.FragmentDetailBinding


class Detail : Fragment() {
lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var getData = arguments?.getSerializable("dataDetail") as DataNote
        binding.textIsi.text = getData.note
        binding.textJudul.text = getData.tittle

        binding.btnback.setOnClickListener{
            Navigation.findNavController(requireView()).navigate(R.id.action_detail_to_home2)
        }
    }


}