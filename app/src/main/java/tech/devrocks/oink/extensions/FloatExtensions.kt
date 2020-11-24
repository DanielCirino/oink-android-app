package app.tipsarena.extensions


fun Float.formatar(): String {
  var valorFormatado = this.toString()
  try {

    if (this > 1000000) {
      valorFormatado = "${String.format("%.2f", this / 1000000)} M"
    }


    if (this > 1000) {
      valorFormatado = "${String.format("%.2f", this / 1000)} k"
    }
  } catch (e: Exception) {
    return valorFormatado
  }

  return valorFormatado
}
