package jp.kuskyst.poke_poke_dex_android.view.screen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import jp.kuskyst.poke_poke_dex_android.R
import jp.kuskyst.poke_poke_dex_android.databinding.FragmentListBinding
import jp.kuskyst.poke_poke_dex_android.view.component.PokemonItemClickListener
import jp.kuskyst.poke_poke_dex_android.view.component.PokemonsAdapter
import jp.kuskyst.poke_poke_dex_android.viewmodel.ListViewModel

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentListBinding.inflate(inflater, container, false)
        this.binding.pokemonList.layoutManager = LinearLayoutManager(this.context)
        this.binding.pokemonList.addItemDecoration(
            DividerItemDecoration(this.context, LinearLayoutManager(this.context).getOrientation()))

        this.viewModel.pokemons.observe(this.viewLifecycleOwner, Observer { it ->
            this.binding.pokemonList.adapter = PokemonsAdapter(it.results, this.requireContext(),
                object : PokemonItemClickListener {
                    override fun onItemClickListener(id: String) {
                        findNavController().navigate(R.id.action_listFragment_to_detailFragment,
                            Bundle().apply { putString("id", id) }
                        )
                    }
                }
            )
        })
        this.viewModel.getList(151, 0)

        return this.binding.root
    }

}