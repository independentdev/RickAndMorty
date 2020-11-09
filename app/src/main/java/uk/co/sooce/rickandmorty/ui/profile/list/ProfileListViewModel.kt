package uk.co.sooce.rickandmorty.ui.profile.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import uk.co.sooce.rickandmorty.data.repository.RAMRepository
import javax.inject.Inject

class ProfileListViewModel @ViewModelInject constructor(repository :  RAMRepository) : ViewModel() {

    val characters = repository.getCharacters()

}