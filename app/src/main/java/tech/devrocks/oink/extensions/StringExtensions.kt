package app.tipsarena.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.converterParaDataUTC(
    mascara: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
    timeZone: String = "GMT"
): Date {
  return try {
    val parser = SimpleDateFormat(mascara, Locale.getDefault())
    var novaData = parser.parse(this)

    val timezoneOffset = novaData.obterTimezoneOffset() * -1
    novaData.adicionarHoras(timezoneOffset).also { novaData = it }

    novaData

  } catch (e: ParseException) {
    e.printStackTrace()
    Date()
  }

}

fun String.converterParaDataLocal(
    mascara: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
    timeZone: String = "UTC"
): Date {
  return try {
    val parser = SimpleDateFormat(mascara, Locale.getDefault())
    parser.timeZone = TimeZone.getTimeZone(timeZone)

    parser.parse(this)

  } catch (e: ParseException) {
    e.printStackTrace()
    Date()
  }

}


fun String.converterParaData(mascara: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"): Date {
  return try {
    val parser = SimpleDateFormat(mascara, Locale.getDefault())
    val novaData = parser.parse(this)

    novaData

  } catch (e: ParseException) {
    e.printStackTrace()
    Date()
  }

}

