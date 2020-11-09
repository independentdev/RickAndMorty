package uk.co.sooce.rickandmorty.data.remote

import uk.co.sooce.rickandmorty.base.data.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val ramApi: RAMApi) : BaseDataSource() {

    suspend fun getCharacter(id: Int) = getResult { ramApi.getCharacter(id = id) }

    suspend fun getCharacterListPage(page: Int) = getResult { ramApi.getCharacterList(page) }

    suspend fun getCharacterList() = getResult { ramApi.getCharacterList() }
}