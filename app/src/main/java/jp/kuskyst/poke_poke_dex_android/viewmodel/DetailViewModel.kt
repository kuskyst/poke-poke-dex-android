package jp.kuskyst.poke_poke_dex_android.viewmodel

import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.kuskyst.poke_poke_dex_android.BuildConfig
import jp.kuskyst.poke_poke_dex_android.model.entity.DetailResponse
import jp.kuskyst.poke_poke_dex_android.model.entity.SpeciesResponse
import jp.kuskyst.poke_poke_dex_android.model.repository.PokeApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: PokeApiRepository,
    private val glide: RequestManager
) : ViewModel() {

    val detail = MutableLiveData<DetailResponse>()
    val species = MutableLiveData<SpeciesResponse>()

    val image1 = MutableLiveData<RequestBuilder<Drawable>>()
    val image2 = MutableLiveData<RequestBuilder<Drawable>>()
    val image3 = MutableLiveData<RequestBuilder<Drawable>>()
    val image4 = MutableLiveData<RequestBuilder<Drawable>>()

    enum class ImageType(val path: String) {
        DEFAULT("/"),
        DEFAULT_BACK("/back/"),
        SHINY("/shiny/"),
        SHINY_BACK("/back/shiny/")
    }

    fun getDetail(id: String) {
        this.viewModelScope.launch(Dispatchers.IO) {
            detail.postValue(repository.getDetail(id))
        }
    }

    fun getSpecies(id: String) {
        this.viewModelScope.launch(Dispatchers.IO) {
            species.postValue(repository.getSpecies(id))
        }
    }

    fun getImage(id: String, type: ImageType) {
        when (type) {
            ImageType.DEFAULT -> image1.postValue(this.glide
                .load(Uri.parse("${BuildConfig.IMAGE_URL}${type.path}${id}.png")))
            ImageType.DEFAULT_BACK -> image2.postValue(this.glide
                .load(Uri.parse("${BuildConfig.IMAGE_URL}${type.path}${id}.png")))
            ImageType.SHINY -> image3.postValue(this.glide
                .load(Uri.parse("${BuildConfig.IMAGE_URL}${type.path}${id}.png")))
            ImageType.SHINY_BACK -> image4.postValue(this.glide
                .load(Uri.parse("${BuildConfig.IMAGE_URL}${type.path}${id}.png")))
        }
    }

}
