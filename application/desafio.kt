import entities.Conteúdo
import entities.Formação
import services.FormacaoServico
import services.UsuarioServico
import utils.Utilidade
import enumerations.NivelConteudo
import enumerations.StatusConteudo
import enumerations.StatusFormacao
import java.util.*

fun main() {
    interagePrimeiro()
}
fun interagePrimeiro(){
    val formacao = Formação("", 12, StatusFormacao.NÃO_INICIADA, Conteúdo(1, "", 1, NivelConteudo.FÁCIL, StatusConteudo.PENDENTE))
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

            UsuarioServico.realizaLogin(formacao)
        }
        "n" -> {
            FormacaoServico.realizaMatricula(formacao)
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
