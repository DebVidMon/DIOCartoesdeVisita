package com.example.cartao_de_visita

import android.app.Application
import com.example.cartao_de_visita.data.AppDatabase
import com.example.cartao_de_visita.data.CardRepository

class App : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { CardRepository(database.businessDao()) }
}