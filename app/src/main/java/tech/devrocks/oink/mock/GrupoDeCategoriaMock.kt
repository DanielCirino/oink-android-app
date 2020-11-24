package tech.devrocks.oink.mock

import tech.devrocks.oink.model.GrupoDeCategoria

class GrupoDeCategoriaMock {
  fun obterListaGruposAgrupada(): MutableList<Any> {
    val listaGrupos = obterListaGrupos()

    val listaAgrupada: MutableList<Any> = mutableListOf<Any>()
    var nomeClassificacao = ""

    for (grupo in listaGrupos.sortedBy {
      it.nomeClassificacao
    }) {
      if (grupo.nomeClassificacao != nomeClassificacao) {
        nomeClassificacao = grupo.nomeClassificacao!!
        listaAgrupada.add(nomeClassificacao)
      }
      listaAgrupada.add(grupo)


    }

    return listaAgrupada

  }

  fun obterListaGrupos(): ArrayList<GrupoDeCategoria> {
    val lista = ArrayList<GrupoDeCategoria>()

    lista.add(
      GrupoDeCategoria(
        "RE",
        "Receita",
        "Receita"
      )
    )

    lista.add(
      GrupoDeCategoria(
        "IN",
        "Investimento",
        "Investimento"
      )
    )

    lista.add(
      GrupoDeCategoria(
        "RS",
        "Reserva",
        "Reserva"
      )
    )

    lista.add(
      GrupoDeCategoria(
        "DF",
        "Despesa Fixa",
        "Habitação"
      )
    )

    lista.add(
      GrupoDeCategoria(
        "DF",
        "Despesa Fixa",
        "Transporte"
      )
    )

    lista.add(
      GrupoDeCategoria(
        "DF",
        "Despesa Fixa",
        "Saúde"
      )
    )

    lista.add(
      GrupoDeCategoria(
        "DF",
        "Despesa Fixa",
        "Educação"
      )
    )

    lista.add(
      GrupoDeCategoria(
        "DF",
        "Despesa Fixa",
        "Impostos"
      )
    )

    lista.add(
      GrupoDeCategoria(
        "DF",
        "Despesa Fixa",
        "Outros"
      )
    )

    lista.add(
      GrupoDeCategoria(
        "DV",
        "Despesa Variável",
        "Habitação"
      )
    )

    lista.add(
      GrupoDeCategoria(
        "DV",
        "Despesa Variável",
        "Transporte"
      )
    )
    lista.add(
      GrupoDeCategoria(
        "DV",
        "Despesa Variável",
        "Alimentação"
      )
    )
    lista.add(
      GrupoDeCategoria(
        "DV",
        "Despesa Variável",
        "Saúde"
      )
    )
    lista.add(
      GrupoDeCategoria(
        "DV",
        "Despesa Variável",
        "Cuidados pessoais"
      )
    )

    lista.add(
      GrupoDeCategoria(
        "DA",
        "Despesa Adicional",
        "Lazer"
      )
    )

    lista.add(
      GrupoDeCategoria(
        "DA",
        "Despesa Adicional",
        "Vestuário"
      )
    )

    lista.add(
      GrupoDeCategoria(
        "DA",
        "Despesa Adicional",
        "Presentes"
      )
    )

    lista.add(
      GrupoDeCategoria(
        "DA",
        "Despesa Adicional",
        "Outros"
      )
    )

    lista.add(
      GrupoDeCategoria(
        "DE",
        "Despesa Extra",
        "Saúde"
      )
    )

    lista.add(
      GrupoDeCategoria(
        "DE",
        "Despesa Extra",
        "Habitação"
      )
    )

    lista.add(
      GrupoDeCategoria(
        "DE",
        "Despesa Extra",
        "Transporte"
      )
    )
    return lista
  }
}