package tech.devrocks.oink.mock

import tech.devrocks.oink.model.Classificacao
import java.util.*
import kotlin.collections.ArrayList

class ClassificacaoDeRegistroMock {
  fun obterListaClassificacao(): ArrayList<Classificacao> {
    val lista = ArrayList<Classificacao>()

    lista.add(
      Classificacao(
        "RE",
        "Receita",
        "E"
      )
    )

    lista.add(
      Classificacao(
        "IN",
        "Investimento",
        "S"
      )
    )

    lista.add(
      Classificacao(
        "RS",
        "Reserva",
        "S"
      )
    )

    lista.add(
      Classificacao(
        "DF",
        "Despesa Fixa",
        "S"
      )
    )

    lista.add(
      Classificacao(
        "DV",
        "Despesa Variável",
        "S"
      )
    )

    lista.add(
      Classificacao(
        "DA",
        "Despesa Adicional",
        "S"
      )
    )

    lista.add(
      Classificacao(
        "DE",
        "Despesa Extra",
        "S"
      )
    )
    return lista
  }
}