package tech.devrocks.oink.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class Categoria(
  val _id: String?,
  val codigoTipoRegistro:String?, //S
  val nomeTipoRegistro: String?, // Saída
  val codigoClassificacao: String?, // DF
  val nomeClassificacao: String?, //DESPESA_FIXA
  val idGrupoCategoria:String?,
  val nomeGrupoCategoria:String?,//Habitação
  val nome: String?, // Prestação do imóvel
  val dataCadastro: Date?

) : Parcelable {

  companion object CREATOR : Parcelable.Creator<Categoria> {
    fun Parcel.writeDate(date: Date?) {
      writeLong(date?.time ?: -1)
    }

    fun Parcel.readDate(): Date? {
      val long = readLong()
      return if (long != -1L) Date(long) else null
    }


    override fun createFromParcel(parcel: Parcel): Categoria {
      return Categoria(parcel)
    }

    override fun newArray(size: Int): Array<Categoria?> {
      return arrayOfNulls(size)
    }
  }

  constructor(parcel: Parcel) : this(
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readDate()

  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(_id)
    parcel.writeString(codigoTipoRegistro)
    parcel.writeString(nomeTipoRegistro)
    parcel.writeString(codigoClassificacao)
    parcel.writeString(nomeClassificacao)
    parcel.writeString(idGrupoCategoria)
    parcel.writeString(nomeGrupoCategoria)
    parcel.writeString(nome)
    parcel.writeDate(dataCadastro)
  }

  override fun describeContents(): Int {
    return 0
  }


}