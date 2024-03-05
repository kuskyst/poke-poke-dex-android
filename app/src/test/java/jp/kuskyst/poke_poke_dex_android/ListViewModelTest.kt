package jp.kuskyst.poke_poke_dex_android

import android.net.Uri
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import jp.kuskyst.poke_poke_dex_android.model.repository.PokeApiRepository
import jp.kuskyst.poke_poke_dex_android.model.service.PokeApiService
import jp.kuskyst.poke_poke_dex_android.viewmodel.ListViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(AndroidJUnit4::class)
class ListViewModelTest {

    private lateinit var target: ListViewModel

    @Before
    fun setUp() {
        this.target = ListViewModel(PokeApiRepository(Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApiService::class.java)))
    }

    @Test
    fun getList_success() {
        target.getList(151, 0)
        target.pokemons.observeForever {
            assertEquals(151, it.results.size)
            assertEquals("bulbasaur", it.results.first().name)
            assertEquals("1", Uri.parse(it.results.first().url.toString()).lastPathSegment)
        }
    }

}
