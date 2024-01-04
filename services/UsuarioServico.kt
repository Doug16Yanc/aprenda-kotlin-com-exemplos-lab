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
                            "1 - Continuar conteúdo:\n" +
                            "2 - Listar conteúdos já finalizados.\n" +
                            "3 - Começar outro curso.\n" +
                            "4 - Sair da conta:\n"
                )
                var opcao = Utilidade.sc.nextInt()

                when (opcao) {
                    1 -> {
                        ConteudoServico.direcionaCurso(usuario, formacao)
                    }

                    2 -> {
                        ConteudoServico.listaConteudosFim()
                    }

                    3 -> {

                    }

                    4 -> {
                        interagePrimeiro()
                        break
                    }

                    else -> {
                        Utilidade.imprimeMensagem("Opção não reconhecida.\n")
                    }
                }
            } while(true)
        }
    }
}