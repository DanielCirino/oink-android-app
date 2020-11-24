package tech.devrocks.oink.mock

import tech.devrocks.oink.model.Conta
import java.util.*
import kotlin.collections.ArrayList

class ContaMock {
    fun obterListaContas(): ArrayList<Conta> {
        val lista = ArrayList<Conta>()

        lista.add(
            Conta(
               "",
                "",
                "CC",
                "Conta corrente",
                "Banco Itaú",
                "318",
                "8781",
                "12548-2",
                "08571100632",
                Date()
            )
        )
        return lista
    }
}