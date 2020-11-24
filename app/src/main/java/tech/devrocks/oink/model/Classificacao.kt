package tech.devrocks.oink.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class Classificacao(
  val codigo: String?,
  val nome: String?,
  val tipoDeRegistro: String?

) : Parcelable {

  companion object CREATOR : Parcelable.Creator<Classificacao> {
    fun Parcel.writeDate(date: Date?) {
      writeLong(date?.time ?: -1)
    }


    override fun createFromParcel(parcel: Parcel): Classificacao {
      return Classificacao(parcel)
    }

    override fun newArray(size: Int): Array<Classificacao?> {
      return arrayOfNulls(size)
    }
  }

  constructor(parcel: Parcel) : this(
    parcel.readString(),
    parcel.readString(),
    parcel.readString()

  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {

    parcel.writeString(codigo)
    parcel.writeString(nome)
    parcel.writeString(tipoDeRegistro)

  }

  override fun describeContents(): Int {
    return 0
  }


}