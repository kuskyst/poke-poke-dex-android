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
import com.faltenreich.skeletonlayout.applySkeleton
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import dagger.hilt.android.AndroidEntryPoint
import jp.kuskyst.poke_poke_dex_android.R
import jp.kuskyst.poke_poke_dex_android.databinding.FragmentDetailBinding
import jp.kuskyst.poke_poke_dex_android.ui.FlavorTextsAdapter
import jp.kuskyst.poke_poke_dex_android.viewmodel.DetailViewModel
import jp.kuskyst.poke_poke_dex_android.viewmodel.DetailViewModel.ImageType.*

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding
    private val vm: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

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
        this.binding.flavorTextList.applySkeleton(R.layout.row_flavortext, 7).showSkeleton()
        this.binding.skeletonDetail.showSkeleton()
        this.vm.detail.observe(this.viewLifecycleOwner) {
            this.binding.pokemonStatus.apply {
                this.xAxis.apply {
                    valueFormatter = IndexAxisValueFormatter(listOf("H", "A", "B", "C", "D", "S"))
                    this.position = XAxisPosition.BOTTOM
                    this.setDrawGridLines(false)
                }
                this.setScaleEnabled(false)
                this.description.isEnabled = false
                this.legend.isEnabled = false
                this.axisRight.isEnabled = false
                this.axisLeft.axisMinimum = 0F
                this.data = BarData(mutableListOf<IBarDataSet>(BarDataSet(it.stats.mapIndexed { index, stats ->
                    BarEntry(index.toFloat(), stats.base_stat.toFloat()) }, "status")))
                this.animateY(300)
            }.invalidate()
        }
        this.vm.species.observe(this.viewLifecycleOwner) {
            this.binding.flavorTextList.adapter = FlavorTextsAdapter(
                it.flavor_text_entries.filter { v -> v.language.name == "ja" }.toTypedArray())
            this.binding.skeletonDetail.showOriginal()
        }
        this.vm.image1.observe(this.viewLifecycleOwner) { it.into(this.binding.pokemonImage1) }
        this.vm.image2.observe(this.viewLifecycleOwner) { it.into(this.binding.pokemonImage2) }
        this.vm.image3.observe(this.viewLifecycleOwner) { it.into(this.binding.pokemonImage3) }
        this.vm.image4.observe(this.viewLifecycleOwner) { it.into(this.binding.pokemonImage4) }
        this.binding.btnBack.setOnClickListener { this.activity?.onBackPressed() }

        this.vm.getDetail(args.id)
        this.vm.getSpecies(args.id)
        this.vm.getImage(args.id, ALL)

        return this.binding.root
    }

}
