package jp.kuskyst.poke_poke_dex_android.view.component

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jp.kuskyst.poke_poke_dex_android.R
import jp.kuskyst.poke_poke_dex_android.databinding.RowPokemonBinding
import jp.kuskyst.poke_poke_dex_android.model.constant.ApiConstant
import jp.kuskyst.poke_poke_dex_android.model.entity.child.Results

class PokemonsAdapter(
            private val pokemons: Array<Results>,
            private val context: Context,
            private val listener: PokemonItemClickListener)
        : RecyclerView.Adapter<PokemonsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RowPokemonBinding = DataBindingUtil.inflate(inflater, R.layout.row_pokemon, parent, false)
        return PokemonsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonsViewHolder, position: Int) {
        holder.binding.id = Uri.parse(this.pokemons[position].url.toString()).lastPathSegment!!
        holder.binding.pokemon = this.pokemons[position]
        Glide.with(this.context)
            .load(Uri.parse(ApiConstant.image1Url.replace(ApiConstant.replaceId, holder.binding.id.toString())))
            .into(holder.binding.pokemonRowImage)
        holder.itemView.setOnClickListener {
            this.listener.onItemClickListener(holder.binding.id.toString())
        }
    }

    override fun getItemCount(): Int = this.pokemons.size

}