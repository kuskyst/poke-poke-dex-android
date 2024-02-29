package jp.kuskyst.poke_poke_dex_android.model.repository

import jp.kuskyst.poke_poke_dex_android.model.entity.DetailResponse
import jp.kuskyst.poke_poke_dex_android.model.service.PokeApiService
import jp.kuskyst.poke_poke_dex_android.model.entity.ListResponse
import jp.kuskyst.poke_poke_dex_android.model.entity.SpeciesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

class PokeApiRepository @Inject constructor(
    private val service: PokeApiService
) {

    suspend fun getList(limit: Int, offset: Int): ListResponse? {
        val response = this.service.getList(limit, offset)
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun getDetail(id: Int): DetailResponse? {
        val response = this.service.getDetail(id)
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun getSpecies(id: Int): SpeciesResponse? {
        val response = this.service.getSpecies(id)
        return if (response.isSuccessful) response.body() else null
    }

}
