package jp.kuskyst.poke_poke_dex_android

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import jp.kuskyst.poke_poke_dex_android.viewmodel.ListViewModel
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(AndroidJUnit4::class)
class ListViewModelTest {

    @get:Rule
    private var hiltRule = HiltAndroidRule(this)

    @Inject lateinit var target: ListViewModel

    @Before
    fun setUp() {
        this.hiltRule.inject()
    }

    @Test
    @Composable
    fun getList_success() {
        this.target = hiltViewModel()
        target.getList(151, 0)
        assertEquals(151, target.pokemons.value!!.results.size)
    }

}