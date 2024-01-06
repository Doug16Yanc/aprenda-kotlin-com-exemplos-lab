package services

import entities.Custo
import entities.Formação
import entities.Usuario
import enumerations.NivelUsuario
import repositories.Desconta
import utils.Utilidade
import java.lang.String.format

class CustoServico {
    companion object : Desconta {
        fun mostraCustos(usuario : Usuario, formacao: Formação){
            val custo = Custo(59.90, 0.0)
            var novoValor = calculaDescontos(usuario, formacao, custo)
            Utilidade.imprimeMensagem("Bem-vindo, caríssimo(a) ${usuario.nome},\n" +
                    "a cada avanço de nível, você tem direito a descontos consecutivos percentuais de\n\n" +
                    "5% no preço de uma nova formação : R$ 59.90.\n\n" +
                    "> Status da primeira formação escolhida: ${formacao.status}\n" +
                    "> Nível do inscrito : ${usuario.nivel}\n" +
                    "> Custo atual de uma nova formação : R$ %.2f\n\n".format(novoValor)
            )

        }
        override fun calculaDescontos(usuario : Usuario, formacao: Formação, custo: Custo) : Double {
            var novoValor = 0.0
            when(usuario.nivel){
                NivelUsuario.BRONZE -> {
                    novoValor += custo.preco
                }
                NivelUsuario.SILVER -> {
                    novoValor += custo.preco*0.95
                    custo.desconto = novoValor*(1 - 0.95)
                }
                NivelUsuario.GOLD -> {
                    novoValor += custo.preco*0.90
                    custo.desconto = novoValor*(1 - 0.90)
                }
                NivelUsuario.PLATINUM -> {
                    novoValor += custo.preco*0.85
                    custo.desconto = novoValor*(1 - 0.85)
                }
                else -> {
                    usuario.nivel
                }
            }
            return novoValor
        }
    }
}