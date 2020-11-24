package tech.devrocks.oink.mock

import tech.devrocks.oink.model.Categoria
import java.util.*
import kotlin.collections.ArrayList

class CategoriaMock {
  fun obterListaDeCategorias(): ArrayList<Categoria> {
    val lista = ArrayList<Categoria>()

    lista.add(
      Categoria(
        "",
        "E",
        "Entrada",
        "RC",
        "Receita",
        "",
        "Receita",
        "Salário",
        Date()
      )
    )

    lista.add(
      Categoria(
        "",
        "S",
        "Saída",
        "DF",
        "Despesa Fixa",
        "",
        "Habitação",
        "Aluguel",
        Date()
      )
    )
    return lista
  }
}