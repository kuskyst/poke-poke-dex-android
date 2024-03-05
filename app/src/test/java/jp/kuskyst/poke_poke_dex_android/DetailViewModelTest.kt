package jp.kuskyst.poke_poke_dex_android

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bumptech.glide.Glide
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import jp.kuskyst.poke_poke_dex_android.model.repository.PokeApiRepository
import jp.kuskyst.poke_poke_dex_android.model.service.PokeApiService
import jp.kuskyst.poke_poke_dex_android.viewmodel.DetailViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(AndroidJUnit4::class)
class DetailViewModelTest {

    private lateinit var target: DetailViewModel
    private val context: Context = RuntimeEnvironment.application;

    @Before
    fun setUp() {
        this.target = DetailViewModel(
            PokeApiRepository(
                Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApiService::class.java))
        , Glide.with(this.context))
    }

    @Test
    fun getDetail_success() {
        target.getDetail("330")
        target.detail.observeForever {
            assertEquals(330, it.id)
            assertEquals("flygon", it.name)
            assertEquals(6, it.stats.size)
            assertEquals(2, it.types.size)
        }
    }


    @Test
    fun getSpecies_success() {
        target.getSpecies("444")
        target.species.observeForever {
            assertEquals(444, it.id)
            assertNotEquals(0, it.flavor_text_entries.size)
            assertNotEquals(0, it.egg_groups.size)
            assertNotEquals(0, it.genera.size)
        }
    }

}