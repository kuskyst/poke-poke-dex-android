package jp.kuskyst.poke_poke_dex_android.model.service

import jp.kuskyst.poke_poke_dex_android.model.entity.DetailResponse
import jp.kuskyst.poke_poke_dex_android.model.entity.ListResponse
import jp.kuskyst.poke_poke_dex_android.model.entity.SpeciesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {

    @GET("api/v2/pokemon")
    suspend fun getList(@Query("limit") limit: Int, @Query("offset") offset: Int): Response<ListResponse>

    @GET("api/v2/pokemon/{id}")
    suspend fun getDetail(@Path("id") id: String): Response<DetailResponse>

    @GET("api/v2/pokemon-species/{id}")
    suspend fun getSpecies(@Path("id") id: String): Response<SpeciesResponse>

}