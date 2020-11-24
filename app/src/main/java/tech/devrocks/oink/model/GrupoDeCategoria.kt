package tech.devrocks.oink.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class GrupoDeCategoria(
  val codigoClassificacao: String?,
  val nomeClassificacao: String?,
  val nome: String?,

  ) : Parcelable {

  companion object CREATOR : Parcelable.Creator<GrupoDeCategoria> {
    fun Parcel.writeDate(date: Date?) {
      writeLong(date?.time ?: -1)
    }


    override fun createFromParcel(parcel: Parcel): GrupoDeCategoria {
      return GrupoDeCategoria(parcel)
    }

    override fun newArray(size: Int): Array<GrupoDeCategoria?> {
      return arrayOfNulls(size)
    }
  }

  constructor(parcel: Parcel) : this(
    parcel.readString(),
    parcel.readString(),
    parcel.readString()

  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(codigoClassificacao)
    parcel.writeString(nomeClassificacao)
    parcel.writeString(nome)

  }

  override fun describeContents(): Int {
    return 0
  }


}