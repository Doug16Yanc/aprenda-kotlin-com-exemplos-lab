package services

import entities.Formação
import entities.Usuario
import enumerations.StatusFormacao
import interagePrimeiro
import utils.Utilidade

class UsuarioServico {
    companion object{
        fun realizaLogin(formacao: Formação){
            Utilidade.imprimeMensagem("LOGIN DE INSCRITO\n\n")
            println("Digite seu id (sua chave primária que comprova seu registo na plataforma):\n")
            var id = Utilidade.sc.nextInt()

            val usuarioEncontrado = FormacaoServico.inscritos.find {it.id == id}

            if (usuarioEncontrado != null){
                println("Validação por meio de usuário e senha:\n")
                Utilidade.sc.nextLine()
                println("Login:")
                var login = Utilidade.sc.nextLine()
                println("Senha de acesso:")
                var senha = Utilidade.sc.nextLine()

                val usuarioInscrito = FormacaoServico.inscritos.find {it.login == login && it.senha == senha}

                if(usuarioInscrito != null){
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
                            "2 - Ir para conteúdo:\n" +
                            "3 - Listar conteúdos já finalizados.\n" +
                            "4 - Ver custo de uma nova formação não gratuita.\n" +
                            "5 - Sair da conta:\n"
                )
                var opcao = Utilidade.sc.nextInt()

                when (opcao) {
                    1 -> {
                        consultaDados(usuario)
                    }
                    2 -> {
                        ConteudoServico.defineKotlin(usuario, formacao)
                    }
                    3 -> {
                        ConteudoServico.listaConteudosFim()
                    }
                    4 -> {
                        CustoServico.mostraCustos(usuario, formacao)
                    }

                    5 -> {
                        interagePrimeiro()
                    }

                    else -> {
                        Utilidade.imprimeMensagem("Opção não reconhecida.\n")
                    }
                }
            } while(true)
        }
        fun consultaDados(usuario : Usuario){
            Utilidade.imprimeMensagem("Visualização de dados do usuário\n\n" +
                    "                           > Nome : ${usuario.nome}\n" +
                    "                           > Id : ${usuario.id}\n" +
                    "                           > Email : ${usuario.email}\n" +
                    "                           > Nível usuario : ${usuario.nivel}\n\n")
        }
    }
}