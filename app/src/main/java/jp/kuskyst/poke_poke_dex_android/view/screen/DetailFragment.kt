package jp.kuskyst.poke_poke_dex_android.view.screen

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import jp.kuskyst.poke_poke_dex_android.databinding.FragmentDetailBinding
import jp.kuskyst.poke_poke_dex_android.model.constant.ApiConstant
import jp.kuskyst.poke_poke_dex_android.view.component.FlavorTextsAdapter
import jp.kuskyst.poke_poke_dex_android.viewmodel.DetailViewModel

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentDetailBinding.inflate(inflater, container, false)
        this.binding.vm = this.viewModel
        this.binding.lifecycleOwner = this
        this.binding.flavorTextList.layoutManager = LinearLayoutManager(this.context)
        this.binding.flavorTextList.addItemDecoration(
            DividerItemDecoration(this.context, LinearLayoutManager(this.context).getOrientation()))
        this.viewModel.detail.observe(this.viewLifecycleOwner, Observer { it ->
        })
        this.viewModel.species.observe(this.viewLifecycleOwner, Observer { it ->
            this.binding.flavorTextList.adapter = FlavorTextsAdapter(
                it.flavor_text_entries.filter { it.language.name.equals("ja") }.toTypedArray())
        })

        Glide.with(this.requireContext())
            .load(Uri.parse(ApiConstant.image1Url.replace(ApiConstant.replaceId, args.id)))
            .into(this.binding.pokemonImage1)
        Glide.with(this.requireContext())
            .load(Uri.parse(ApiConstant.image2Url.replace(ApiConstant.replaceId, args.id)))
            .into(this.binding.pokemonImage2)
        Glide.with(this.requireContext())
            .load(Uri.parse(ApiConstant.image3Url.replace(ApiConstant.replaceId, args.id)))
            .into(this.binding.pokemonImage3)
        Glide.with(this.requireContext())
            .load(Uri.parse(ApiConstant.image4Url.replace(ApiConstant.replaceId, args.id)))
            .into(this.binding.pokemonImage4)

        this.viewModel.getDetail(Integer.parseInt(args.id))
        this.viewModel.getSpecies(Integer.parseInt(args.id))
        return this.binding.root
    }

}

