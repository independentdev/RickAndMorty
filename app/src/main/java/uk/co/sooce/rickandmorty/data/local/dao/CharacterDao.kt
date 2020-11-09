package uk.co.sooce.rickandmorty.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uk.co.sooce.rickandmorty.data.model.MovieCharacter

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character WHERE id = :id")
    fun getCharacter(id: Int) : LiveData<MovieCharacter>

    @Query("SELECT * FROM character")
    fun getAllCharacters() : LiveData<List<MovieCharacter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: MovieCharacter)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterList(characters: List<MovieCharacter>)
}