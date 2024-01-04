package utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Utilidade {
    companion object{
        val sc = Scanner(System.`in`)
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        val formattedDateTime = currentDateTime.format(formatter)
        fun imprimeMensagem(mensagem : String){
            println("***************************************\n")
            println(mensagem)
            println("***************************************\n")
        }

    }
}