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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentListBinding.inflate(inflater, container, false)
        this.binding.pokemonList.layoutManager = LinearLayoutManager(this.context)
        this.binding.pokemonList.addItemDecoration(
            DividerItemDecoration(this.context, LinearLayoutManager(this.context).orientation))
        this.binding.pokemonList.applySkeleton(R.layout.row_pokemon, 16).showSkeleton()
        this.viewModel.pokemons.observe(this.viewLifecycleOwner) {
            this.adapter.pokemons = it.results
            this.adapter.listener = object : PokemonItemClickListener {
                override fun onItemClickListener(id: String) {
                    findNavController().navigate(R.id.action_listFragment_to_detailFragment,
                        Bundle().apply { putString("id", id) }
                    )
                }
            }
            this.binding.pokemonList.adapter = this.adapter
        }
        this.viewModel.getList(151, 0)

        return this.binding.root
    }

}