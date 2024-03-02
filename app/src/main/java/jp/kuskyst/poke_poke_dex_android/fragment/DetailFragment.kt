package jp.kuskyst.poke_poke_dex_android.fragment

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
import jp.kuskyst.poke_poke_dex_android.adapter.FlavorTextsAdapter
import jp.kuskyst.poke_poke_dex_android.viewmodel.DetailViewModel
import jp.kuskyst.poke_poke_dex_android.viewmodel.DetailViewModel.ImageType.*

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private val vm: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentDetailBinding.inflate(inflater, container, false)
        this.binding.vm = this.vm
        this.binding.lifecycleOwner = this
        this.binding.flavorTextList.layoutManager = LinearLayoutManager(this.context)
        this.binding.flavorTextList.addItemDecoration(
            DividerItemDecoration(this.context, LinearLayoutManager(this.context).orientation))
        this.vm.species.observe(this.viewLifecycleOwner) {
            this.binding.flavorTextList.adapter = FlavorTextsAdapter(
                it.flavor_text_entries.filter { v -> v.language.name == "ja" }.toTypedArray())
        }
        this.vm.image1.observe(this.viewLifecycleOwner) { it.into(this.binding.pokemonImage1) }
        this.vm.image2.observe(this.viewLifecycleOwner) { it.into(this.binding.pokemonImage2) }
        this.vm.image3.observe(this.viewLifecycleOwner) { it.into(this.binding.pokemonImage3) }
        this.vm.image4.observe(this.viewLifecycleOwner) { it.into(this.binding.pokemonImage4) }
        this.binding.btnBack.setOnClickListener { this.activity?.onBackPressed() }

        this.vm.getDetail(args.id)
        this.vm.getSpecies(args.id)
        this.vm.getImage(args.id, DEFAULT)
        this.vm.getImage(args.id, DEFAULT_BACK)
        this.vm.getImage(args.id, SHINY)
        this.vm.getImage(args.id, SHINY_BACK)

        return this.binding.root
    }

}