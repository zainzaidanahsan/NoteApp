package com.example.noteapp.Adapter

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.os.NetworkOnMainThreadException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.Fragment.Home
import com.example.noteapp.MainActivity
import com.example.noteapp.R
import com.example.noteapp.Room.DataNote
import com.example.noteapp.Room.NoteDatabase
import com.example.noteapp.ViewModel.NoteViewModel
import com.example.noteapp.databinding.ItemNoteBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class NoteAdapter(var listNote: List<DataNote>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    var DBNote: NoteDatabase? = null
    lateinit var noteVm: NoteViewModel

    class ViewHolder(var binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        val view = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: NoteAdapter.ViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        holder.binding.txtJudul.text = listNote[position].tittle
        holder.binding.txtNote.text = listNote[position].note

        var id = listNote[position].id
        var judul = listNote[position].tittle
        var note = listNote[position].note
//        holder.binding.btnDel.setOnClickListener{
//            DBNote = NoteDatabase.getInstance(it.context)
//            GlobalScope.async {
//                    (holder.itemView.context as Home).getAllNote()
//            }
//        }
        holder.binding.btnDel.setOnClickListener {
            DBNote = NoteDatabase.getInstance(it.context)
            run {
                GlobalScope.async {
                    val delete = DBNote?.noteDao()!!.deleteNote(listNote[position])
                    (holder.itemView.context as Home).run {
                        (holder.itemView.context as Home).getAllNote()
                    }
                }
            }

            holder.binding.cardViewItem.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    val bundle = Bundle()
                    var sendData = DataNote(id, judul, note)
                    bundle.putSerializable("dataDetail", sendData)
                    Navigation.findNavController(holder.itemView)
                        .navigate(R.id.action_home2_to_detail, bundle)
                }
            })

            holder.binding.btnEdit.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    val bundle = Bundle()
                    bundle.putSerializable("data", listNote[position])
                    Navigation.findNavController(holder.itemView)
                        .navigate(R.id.action_home2_to_edit, bundle)
                }
            })


        }
    }

    override fun getItemCount(): Int {
        return listNote.size
    }
    fun setData(listNote: ArrayList<DataNote>) {
        this.listNote = listNote
    }
}
