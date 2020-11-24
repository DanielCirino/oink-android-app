package app.tipsarena.extensions


import java.text.SimpleDateFormat
import java.util.*


fun Date.formatarData(format: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"): String? {
  val formatador = SimpleDateFormat(format)
  return formatador.format(this)
}

fun Date.formatarUTC(mascara: String, timeZone: TimeZone = TimeZone.getTimeZone("GMT")): String {

  val formatador = SimpleDateFormat(mascara, Locale.getDefault())
  formatador.timeZone = timeZone

  return formatador.format(this)

}


fun Date.converterLocalParaUtc(formato: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"): Date? {
  val timezoneOffset = this.obterTimezoneOffset()
  val novaData = this.adicionarHoras(timezoneOffset)

//    val formatador = SimpleDateFormat(formato,Locale.getDefault())


  return novaData

}

fun Date.obterTimezoneOffset(): Int {
  val tz = TimeZone.getDefault()
  val cal = GregorianCalendar.getInstance(tz)
  val offsetInMillis = tz.getOffset(cal.timeInMillis)

  val offsetInMinutes = offsetInMillis / 3600000
  var offset = String.format(
    "%02d:%02d",
    Math.abs(offsetInMillis / 3600000),
    Math.abs(offsetInMillis / 60000 % 60)
  )
  offset = (if (offsetInMillis >= 0) "+" else "-") + offset

  return offsetInMinutes
}

fun Date.adicionarHoras(horas: Int): Date? {
  try {
    val calendar = Calendar.getInstance()
    calendar.setTime(this)
    calendar.add(Calendar.HOUR_OF_DAY, horas)
    return calendar.time
  } catch (e: Exception) {
    return this
  }
}

fun Date.adicionarMinutos(minutos: Int): Date? {
  try {
    val calendar = Calendar.getInstance()
    calendar.setTime(this)
    calendar.add(Calendar.MINUTE, minutos)
    return calendar.time
  } catch (e: Exception) {
    return this
  }
}

