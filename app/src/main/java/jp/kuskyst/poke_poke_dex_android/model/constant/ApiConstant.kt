package jp.kuskyst.poke_poke_dex_android.model.constant

class ApiConstant {
    companion object {
        const val pokeApiBaseUrl = "https://pokeapi.co/"
        const val ImageBaseUrl = "https://raw.githubusercontent.com/POKEAPI/sprites/master/sprites/pokemon"
        fun image1Url(id: String) = "$ImageBaseUrl/$id.png"
        fun image2Url(id: String) = "$ImageBaseUrl/back/$id.png"
        fun image3Url(id: String) = "$ImageBaseUrl/shiny/$id.png"
        fun image4Url(id: String) = "$ImageBaseUrl/back/shiny/$id.png"
    }
}