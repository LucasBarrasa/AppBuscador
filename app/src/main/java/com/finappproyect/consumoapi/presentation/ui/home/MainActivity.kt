package com.finappproyect.consumoapi.presentation.ui.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.finappproyect.consumoapi.data.services.ApiService
import com.finappproyect.consumoapi.databinding.ActivityMainBinding
import com.finappproyect.consumoapi.presentation.ui.adapters.PersonajesAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit = getRetrofit()

        initUI()
        initListener()

    }

    private fun initUI() {
    }

    private fun initListener() {

        binding.svBuscador.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchText(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun searchText(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(ApiService::class.java).getCharacterByName(query)

            if (myResponse.isSuccessful) {
                val response = myResponse.body()
                if (response != null){

                } else {
                    Log.e("pruebaConexion", "Error response es null: ${response}")
                }

            } else {
                Log.e("pruebaConexion", "Error myResponse: ${myResponse.errorBody()}")
            }

        }
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}