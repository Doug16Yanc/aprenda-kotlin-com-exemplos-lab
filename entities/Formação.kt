package entities

import enumerations.StatusFormacao

data class Formação(val nome: String,
                    val duracao : Int = 12,
                    var status : StatusFormacao,
                    var conteudo : Conteúdo)
