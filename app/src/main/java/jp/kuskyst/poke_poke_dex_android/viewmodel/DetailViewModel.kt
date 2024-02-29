package jp.kuskyst.poke_poke_dex_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.kuskyst.poke_poke_dex_android.model.entity.DetailResponse
import jp.kuskyst.poke_poke_dex_android.model.entity.SpeciesResponse
import jp.kuskyst.poke_poke_dex_android.model.repository.PokeApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: PokeApiRepository)
: ViewModel() {

    val detail = MutableLiveData<DetailResponse>()
    val species = MutableLiveData<SpeciesResponse>()

    fun getDetail(id: Int) {
        this.viewModelScope.launch(Dispatchers.IO) {
            detail.postValue(repository.getDetail(id))
        }
    }

    fun getSpecies(id: Int) {
        this.viewModelScope.launch(Dispatchers.IO) {
            species.postValue(repository.getSpecies(id))
        }
    }

}
