package tech.devrocks.oink.mock

import tech.devrocks.oink.model.PoteDeGasto

class PoteDeGastosMock {


  fun obterListaPotes(): ArrayList<PoteDeGasto> {
    val lista = ArrayList<PoteDeGasto>()

    lista.add(
      PoteDeGasto(
        "P1",
        "Necessidades Básicas",
        "Representa o percentual do seu orçamento que você poderá gastar suas despesas diárias e pagamentos de contas mensais fixas, como quitar dívidas, pagar internet, luz, água, aluguel, e etc.",
        45

      )
    )

    lista.add(
      PoteDeGasto(
        "P2",
        "Caridade e Presentes",
        "Percentual do seu dinheiro devem ser guardados para presentes e caridade. Sempre que estiver chegando a data de alguma comemoração, você pode tirar esse dinheiro para presentear o sortudo. Caso deseje ajudar alguma organização, esse também é o pote certo.",
        5

      )
    )

    lista.add(
      PoteDeGasto(
        "P3",
        "Entretenimento",
        "Representa o percentual do seu dinheiro, que poderá ser gasto para seu lazer. Ou seja, para o cineminha, catuaba cerveja importada, restaurante, entre outros prazeres da vida.",
        10

      )
    )

    lista.add(
      PoteDeGasto(
        "P4",
        "Educação",
        "Percentual do seu dinheiro que deve ser gasto para seu crescimento profissional e pessoal, com ele você pode fazer cursos, palestras, comprar livros… Enfim, é um gasto para expandir sua mente.",
        10

      )
    )

    lista.add(
      PoteDeGasto(
        "P5",
        "Reserva de Emergência",
        "Esta poupança pode ser utilizada para grandes compras. Gastar esse dinheiro quando você quer comprar uma TV de tela plana, um novo computador, ou um carro. Este é o seu fundo de reserva que você deve reabastecer constantemente.",
        10

      )
    )

    lista.add(
      PoteDeGasto(
        "P6",
        "Investimentos",
        "Representa o percentual do seu dinheiro que deve ser utilizado para seu futuro. Você deve guardar essa porcentagem na poupança, ou fazer algum investimento confiável, e só tirar quando estiver independente financeiramente.",
        10

      )
    )

    return lista
  }
}