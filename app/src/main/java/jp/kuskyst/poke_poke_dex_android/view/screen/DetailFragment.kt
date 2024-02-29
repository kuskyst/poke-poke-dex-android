package jp.kuskyst.poke_poke_dex_android.view.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import jp.kuskyst.poke_poke_dex_android.R
import jp.kuskyst.poke_poke_dex_android.databinding.FragmentDetailBinding
import jp.kuskyst.poke_poke_dex_android.view.component.FlavorTextsAdapter
import jp.kuskyst.poke_poke_dex_android.viewmodel.DetailViewModel
import jp.kuskyst.poke_poke_dex_android.viewmodel.DetailViewModel.ImageType.*

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentDetailBinding.inflate(inflater, container, false)
        this.binding.vm = this.viewModel
        this.binding.lifecycleOwner = this
        this.binding.flavorTextList.layoutManager = LinearLayoutManager(this.context)
        this.binding.flavorTextList.addItemDecoration(
            DividerItemDecoration(this.context, LinearLayoutManager(this.context).orientation))
        this.viewModel.species.observe(this.viewLifecycleOwner) {
            this.binding.flavorTextList.adapter = FlavorTextsAdapter(
                it.flavor_text_entries.filter { v -> v.language.name == "ja" }.toTypedArray())
        }
        this.viewModel.image1.observe(this.viewLifecycleOwner) {
            it.into(this.binding.pokemonImage1)
        }
        this.viewModel.image2.observe(this.viewLifecycleOwner) {
            it.into(this.binding.pokemonImage2)
        }
        this.viewModel.image3.observe(this.viewLifecycleOwner) {
            it.into(this.binding.pokemonImage3)
        }
        this.viewModel.image4.observe(this.viewLifecycleOwner) {
            it.into(this.binding.pokemonImage4)
        }

        this.viewModel.getDetail(args.id)
        this.viewModel.getSpecies(args.id)
        this.viewModel.getImage(args.id, DEFAULT)
        this.viewModel.getImage(args.id, DEFAULT_BACK)
        this.viewModel.getImage(args.id, SHINY)
        this.viewModel.getImage(args.id, SHINY_BACK)

        return this.binding.root
    }

}

