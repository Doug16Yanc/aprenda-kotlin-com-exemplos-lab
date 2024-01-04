package services

import entities.Usuario
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
                    interageUsuario(usuarioInscrito)
                }
                else{
                    Utilidade.imprimeMensagem("Login ou senha não reconhecidos.\n")
                }
            }
            else{
                Utilidade.imprimeMensagem("Usuário não encontrado.\n")
            }
        }
        fun interageUsuario(usuario: Usuario){
            Utilidade.imprimeMensagem("\n")
        }
    }
}