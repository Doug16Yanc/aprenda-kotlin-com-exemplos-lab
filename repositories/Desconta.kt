package repositories

import entities.Custo
import entities.Formação
import entities.Usuario

interface Desconta {
    fun calculaDescontos(usuario: Usuario, formacao : Formação, custo: Custo) : Double
}