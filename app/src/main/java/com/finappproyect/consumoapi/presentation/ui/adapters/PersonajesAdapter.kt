package com.finappproyect.consumoapi.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finappproyect.consumoapi.R
import com.finappproyect.consumoapi.data.models.Character
import com.finappproyect.consumoapi.data.models.ItemCharacter
import com.finappproyect.consumoapi.presentation.viewModel.PersonajesViewModel

class PersonajesAdapter(private var itemList: List<ItemCharacter> = emptyList()) :
    RecyclerView.Adapter<PersonajesViewModel>() {

    fun updateListPersonajes(listadoPersonajes: List<ItemCharacter>){
        this.itemList = listadoPersonajes
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajesViewModel {
        return PersonajesViewModel(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: PersonajesViewModel, position: Int) {
        viewHolder.bind(itemList[position])
    }

    override fun getItemCount() = itemList.size
}