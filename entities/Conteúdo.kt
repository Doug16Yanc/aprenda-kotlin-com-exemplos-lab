package entities

import enumerations.NivelConteudo
import enumerations.StatusConteudo

data class Conteúdo(
                    var id : Int,
                    var nome: String,
                    var duracao: Int,
                    var nivel : NivelConteudo,
                    var status : StatusConteudo
    )
