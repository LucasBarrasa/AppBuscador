package com.finappproyect.consumoapi.presentation.ui.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.finappproyect.consumoapi.data.api.ApiService
import com.finappproyect.consumoapi.databinding.ActivityMainBinding
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
        //Adapters
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
            Log.d("pruebaConexion", "mi response: ${myResponse}")
            Log.d("pruebaConexion", "mi response: ${myResponse.isSuccessful}")
            Log.d("pruebaConexion", "mi response: ${myResponse.body()}")
            Log.d("pruebaConexion", "mi response: ${myResponse.body()?.results}")

            if (myResponse.isSuccessful) {
                val characters = myResponse.body()?.results ?: emptyList()
                characters.forEach { character ->
                    Log.d("pruebaConexion", "Character name: ${character.name}, image: ${character.image}")
                }
            } else {
                Log.e("pruebaConexion", "Error: ${myResponse.errorBody()?.string()}")
            }

            /*val myResponse = retrofit.create(ApiService::class.java).getCharacterByName(query)

            Log.i("pruebaConexion", "my response: ${myResponse}")
            Log.i("pruebaConexion", "my response body: ${myResponse.body()}")
            Log.i("pruebaConexion", "my response results: ${myResponse.body()?.results}")
            Log.i("pruebaConexion", "my response susccesful: ${myResponse.isSuccessful}")
            *//*if (myResponse) {
               val response = myResponse.body()
                if (response?.result != null){
                    Log.i("pruebaConexion", "Obtencion de datos exitosa")
                    Log.i("pruebaConexion", "los datos son: ${response.result}")
                }
            } else {
                Log.i("pruebaConexion", "Error al obtener la respuesta")
            }*/
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