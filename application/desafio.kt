import services.FormacaoServico
import services.UsuarioServico
import utils.Utilidade
import entities.Usuario
import java.util.*

fun main() {
    interagePrimeiro()
}
fun interagePrimeiro(){
    Utilidade.imprimeMensagem("Bem-vindo(a) à Formação DIO\n\n")
    println(
        "Já é cadastrado aqui na plataforma?\n" +
                "S/s - sim\n" +
                "N/n - não\n" +
                "O/o - Aqui caso queira sair da plataforma.\n"
    )
    var opcao = Utilidade.sc.next()
    Utilidade.sc.nextLine()
    when (opcao.lowercase(Locale.getDefault())) {
        "s" -> {

            UsuarioServico.realizaLogin()
        }

        "n" -> {
            FormacaoServico.realizaMatricula()
        }

        "o" -> {
            Utilidade.imprimeMensagem("Foi um prazer tê-lo em nossa plataforma. Até logo.\n")
            System.exit(0)
        }

        else -> {
            Utilidade.imprimeMensagem("Opção não reconhecida.\n")
        }
    }
}
