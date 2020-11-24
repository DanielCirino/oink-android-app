package tech.devrocks.oink.mock

import tech.devrocks.oink.model.TipoDeConta
import java.util.*
import kotlin.collections.ArrayList

class TipoContaMock {
    fun obterListaTipos(): ArrayList<TipoDeConta> {
        val lista = ArrayList<TipoDeConta>()

        lista.add(
            TipoDeConta(
                "",
                "CC",
                "Conta corrente",
                Date()
            )
        )

        lista.add(
            TipoDeConta(
                "",
                "PP",
                "Poupança",
                Date()
            )
        )

        lista.add(
            TipoDeConta(
                "",
                "CR",
                "Cartão de Crédito",
                Date()
            )
        )

        lista.add(
            TipoDeConta(
                "",
                "CT",
                "Carteira",
                Date()
            )
        )

        lista.add(
            TipoDeConta(
                "",
                "TR",
                "Ticket Refeição",
                Date()
            )
        )

        lista.add(
            TipoDeConta(
                "",
                "TA",
                "Ticket Alimentação",
                Date()
            )
        )

        return lista
    }
}