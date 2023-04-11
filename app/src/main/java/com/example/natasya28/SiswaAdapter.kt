package com.example.natasya28

import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class SiswaAdapter(private val data: ArrayList<Siswa>?): RecyclerView.Adapter<SiswaAdapter.siswaViewHolder>() {

    class siswaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nama = itemView.findViewById<TextView>(R.id.namaSiswa)
        private val nis = itemView.findViewById<TextView>(R.id.nisSiswa)
        private val jk = itemView.findViewById<TextView>(R.id.jkSiswa)
        private val kelas = itemView.findViewById<TextView>(R.id.kelasSiswa)
        private val editbtn = itemView.findViewById<TextView>(R.id.btnEdit)
        private val hapusbtn = itemView.findViewById<TextView>(R.id.btnHapus)
        fun bind(get: Siswa) {
            nama.text = get.nama
            nis.text = get.nis
            jk.text = get.jk
            kelas.text = get.kelas

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiswaAdapter.siswaViewHolder {
        return SiswaAdapter.siswaViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_siswa, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SiswaAdapter.siswaViewHolder, position: Int) {
        holder.bind(data?.get(position) ?: Siswa("", "", "",  ""))
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0

    }
}


