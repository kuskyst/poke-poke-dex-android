package jp.kuskyst.poke_poke_dex_android.model.entity.child

import jp.kuskyst.poke_poke_dex_android.model.entity.child.Language
import jp.kuskyst.poke_poke_dex_android.model.entity.child.Version

data class FlavorTextEntries(
    val flavor_text: String,
    val language: Language,
    val version: Version
)