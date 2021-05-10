package com.example.navigasiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_data_pembeli.*
import kotlinx.android.synthetic.main.activity_data_pembeli.txtHarga
import kotlinx.android.synthetic.main.activity_data_pembeli.txtNamaProduk
import kotlinx.android.synthetic.main.activity_data_pembeli.txtQty
import kotlinx.android.synthetic.main.activity_data_pembeli.txtTotHarga
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.activity_struk.*

class Struk : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_struk)

        val actionBar : ActionBar? = supportActionBar
        actionBar!!.setDisplayShowHomeEnabled(true)
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        val intent      = intent

        val aNama        = intent.getStringExtra("nm")
        val aTelp       = intent.getStringExtra("telp")
        val aAlamat     = intent.getStringExtra("alamat")
        val aProduct    = intent.getStringExtra("name")
        val aHarga      = intent.getIntExtra("price", 0)
        val aQty        = intent.getIntExtra("qty", 0)
        val aTot        = intent.getIntExtra("tot", 0)


        actionBar.setTitle("struck " + aProduct)

        txtnama.text             = aNama
        txtTelp.text             = aTelp
        txtAlamat.text           = aAlamat
        txtNamaProduk1.text      = aProduct
        txtHarga1.text           = aHarga.toString()
        txtQty1.text             = aQty.toString()
        txtTotHarga1.text        = aTot.toString()

        }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

