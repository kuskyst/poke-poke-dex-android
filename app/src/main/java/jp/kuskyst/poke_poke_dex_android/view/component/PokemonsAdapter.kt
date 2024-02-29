package jp.kuskyst.poke_poke_dex_android.view.component

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import jp.kuskyst.poke_poke_dex_android.BuildConfig
import jp.kuskyst.poke_poke_dex_android.R
import jp.kuskyst.poke_poke_dex_android.databinding.RowPokemonBinding
import jp.kuskyst.poke_poke_dex_android.model.entity.child.Results
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonsAdapter @Inject constructor (
    private val glide: RequestManager
) : RecyclerView.Adapter<PokemonsViewHolder>() {

    lateinit var pokemons: Array<Results>
    lateinit var listener: PokemonItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RowPokemonBinding = DataBindingUtil.inflate(inflater, R.layout.row_pokemon, parent, false)
        return PokemonsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonsViewHolder, position: Int) {
        holder.binding.id = Uri.parse(this.pokemons[position].url.toString()).lastPathSegment!!
        holder.binding.pokemon = this.pokemons[position]
        this.glide
            .load(Uri.parse("${BuildConfig.IMAGE_URL}/${holder.binding.id}.png"))
            .into(holder.binding.pokemonRowImage)
        holder.itemView.setOnClickListener {
            this.listener.onItemClickListener(holder.binding.id.toString())
        }
    }

    override fun getItemCount(): Int = this.pokemons.size

}