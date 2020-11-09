package uk.co.sooce.rickandmorty.data.repository

import uk.co.sooce.rickandmorty.base.data.BaseRepository
import uk.co.sooce.rickandmorty.data.local.dao.CharacterDao
import uk.co.sooce.rickandmorty.data.remote.RemoteDataSource
import javax.inject.Inject

class RAMRepository @Inject constructor(
    private val localDataSource: CharacterDao,
    private val remoteDataSource: RemoteDataSource
) : BaseRepository() {

    fun getCharacter(id: Int) =
        performGetOperation(databaseQuery = { localDataSource.getCharacter(id) },
            networkCall = { remoteDataSource.getCharacter(id) },
            saveCallResult = { localDataSource.insertCharacter(it) })

    fun getCharacters() =
        performGetOperation(databaseQuery = { localDataSource.getAllCharacters() },
            networkCall = { remoteDataSource.getCharacterList() },
            saveCallResult = { localDataSource.insertCharacterList(it.results) })



}