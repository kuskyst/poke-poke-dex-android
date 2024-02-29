package jp.kuskyst.poke_poke_dex_android.view.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import jp.kuskyst.poke_poke_dex_android.databinding.FragmentDetailBinding
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
        this.viewModel.detail.observe(viewLifecycleOwner, Observer { it ->
            Log.d("res", it.toString())
        })
        this.viewModel.species.observe(viewLifecycleOwner, Observer { it ->
            Log.d("res", it.toString())
        })
        this.viewModel.getDetail(Integer.parseInt(args.id))
        this.viewModel.getSpecies(Integer.parseInt(args.id))
        return this.binding.root
    }

}

