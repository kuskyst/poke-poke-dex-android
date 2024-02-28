package jp.kuskyst.poke_poke_dex_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kuskyst.poke_poke_dex_android.model.repository.PokeApiRepository
import jp.kuskyst.poke_poke_dex_android.model.entity.ListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {
    val pokemons = MutableLiveData<ListResponse>()
    private val repository: PokeApiRepository = PokeApiRepository()

    fun getList(limit: Int, offset: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            pokemons.postValue(repository.getList(limit, offset))
        }
    }

}