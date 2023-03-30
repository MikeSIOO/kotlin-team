package com.example.kotkin_team.profile.domain.model

data class Profile(
    val id: Int,
    val name: String,
    val secondName: String?,
    val image: String = "https://sun9-9.userapi.com/impf/wpKh_I1m4InpdEDsX31RH4Fh2eLb3j-Bo9iA4A/4VibiWNxgdg.jpg?size=604x453&quality=96&sign=b9428274e0de3250c73c22b04d9f9173&c_uniq_tag=LRvg-JVGoUInZ0oqC--Fg1GaVjy84CrtSmJ_NrV8n7M&type=album",
    val madeRecipes: Map<Long, MadeRecipe>? = emptyMap()
)
