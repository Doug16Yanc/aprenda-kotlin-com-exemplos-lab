package entities

import enumerations.NivelConteudo
import enumerations.NivelUsuario
import enumerations.StatusConteudo
import enumerations.StatusFormacao

data class Usuario(
    val id : Int,
    var nome : String,
    var email : String,
    var login : String,
    var senha : String,
    var nivel : NivelUsuario,
    var formacao : Formação,
    val conteudosKt: MutableList<Formação> = ArrayList(),
    val conteudosFinalizados: MutableList<Conteúdo> = mutableListOf()
) {
    init {
        conteudosKt.add(
            Formação(
                "Desenvolvimento Kotlin",
                12,
                StatusFormacao.NÃO_INICIADA,
                Conteúdo(
                    17364,
                    "Linguagem de programação Kotlin e suas funcionalidades",
                    3,
                    NivelConteudo.FÁCIL,
                    StatusConteudo.PENDENTE
                )
            )
        )
        conteudosKt.add(
            Formação(
                "Desenvolvimento Kotlin",
                12,
                StatusFormacao.NÃO_INICIADA,
                Conteúdo(
                    18934,
                    "Padrões de projeto em Kotlin",
                    4,
                    NivelConteudo.INTERMEDIÁRIO,
                    StatusConteudo.PENDENTE
                )
            )
        )
        conteudosKt.add(
            Formação(
                "Desenvolvimento Kotlin",
                12,
                StatusFormacao.NÃO_INICIADA,
                Conteúdo(
                    19756,
                    "Desenvolvimento backend com Spring Boot em Kotlin",
                    5,
                    NivelConteudo.AVANÇADO,
                    StatusConteudo.PENDENTE
                )
            )
        )
    }
}
