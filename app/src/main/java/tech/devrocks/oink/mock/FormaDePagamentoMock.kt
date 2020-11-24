package tech.devrocks.oink.mock

import tech.devrocks.oink.model.FormaDePagamento
import java.util.*
import kotlin.collections.ArrayList

class FormaDePagamentoMock {
  fun obterListaFormasDePagamento(): ArrayList<FormaDePagamento> {
    val lista = ArrayList<FormaDePagamento>()

    lista.add(
      FormaDePagamento(
        "",
        "CD",
        "Cartão de Débito",
        Date()
      )
    )

    lista.add(
      FormaDePagamento(
        "",
        "DC",
        "Débito em Conta",
        Date()
      )
    )

    lista.add(
      FormaDePagamento(
        "",
        "DF",
        "Desconto em Folha",
        Date()
      )
    )

    lista.add(
      FormaDePagamento(
        "",
        "CCA",
        "Cartão de Crédito (a vista)",
        Date()
      )
    )

    lista.add(
      FormaDePagamento(
        "",
        "CCP",
        "Cartão de Crédito (a prazo)",
        Date()
      )
    )

    lista.add(
      FormaDePagamento(
        "",
        "DH",
        "Dinheiro",
        Date()
      )
    )

    lista.add(
      FormaDePagamento(
        "",
        "TR",
        "Ticket Refeição",
        Date()
      )
    )

    lista.add(
      FormaDePagamento(
        "",
        "TA",
        "Ticket Alimentação",
        Date()
      )
    )

    lista.add(
      FormaDePagamento(
        "",
        "CHA",
        "Cheque a vista",
        Date()
      )
    )

    lista.add(
      FormaDePagamento(
        "",
        "CHP",
        "Cheque a prazo",
        Date()
      )
    )

    return lista
  }
}