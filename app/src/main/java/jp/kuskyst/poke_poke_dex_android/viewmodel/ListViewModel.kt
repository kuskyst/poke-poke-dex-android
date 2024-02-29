package jp.kuskyst.poke_poke_dex_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.kuskyst.poke_poke_dex_android.model.repository.PokeApiRepository
import jp.kuskyst.poke_poke_dex_android.model.entity.ListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: PokeApiRepository)
: ViewModel() {

    val pokemons = MutableLiveData<ListResponse>()

    fun getList(limit: Int, offset: Int) {
        this.viewModelScope.launch(Dispatchers.IO) {
            pokemons.postValue(repository.getList(limit, offset))
        }
    }

}