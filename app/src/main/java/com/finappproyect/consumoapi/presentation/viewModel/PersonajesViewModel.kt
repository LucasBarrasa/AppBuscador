package com.finappproyect.consumoapi.presentation.viewModel

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.finappproyect.consumoapi.data.models.ItemCharacter
import com.finappproyect.consumoapi.databinding.ItemListBinding
import com.squareup.picasso.Picasso

class PersonajesViewModel(view: View) : RecyclerView.ViewHolder(view) {

    private var binding = ItemListBinding.bind(view)

    fun bind(personaje: ItemCharacter) {
        //Nombre
        binding.tvNombre.text = personaje.name
        Picasso.get().load(personaje.image).into(binding.ivImagen)
    }
}