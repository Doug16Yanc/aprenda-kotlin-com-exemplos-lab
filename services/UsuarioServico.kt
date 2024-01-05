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

class UsuarioServico {
    companion object{
        fun realizaLogin(){
            Utilidade.imprimeMensagem("LOGIN DE INSCRITO\n\n")
            println("Digite seu id (sua chave primária que comprova seu registo na plataforma):\n")
            var id = Utilidade.sc.nextInt()
            Utilidade.sc.nextLine()
            val usuarioEncontrado = FormacaoServico.inscritos.values.find {it.id == id}

            if (usuarioEncontrado != null){
                println("Validação por meio de usuário e senha:\n")
                println("Login:")
                var login = Utilidade.sc.nextLine()
                println("Senha de acesso:")
                var senha = Utilidade.sc.nextLine()

                val usuarioInscrito = FormacaoServico.inscritos.values.find {it.login == login && it.senha == senha}

                if(usuarioInscrito != null){
                    val formacao = Formação("", StatusFormacao.NÃO_INICIADA, TipoFormacao.OUTRA, Conteúdo(1, "", 1, NivelConteudo.FÁCIL, StatusConteudo.PENDENTE))
                    interageUsuario(usuarioInscrito, formacao)
                }
                else{
                    Utilidade.imprimeMensagem("Login ou senha não reconhecidos.\n")
                    interagePrimeiro()
                }
            }
            else{
                Utilidade.imprimeMensagem("Usuário não encontrado.\n")
                interagePrimeiro()
            }

        }
        fun interageUsuario(usuario: Usuario, formacao : Formação){
            do {
                Utilidade.imprimeMensagem("Bem-vindo(a) caríssimo(a) ${usuario.nome}\n")
                println(
                    "O que temos para hoje:\n" +
                            "1 - Visualizar dados\n" +
                            "2 - Continuar conteúdo:\n" +
                            "3 - Listar conteúdos já finalizados.\n" +
                            "4 - Iniciar outra formação.\n" +
                            "5 - Sair da conta:\n"
                )
                var opcao = Utilidade.sc.nextInt()

                when (opcao) {
                    1 -> {
                        consultaDados(usuario, formacao)
                    }
                    2 -> {
                        ConteudoServico.direcionaCurso(usuario, formacao)
                    }

                    3 -> {
                        ConteudoServico.listaConteudosFim()
                    }

                    4 -> {
                        if (ConteudoServico.validaConclusaoTotal(formacao)) {

                            FormacaoServico.realizaNovaFormacao(usuario, formacao)

                        } else {
                            Utilidade.imprimeMensagem("Você ainda não concluiu a formação atual.\n")
                        }
                    }

                    5 -> {
                        interagePrimeiro()
                        break
                    }

                    else -> {
                        Utilidade.imprimeMensagem("Opção não reconhecida.\n")
                    }
                }
            } while(true)
        }
        fun consultaDados(usuario : Usuario, formacao: Formação){
            Utilidade.imprimeMensagem("Visualização de dados do usuário\n\n" +
                    "                           > Nome : ${usuario.nome}\n" +
                    "                           > Id : ${usuario.id}\n" +
                    "                           > Email : ${usuario.email}\n" +
                    "                           > Nível usuario : ${usuario.nivel}\n\n")
        }
    }
}