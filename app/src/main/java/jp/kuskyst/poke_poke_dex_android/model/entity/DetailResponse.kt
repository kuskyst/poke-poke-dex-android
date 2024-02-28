package jp.kuskyst.poke_poke_dex_android.model.entity

import jp.kuskyst.poke_poke_dex_android.model.entity.child.Sprites
import jp.kuskyst.poke_poke_dex_android.model.entity.child.Stats
import jp.kuskyst.poke_poke_dex_android.model.entity.child.Types

data class DetailResponse(
    val id: Int,
    val name: String,
    val height: Double,
    val weight: Double,
    val sprites: Sprites,
    val types: Array<Types>,
    val stats: Array<Stats>
)
