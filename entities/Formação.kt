package entities

import enumerations.StatusFormacao
import enumerations.TipoFormacao

data class Formação(val nome: String,
                    val duracao : Int = 12,
                    var status : StatusFormacao,
                    var tipo : TipoFormacao,
                    var conteudo : Conteúdo)
