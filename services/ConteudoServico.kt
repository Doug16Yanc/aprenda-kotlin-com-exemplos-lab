package services

import entities.Conteúdo
import entities.Formação
import entities.Usuario
import enumerations.*
import interagePrimeiro
import utils.Utilidade

class ConteudoServico {
    companion object {

        fun defineKotlin(usuario: Usuario, formacao: Formação) {
            formacao.status = StatusFormacao.ANDAMENTO
            Utilidade.imprimeMensagem("Definição de andamento da formação:")
            println("O que você tem a aprender:\n")
            Utilidade.imprimeMensagem("Desenvolvimento backend com Kotlin e Spring Boot.\n")
            for (conteudos in usuario.conteudosKt) {
                Utilidade.imprimeMensagem(
                    "" +
                            "                   > Código do conteúdo : ${conteudos.conteudo.id}\n" +
                            "                   > Nome do conteúdo : ${conteudos.conteudo.nome}\n" +
                            "                   > Duração em horas : ${conteudos.conteudo.duracao}\n" +
                            "                   > Nível do conteúdo : ${conteudos.conteudo.nivel}\n\n"
                )
            }
            fazConteudosKt(usuario, formacao)
        }

        fun fazConteudosKt(usuario: Usuario, formacao: Formação) {
            if (validaConclusaoTotal(usuario)) {
                Utilidade.imprimeMensagem(
                    "Você concluiu toda a formação de Desenvolvimento backend com\n" +
                            "Kotlin e Spring Boot.\n"
                )
                return
            }
            Utilidade.imprimeMensagem(
                "Olá, aqui você dar início aos conteúdos,\n" +
                        "digite o id dos conteúdos que já concluiu ou -1 caso deseje sair do laço:\n"
            )

            do {
                println("Id do conteúdo ou -1 para finalizar:")
                val id = Utilidade.sc.nextInt()

                if (id == -1) {
                    break
                }

                val idEncontrado = usuario.conteudosKt.find { it.conteudo.id == id }

                if (idEncontrado != null) {
                    if (idEncontrado.conteudo.status != StatusConteudo.CONCLUÍDO) {
                        Utilidade.imprimeMensagem(
                            "Conteúdo ${idEncontrado.conteudo.id},\n" +
                                    "${idEncontrado.conteudo.nome} finalizado com sucesso.\n"
                        )
                        idEncontrado.conteudo.status = StatusConteudo.CONCLUÍDO
                        aumentaNivel(usuario, idEncontrado.conteudo)
                        usuario.conteudosFinalizados.add(idEncontrado.conteudo)
                    } else {
                        println("Você já concluiu esse conteúdo.")
                    }

                } else {
                    Utilidade.imprimeMensagem("Id de conteúdo não reconhecido.\n")
                }

                if (validaConclusaoTotal(usuario)) {
                    Utilidade.imprimeMensagem(
                        "Você concluiu toda a formação de Desenvolvimento backend com\n" +
                                "Kotlin e Spring Boot.\n"
                    )
                    formacao.status = StatusFormacao.CONCLUÍDA
                    FormacaoServico.comprovaFormacaoKt(usuario, formacao)
                    break
                }

            } while (true)

            UsuarioServico.interageUsuario(usuario, formacao)
        }
        fun listaConteudosFim(usuario : Usuario) {
            Utilidade.imprimeMensagem("Conteúdos finalizados. Mais um passo importante na carreira.\n")
            if (usuario.conteudosFinalizados.isNotEmpty()) {
                for (conteudos in usuario.conteudosFinalizados) {
                    Utilidade.imprimeMensagem(
                        "" +
                                "                   > Código do conteúdo : ${conteudos.id}\n" +
                                "                   > Nome do conteúdo : ${conteudos.nome}\n" +
                                "                   > Duração em horas : ${conteudos.duracao}\n" +
                                "                   > Nível do conteúdo : ${conteudos.nivel}\n\n"
                    )
                }
            } else {
                println("Você ainda não concluiu nenhum conteúdo.\n")
            }
        }
        fun validaConclusaoTotal(usuario : Usuario) : Boolean {
            return usuario.conteudosKt.all { it.conteudo.status == StatusConteudo.CONCLUÍDO }
        }

        fun aumentaNivel(usuario: Usuario, conteudo: Conteúdo) {
            if (conteudo.status == StatusConteudo.CONCLUÍDO) {
                usuario.nivel = when (usuario.nivel) {
                    NivelUsuario.BRONZE -> NivelUsuario.SILVER
                    NivelUsuario.SILVER -> NivelUsuario.GOLD
                    NivelUsuario.GOLD -> NivelUsuario.PLATINUM
                    else -> usuario.nivel
                }
            }
        }
    }
}