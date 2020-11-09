package uk.co.sooce.rickandmorty.ui.profile.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import uk.co.sooce.rickandmorty.base.data.model.Resource
import uk.co.sooce.rickandmorty.data.model.MovieCharacter
import uk.co.sooce.rickandmorty.data.repository.RAMRepository

class ProfileDetailViewModel @ViewModelInject constructor(private val ramRepository: RAMRepository) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _character = _id.switchMap { id ->
        ramRepository.getCharacter(id)
    }
    val character: LiveData<Resource<MovieCharacter>> = _character


    fun start(id: Int) {
        _id.value = id
    }
}