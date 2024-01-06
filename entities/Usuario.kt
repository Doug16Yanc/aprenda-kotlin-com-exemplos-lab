package entities

import enumerations.NivelUsuario

data class Usuario(
    val id : Int,
    var nome : String,
    var email : String,
    var login : String,
    var senha : String,
    var nivel : NivelUsuario,
    var formacao : Formação,
    val conteudosFinalizados: MutableList<Conteúdo> = mutableListOf()
)
