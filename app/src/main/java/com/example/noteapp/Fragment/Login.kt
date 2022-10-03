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
import com.example.noteapp.databinding.FragmentLoginBinding

class Login : Fragment() {

    lateinit var binding: FragmentLoginBinding
    lateinit var sharedprefs: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedprefs = requireContext().getSharedPreferences("data", Context.MODE_PRIVATE)

        binding.textBelumPunyaAkun.setOnClickListener{
            Navigation.findNavController(requireView()).navigate(R.id.action_login_to_regist)
        }
        binding.btnLogin.setOnClickListener{
            if ((binding.edtUsername.text.toString() == sharedprefs.getString("username", "")) &&
                (binding.edtPass.text.toString() == sharedprefs.getString("password", ""))){
                Navigation.findNavController(requireView()).navigate(R.id.action_login_to_home2)
            }else if(binding.edtPass.text.toString().isEmpty() || binding.edtUsername.text.toString().isEmpty()){
                Toast.makeText(context, "Isi semua data dengan benar", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context, "Username/Password Salah!", Toast.LENGTH_LONG).show()
            }
        }
    }

}