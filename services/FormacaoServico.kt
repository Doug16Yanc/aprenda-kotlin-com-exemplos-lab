package services

import entities.Formação
import entities.Usuario
import enumerations.NivelUsuario
import enumerations.StatusFormacao
import enumerations.TipoFormacao
import interagePrimeiro
import utils.Utilidade
import java.util.*
import kotlin.random.Random

class FormacaoServico {
    companion object{
        val inscritos : MutableMap<Int, Usuario> = HashMap()
        fun gerarId(): Int {
            var num = 0
            var entrada = Random.nextInt(1000, 10000)

            while (inscritos.containsKey(entrada)) {
                entrada = Random.nextInt(1000, 10000)
            }
            return entrada
        }
        fun realizaMatricula(formacao: Formação){
            Utilidade.imprimeMensagem("Realização de matrícula:\n" +
                    "Preencha com os dados solicitados.\n\n")
            val id = gerarId()
            println("Nome:")
            var nome = Utilidade.sc.nextLine()
            println("Seu email para contato:")
            var email = Utilidade.sc.nextLine()
            println("Login para entrar na plataforma:")
            var login = Utilidade.sc.nextLine()
            println("Senha para o seu login:")
            var senha = Utilidade.sc.nextLine()

            val usuario = Usuario(id, nome, email, login, senha, NivelUsuario.BRONZE)

            inscritos[id] = usuario

            comprovaMatricula(usuario, formacao)
        }
        fun comprovaMatricula(usuario: Usuario, formacao : Formação){
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
            UsuarioServico.interageUsuario(usuario, formacao)
        }
        fun escolherFormação(usuario: Usuario, formacao: Formação){
            Utilidade.imprimeMensagem("Escolha apenas uma de nossas formações:")
            println("           > 1 - Desenvolvimento backend com Kotlin e Spring Boot\n" +
                    "           > 2 - Desenvolvimento frontend com JavaScript e Angular\n" +
                    "           > 3 - Sair da conta:\n")
            var opcao = Utilidade.sc.nextInt()

            when(opcao){
                1 -> {
                    formacao.status = StatusFormacao.ANDAMENTO
                    formacao.tipo = TipoFormacao.BACKEND
                    ConteudoServico.defineKotlin(usuario, formacao)
                }
                2 -> {
                    formacao.status = StatusFormacao.ANDAMENTO
                    formacao.tipo = TipoFormacao.FRONTEND
                    ConteudoServico.defineJS(usuario, formacao)

                }
                3 -> {
                    formacao.tipo = TipoFormacao.OUTRA
                    interagePrimeiro()
                }
                else -> {
                    Utilidade.imprimeMensagem("Opção não possível.\n")
                }
            }
        }
        fun realizaNovaFormacao(usuario: Usuario, formacao : Formação){
           when (formacao.tipo){
               TipoFormacao.BACKEND -> {
                   ConteudoServico.defineJS(usuario, formacao)
               }
               TipoFormacao.FRONTEND -> {
                   ConteudoServico.defineKotlin(usuario, formacao)
               }
               TipoFormacao.OUTRA -> {
                   return
               }
           }
        }
        fun comprovaFormacao(usuario: Usuario, formacao : Formação){
            Utilidade.imprimeMensagem("Certificamos que ${usuario.nome} concluiu\n" +
                    "com sucesso a sua jornada em ${formacao.nome} com duração de ${formacao.duracao} horas.\n")
        }
    }
}