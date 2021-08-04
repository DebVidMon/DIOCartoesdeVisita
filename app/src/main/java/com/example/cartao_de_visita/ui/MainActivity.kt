package com.example.cartao_de_visita.ui

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.cartao_de_visita.App
import com.example.cartao_de_visita.databinding.ActivityMainBinding
import com.example.cartao_de_visita.util.Image

class MainActivity: AppCompatActivity() {

private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
private val mainViewModel: MainViewModel by viewModels {
    MainViewModelFactory((application as App).repository)
}
private val adapter by lazy { CardAdapter() }

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    setUpPermissions()
    binding.rvCards.adapter = adapter
    getAllBusinessCard()
    insertListeners()
}

private fun setUpPermissions() {
    ActivityCompat.requestPermissions(
        this,
        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
        1
    )
    ActivityCompat.requestPermissions(
        this,
        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
        1
    )
}

private fun insertListeners() {
    binding.fabAdd.setOnClickListener {
        val intent = Intent(this, AddCard::class.java)
        startActivity(intent)
    }
    adapter.listenerShare = { card ->
        Image.share(this@MainActivity, card)
    }
}

private fun getAllBusinessCard() {
    mainViewModel.getAll().observe(this, { businessCards ->
        adapter.submitList(businessCards)
    })
}

}