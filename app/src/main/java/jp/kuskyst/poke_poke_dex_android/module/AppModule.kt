package jp.kuskyst.poke_poke_dex_android.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.kuskyst.poke_poke_dex_android.model.constant.ApiConstant
import jp.kuskyst.poke_poke_dex_android.model.service.PokeApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePokeApiService(): PokeApiService {
        return Retrofit.Builder()
            .baseUrl(ApiConstant.pokeApiBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApiService::class.java)
    }

}