package services

import entities.Conteúdo
import entities.Formação
import utils.Utilidade

class ConteudoServico {
    companion object{
        val conteudosKt : MutableList<Formação> = ArrayList()
        val conteudosJS : MutableList<Formação> = ArrayList()
        init {
            conteudosKt.add(Formação("Desenvolvimento Kotlin", Conteúdo(17364,"Linguagem de programação Kotlin e suas funcionalidades", 3)))
            conteudosKt.add(Formação("Desenvolvimento Kotlin", Conteúdo(18934, "Padrões de projeto em Kotlin", 4)))
            conteudosKt.add(Formação("Desenvolvimento Kotlin" , Conteúdo(19756, "Desenvolvimento backend com Spring Boot em Kotlin", 5)))

            conteudosJS.add(Formação("Desenvolvimento frontend com JS e Angular", Conteúdo(23564, "Frontend com JavaScript", 3)))
            conteudosJS.add(Formação("Desenvolvimento frontend com JS e Angular", Conteúdo(24521, "Fundamentos do Angular Framewrok", 3)))
            conteudosJS.add(Formação("Desenvolvimento frontend com JS e Angular", Conteúdo(25789, "Construindo uma aplicação com Angular", 4)))
        }


        fun defineKotlin(){
            Utilidade.imprimeMensagem("Definição de andamento da formação:")
            println("O que você tem a aprender:\n")
            Utilidade.imprimeMensagem("Desenvolvimento backend com Kotlin e Spring Boot.\n")
            for (conteudos in conteudosKt){
                Utilidade.imprimeMensagem("" +
                        "                   > Código do conteúdo : ${conteudos.conteudo.id}\n" +
                        "                   > Nome do conteúdo : ${conteudos.conteudo.nome}\n" +
                        "                   > Duração em horas : ${conteudos.conteudo.duracao}\n\n")
            }
        }
        fun defineJS(){

        }
    }
}