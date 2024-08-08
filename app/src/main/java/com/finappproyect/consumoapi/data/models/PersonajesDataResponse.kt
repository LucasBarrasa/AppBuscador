package com.finappproyect.consumoapi.data.models

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("results") val results: List<ItemCharacter>
)

data class ItemCharacter (
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String
)
