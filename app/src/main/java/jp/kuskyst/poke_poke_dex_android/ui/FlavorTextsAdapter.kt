package jp.kuskyst.poke_poke_dex_android.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import jp.kuskyst.poke_poke_dex_android.R
import jp.kuskyst.poke_poke_dex_android.databinding.RowFlavortextBinding
import jp.kuskyst.poke_poke_dex_android.model.entity.child.FlavorTextEntries

class FlavorTextsAdapter(private val flavors: Array<FlavorTextEntries>) : RecyclerView.Adapter<FlavorTextsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlavorTextsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RowFlavortextBinding = DataBindingUtil.inflate(inflater, R.layout.row_flavortext, parent, false)
        return FlavorTextsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FlavorTextsViewHolder, position: Int) {
        holder.binding.version = flavors.get(position).version.name
        holder.binding.flavorText = flavors.get(position).flavor_text
    }

    override fun getItemCount(): Int = this.flavors.size

}