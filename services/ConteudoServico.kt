package services

import entities.Conteúdo
import entities.Formação
import entities.Usuario
import enumerations.NivelConteudo
import enumerations.StatusConteudo
import enumerations.StatusFormacao
import enumerations.TipoFormacao
import interagePrimeiro
import utils.Utilidade

class ConteudoServico {
    companion object{
        val conteudosKt : MutableList<Formação> = ArrayList()
        val conteudosJS : MutableList<Formação> = ArrayList()
        val conteudosFinalizados : MutableList<Conteúdo> = ArrayList()
        init {
            conteudosKt.add(Formação("Desenvolvimento Kotlin", StatusFormacao.NÃO_INICIADA, TipoFormacao.BACKEND, Conteúdo(17364,"Linguagem de programação Kotlin e suas funcionalidades", 3, NivelConteudo.FÁCIL, StatusConteudo.PENDENTE)))
            conteudosKt.add(Formação("Desenvolvimento Kotlin", StatusFormacao.NÃO_INICIADA, TipoFormacao.BACKEND, Conteúdo(18934, "Padrões de projeto em Kotlin", 4, NivelConteudo.INTERMEDIÁRIO, StatusConteudo.PENDENTE)))
            conteudosKt.add(Formação("Desenvolvimento Kotlin" , StatusFormacao.NÃO_INICIADA, TipoFormacao.BACKEND, Conteúdo(19756, "Desenvolvimento backend com Spring Boot em Kotlin", 5, NivelConteudo.AVANÇADO, StatusConteudo.PENDENTE)))

            conteudosJS.add(Formação("Desenvolvimento frontend com JS e Angular", StatusFormacao.NÃO_INICIADA, TipoFormacao.FRONTEND, Conteúdo(23564, "Frontend com JavaScript", 3,NivelConteudo.FÁCIL, StatusConteudo.PENDENTE)))
            conteudosJS.add(Formação("Desenvolvimento frontend com JS e Angular", StatusFormacao.NÃO_INICIADA, TipoFormacao.FRONTEND,  Conteúdo(24521, "Fundamentos do Angular Framewrok", 3, NivelConteudo.INTERMEDIÁRIO, StatusConteudo.PENDENTE)))
            conteudosJS.add(Formação("Desenvolvimento frontend com JS e Angular", StatusFormacao.NÃO_INICIADA, TipoFormacao.FRONTEND, Conteúdo(25789, "Construindo uma aplicação com Angular", 4, NivelConteudo.AVANÇADO, StatusConteudo.PENDENTE)))
        }
        fun direcionaCurso(usuario: Usuario, formacao : Formação){
            when(formacao.tipo){
                TipoFormacao.BACKEND -> fazConteudosKt(usuario, formacao)
                TipoFormacao.FRONTEND -> fazConteudosJS(usuario, formacao)
                else -> {}
            }
        }

        fun defineKotlin(usuario : Usuario, formacao : Formação){
            Utilidade.imprimeMensagem("Definição de andamento da formação:")
            println("O que você tem a aprender:\n")
            Utilidade.imprimeMensagem("Desenvolvimento backend com Kotlin e Spring Boot.\n")
            for (conteudos in conteudosKt){
                Utilidade.imprimeMensagem("" +
                        "                   > Código do conteúdo : ${conteudos.conteudo.id}\n" +
                        "                   > Nome do conteúdo : ${conteudos.conteudo.nome}\n" +
                        "                   > Duração em horas : ${conteudos.conteudo.duracao}\n" +
                        "                   > Nível do conteúdo : ${conteudos.conteudo.nivel}\n\n")
            }
            fazConteudosKt(usuario, formacao)
        }
        fun fazConteudosKt(usuario: Usuario, formacao: Formação){
            Utilidade.imprimeMensagem("Olá, aqui você dar início aos conteúdos,\n" +
                    "digite o id dos conteúdos que já concluiu ou -1 caso deseje sair do laço:\n")

            do {
                println("Id do conteúdo ou -1 para finalizar:")
                var id = Utilidade.sc.nextInt()

                if (id == -1){
                    break
                }

                val idEncontrado = conteudosKt.find {it.conteudo.id == id}

                if (idEncontrado != null){
                    if (idEncontrado.conteudo.status != StatusConteudo.CONCLUÍDO){
                        Utilidade.imprimeMensagem("Conteúdo ${idEncontrado.conteudo.id},\n" +
                                "${idEncontrado.conteudo.nome} finalizado com sucesso.\n")
                        idEncontrado.conteudo.status = StatusConteudo.CONCLUÍDO
                        conteudosFinalizados.add(idEncontrado.conteudo)
                    }
                    else {
                        println("Você já concluiu esse conteúdo.")
                    }
                }
                else{
                    Utilidade.imprimeMensagem("Id de conteúdo não reconhecido.\n")
                }

            } while(id != -1)
            UsuarioServico.interageUsuario(usuario, formacao)

        }
        fun defineJS(usuario: Usuario, formacao : Formação){
            Utilidade.imprimeMensagem("Definição de andamento da formação:")
            println("O que você tem a aprender:\n")
            Utilidade.imprimeMensagem("Desenvolvimento frontend com JavaScript e Angular Framework.\n")
            for (conteudos in conteudosJS){
                Utilidade.imprimeMensagem("" +
                        "                   > Código do conteúdo : ${conteudos.conteudo.id}\n" +
                        "                   > Nome do conteúdo : ${conteudos.conteudo.nome}\n" +
                        "                   > Duração em horas : ${conteudos.conteudo.duracao}\n" +
                        "                   > Nível do conteúdo : ${conteudos.conteudo.nivel}\n\n")

            }
            fazConteudosJS(usuario, formacao)
        }
        fun fazConteudosJS(usuario: Usuario, formacao: Formação){
            Utilidade.imprimeMensagem("Olá, aqui você dar início aos conteúdos,\n" +
                    "digite o id dos conteúdos que já concluiu ou -1 caso deseje sair do laço:\n")

            do {
                println("Id do conteúdo ou -1 para finalizar:")
                var id = Utilidade.sc.nextInt()

                if (id == -1){
                    break
                }
                val idEncontrado = conteudosJS.find {it.conteudo.id == id}

                if (idEncontrado != null){
                    if (idEncontrado.conteudo.status != StatusConteudo.CONCLUÍDO){
                        Utilidade.imprimeMensagem("Conteúdo ${idEncontrado.conteudo.id},\n" +
                                "${idEncontrado.conteudo.nome} finalizado com sucesso.\n")
                        idEncontrado.conteudo.status = StatusConteudo.CONCLUÍDO
                        conteudosFinalizados.add(idEncontrado.conteudo)
                    }
                    else {
                        println("Você já concluiu esse conteúdo.")
                    }

                }
                else{
                    Utilidade.imprimeMensagem("Id de conteúdo não reconhecido.\n")
                }
            } while(id != -1)
            UsuarioServico.interageUsuario(usuario, formacao)
        }
        fun listaConteudosFim(){
            Utilidade.imprimeMensagem("Conteúdos finalizados. Mais um passo importante na carreira.\n")
            if (conteudosFinalizados.isNotEmpty()) {
                for (conteudos in conteudosFinalizados) {
                    Utilidade.imprimeMensagem(
                        "" +
                                "                   > Código do conteúdo : ${conteudos.id}\n" +
                                "                   > Nome do conteúdo : ${conteudos.nome}\n" +
                                "                   > Duração em horas : ${conteudos.duracao}\n" +
                                "                   > Nível do conteúdo : ${conteudos.nivel}\n\n"
                    )
                }
            }
            else {
                println("Você ainda não concluiu nenhum conteúdo.\n")
            }
        }
        fun validaConclusaoTotal(formacao: Formação) : Boolean {
            when (formacao.tipo) {
                TipoFormacao.BACKEND -> {
                    val conteudos = conteudosKt.filter { it.conteudo.status == StatusConteudo.CONCLUÍDO }
                    return conteudos.size == conteudosKt.size
                }
                TipoFormacao.FRONTEND -> {
                    val conteudos = conteudosJS.filter { it.conteudo.status == StatusConteudo.CONCLUÍDO }
                    return conteudos.size == conteudosJS.size
                }
                else -> {
                    return false
                }
            }
        }
    }
}