package jp.kuskyst.poke_poke_dex_android.model.entity

import jp.kuskyst.poke_poke_dex_android.model.entity.child.Results

data class ListResponse(
    val count: Int,
    val results: Array<Results>
)
