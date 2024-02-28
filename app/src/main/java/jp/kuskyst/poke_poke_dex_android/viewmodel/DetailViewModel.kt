package jp.kuskyst.poke_poke_dex_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kuskyst.poke_poke_dex_android.model.entity.DetailResponse
import jp.kuskyst.poke_poke_dex_android.model.entity.SpeciesResponse
import jp.kuskyst.poke_poke_dex_android.model.repository.PokeApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    val detail = MutableLiveData<DetailResponse>()
    val species = MutableLiveData<SpeciesResponse>()
    private val repository: PokeApiRepository = PokeApiRepository()

    fun getDetail(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            detail.postValue(repository.getDetail(id))
        }
    }

    fun getSpecies(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            species.postValue(repository.getSpecies(id))
        }
    }

}
