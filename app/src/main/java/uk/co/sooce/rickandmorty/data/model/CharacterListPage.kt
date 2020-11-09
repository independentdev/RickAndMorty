package uk.co.sooce.rickandmorty.data.model


import com.google.gson.annotations.SerializedName

data class CharacterListPage(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<MovieCharacter>
)