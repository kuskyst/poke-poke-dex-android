package jp.kuskyst.poke_poke_dex_android.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.applySkeleton
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import jp.kuskyst.poke_poke_dex_android.R
import jp.kuskyst.poke_poke_dex_android.databinding.FragmentListBinding
import jp.kuskyst.poke_poke_dex_android.ui.PokemonItemClickListener
import jp.kuskyst.poke_poke_dex_android.ui.PokemonsAdapter
import jp.kuskyst.poke_poke_dex_android.viewmodel.ListViewModel
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var binding: FragmentListBinding
    private val viewModel: ListViewModel by viewModels()
    lateinit var adapter: PokemonsAdapter @Inject set

    private val versionList = mutableMapOf(
        "赤緑" to listOf(151, 0), "金銀" to listOf(100, 151), "RS" to listOf(135, 251), "DP" to listOf(107, 386),
        "BW" to listOf(156, 493), "XY" to listOf(72, 649), "SM" to listOf(88, 721), "剣盾" to listOf(89, 809)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentListBinding.inflate(inflater, container, false)

        this.versionList.forEach { (k, _) ->
            this.binding.versionTab.addTab(
                this.binding.versionTab.newTab().apply { this.text = k })
        }
        this.binding.versionTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.pokemonList.applySkeleton(R.layout.row_pokemon, 16).showSkeleton()
                viewModel.getList(versionList[tab.text]!![0], versionList[tab.text]!![1])
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        this.binding.pokemonList.layoutManager = LinearLayoutManager(this.context)
        this.binding.pokemonList.addItemDecoration(
            DividerItemDecoration(this.context, LinearLayoutManager(this.context).orientation))
        this.binding.pokemonList.applySkeleton(R.layout.row_pokemon, 16).showSkeleton()
        this.viewModel.pokemons.observe(this.viewLifecycleOwner) {
            this.adapter.pokemons = it.results
            this.adapter.listener = object : PokemonItemClickListener {
                override fun onItemClickListener(id: String) {
                    findNavController().navigate(R.id.action_listFragment_to_detailFragment,
                        Bundle().apply { putString("id", id) })
                }
            }
            this.binding.pokemonList.adapter = this.adapter
        }

        this.viewModel.getList(151, 0)

        return this.binding.root
    }

}