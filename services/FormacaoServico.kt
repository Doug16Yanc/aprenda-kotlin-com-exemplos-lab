package services

import entities.Formação
import entities.Usuario
import enumerations.NivelUsuario
import interagePrimeiro
import utils.Utilidade
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class FormacaoServico {
    companion object{
        val inscritos : MutableList<Usuario> = ArrayList()
        fun gerarId(): Int {
            var entrada = Random.nextInt(1000, 10000)


            while (entrada != 1) {
                var auxiliar = true
                for (i in 0 until inscritos.size) {
                    if (entrada == inscritos[i].id) {
                        auxiliar = false
                    }
                }

                if (auxiliar) {
                    return entrada
                } else {
                    entrada = Random.nextInt(1000, 10000)
                }
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

            val usuario = Usuario(id, nome, email, login, senha, NivelUsuario.BRONZE, formacao)

            inscritos.add(usuario)

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
            interagePrimeiro()
        }
        fun comprovaFormacaoKt(usuario: Usuario, formacao : Formação){
            Utilidade.imprimeMensagem("Certificamos que ${usuario.nome} concluiu\n" +
                    "com sucesso a sua jornada em desenvolvimento backend com Kotlin e Spring Boot\n" +
                    "com duração de ${formacao.duracao} horas.\n")
        }
    }
}