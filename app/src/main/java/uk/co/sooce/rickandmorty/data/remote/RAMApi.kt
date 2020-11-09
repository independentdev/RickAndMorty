package uk.co.sooce.rickandmorty.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uk.co.sooce.rickandmorty.data.model.MovieCharacter
import uk.co.sooce.rickandmorty.data.model.CharacterListPage

interface RAMApi {

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int) : Response<MovieCharacter>

    @GET("character/")
    suspend fun getCharacterList(@Query("page") page: Int) : Response<CharacterListPage>

    @GET("character/")
    suspend fun getCharacterList() : Response<CharacterListPage>


}