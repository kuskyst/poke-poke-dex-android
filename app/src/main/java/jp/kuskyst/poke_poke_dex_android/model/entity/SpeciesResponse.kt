package jp.kuskyst.poke_poke_dex_android.model.entity

import jp.kuskyst.poke_poke_dex_android.model.entity.child.EggGroups
import jp.kuskyst.poke_poke_dex_android.model.entity.child.FlavorTextEntries
import jp.kuskyst.poke_poke_dex_android.model.entity.child.Genera

data class SpeciesResponse(
    val id: String,
    val egg_groups: Array<EggGroups>,
    val flavor_text_entries: Array<FlavorTextEntries>,
    val genera: Array<Genera>
)
