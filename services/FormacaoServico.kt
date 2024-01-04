package services

import entities.Usuario
import enumerations.NivelUsuario
import utils.Utilidade
import java.util.*
import kotlin.random.Random

class FormacaoServico {
    companion object{
        val inscritos : MutableMap<Int, Usuario> = HashMap()
        fun gerarId(): Int {
            var num = 0
            var entrada = Random.nextInt(100000, 1000000)

            while (inscritos.containsKey(entrada)) {
                entrada = Random.nextInt(100000, 1000000)
            }
            return entrada
        }
        fun realizaMatricula(){
            Utilidade.imprimeMensagem("Realização de matrícula:\n" +
                    "Preencha com os dados solicitados.\n\n")
            val id = gerarId()
            Utilidade.sc.nextLine()
            println("Nome:")
            var nome = Utilidade.sc.nextLine()
            println("Seu email para contato:")
            var email = Utilidade.sc.nextLine()
            println("Login para entrar na plataforma:")
            var login = Utilidade.sc.nextLine()
            println("Senha para o seu login:")
            var senha = Utilidade.sc.nextLine()

            val usuario = Usuario(id, nome, email, login, senha, NivelUsuario.SILVER)

            inscritos[id] = usuario

            comprovaMatricula(usuario)
        }
        fun comprovaMatricula(usuario: Usuario){
            Utilidade.imprimeMensagem("Comprovante de matrícula com a DIO\n\n" +
                    "                  DADOS DO INSCRITO                \n\n" +
                    "                 > Nome do inscrito : ${usuario.nome}\n" +
                    "                 > Id do inscrito : ${usuario.id}\n" +
                    "                 > Email para contato : ${usuario.email}\n" +
                    "                 > Nível de usuário : ${usuario.nivel}\n\n" +
                    "                  DADOS DA OPERAÇÃO            \n\n" +
                    "                 > Data e hora : ${Utilidade.formattedDateTime}\n" +
                    "                 > Código da operação : ${UUID.randomUUID()}\n\n")

            println("Para tanto, escolha uma de nossas formações e bom aproveitamento:\n\n")
            escolherFormação(usuario)
        }
        fun escolherFormação(usuario: Usuario){
            Utilidade.imprimeMensagem("Escolha apenas uma de nossas formações:")
            println("           > 1 - Desenvolvimento backend com Kotlin e Spring Boot\n" +
                    "           > 2 - Desenvolvimento frontend com JavaScript e Angular\n")
            var opcao = Utilidade.sc.nextInt()

            when(opcao){
                1 -> {
                    ConteudoServico.defineKotlin()
                }
                2 -> {
                    ConteudoServico.defineJS()
                }
                else -> {
                    Utilidade.imprimeMensagem("Opção não possível.\n")
                }
            }
        }
    }
}