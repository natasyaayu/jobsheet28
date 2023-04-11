package com.example.natasya28

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.DataOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets
import java.security.KeyStore.TrustedCertificateEntry
import java.util.concurrent.Executors
import kotlin.math.log

class tambahactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambahactivity)

        val nama = findViewById<EditText>(R.id.namaEdit)
        val nis = findViewById<EditText>(R.id.nisEdit)
        val jk = findViewById<EditText>(R.id.jkEdit)
        val kelas = findViewById<EditText>(R.id.kelasEdit)
        val simpanbtn= findViewById<Button>(R.id.simpanbtn)
        simpanbtn.setOnClickListener() {
            simpanSiswa(Siswa(nis.text.toString(), nama.text.toString(), jk.text.toString(), kelas.text.toString()))
        }
    }

        fun simpanSiswa(siswa: Siswa) {
            val handler = Handler(Looper.getMainLooper())
            val executor = Executors.newSingleThreadExecutor()

            executor.execute {
                val url = URL("http://192.168.147.20/server_siswa/tambah.php")
                val client: HttpURLConnection
                val uriBuilder = Uri.Builder()
                    .appendQueryParameter("nis", siswa.nis)
                    .appendQueryParameter("nama", siswa.nama)
                    .appendQueryParameter("jk", siswa.jk)
                    .appendQueryParameter("kelas", siswa.kelas)
                    .build()
                val params = uriBuilder.toString().replace("?", "")
                val postData = params.toByteArray(StandardCharsets.UTF_8)

                try {
                    client = url.openConnection() as HttpURLConnection
                    client.requestMethod = "POST"
                    client.setRequestProperty("content-Type", "application/x-www-form-urlencoded")
                    client.setRequestProperty("Accept", "application/json")
                    client.doInput = true
                    client.doOutput = true
                    val dataOutputStream = DataOutputStream(client.outputStream)
                    dataOutputStream.write(postData)

                    Log.e(TAG, "hasil GET: " + client.responseCode)
                    if (client.responseCode == HttpURLConnection.HTTP_OK) {
                        finish()
                    }

                } catch (e: java.lang.Exception) {
                    Log.e(TAG, "onCreate: ", e)

                }

                handler.post {
                    Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
