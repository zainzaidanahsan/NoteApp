package com.example.noteapp.Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentRegistBinding


class Regist : Fragment() {

    lateinit var binding: FragmentRegistBinding
    lateinit var sharedprefs: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedprefs = requireContext().getSharedPreferences("data", Context.MODE_PRIVATE)
        binding.btnRegg.setOnClickListener{
            if (binding.edtUsername.text.toString().isEmpty() ||
                binding.edtPass.text.toString().isEmpty() ||
                binding.edtRepeatpass.text.toString().isEmpty()){
                Toast.makeText(context, "Lengkapi Data Jangan Sampai Kosong!", Toast.LENGTH_LONG)
                    .show()
            }else if(binding.edtRepeatpass.text.toString() != binding.edtPass.text.toString()){
                Toast.makeText(context, "Password Tidak Sesuai!", Toast.LENGTH_LONG)
                    .show()
            }else{
                addData()
                Toast.makeText(context, "Registrasi Berhasil", Toast.LENGTH_LONG)
                    .show()
                Navigation.findNavController(requireView()).navigate(R.id.action_regist_to_login)
            }
        }
    }
    fun addData(){
        var getUsername = binding.edtUsername.text.toString()
        var getPass = binding.edtPass.text.toString()

        var addData = sharedprefs.edit()
        addData.putString("username", getUsername)
        addData.putString("password", getPass)
        addData.apply()
    }

}