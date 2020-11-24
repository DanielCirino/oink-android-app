package tech.devrocks.oink.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class TipoDeRegistro(
  val codigo: String?,
  val nome: String?,

  ) : Parcelable {

  companion object CREATOR : Parcelable.Creator<TipoDeRegistro> {
    fun Parcel.writeDate(date: Date?) {
      writeLong(date?.time ?: -1)
    }


    override fun createFromParcel(parcel: Parcel): TipoDeRegistro {
      return TipoDeRegistro(parcel)
    }

    override fun newArray(size: Int): Array<TipoDeRegistro?> {
      return arrayOfNulls(size)
    }
  }

  constructor(parcel: Parcel) : this(

    parcel.readString(),
    parcel.readString()

  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {

    parcel.writeString(codigo)
    parcel.writeString(nome)

  }

  override fun describeContents(): Int {
    return 0
  }


}