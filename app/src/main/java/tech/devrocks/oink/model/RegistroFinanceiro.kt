package tech.devrocks.oink.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class RegistroFinanceiro(
  val _id:String?,
  val dataMovimentacao: Date?,
  val dataRegistro: Date?,
  val dataVencimento: Date?,
  val codigoTipoRegistro:String?,
  val nomeTipoDeRegistro: String?,
  val codigoClassificacao: String?,
  val nomeClassificacao: String?,
  val descricao: String?,
  val valor: Double,
  val idConta:String?,
  val codigoConta:String?,
  val nomeConta:String?,
  val idFormaDePagamento:String?,
  val nomeFormaDePagamento: String?,
  val status: String?,
  val numeroDaParcela: Int,
  val totalDeParcelas: Int,
  val periodoRegistro:String?,
  val periodoVencimento:String?
) : Parcelable {
  companion object CREATOR : Parcelable.Creator<RegistroFinanceiro> {
    fun Parcel.writeDate(date: Date?) {
      writeLong(date?.time ?: -1)
    }

    fun Parcel.readDate(): Date? {
      val long = readLong()
      return if (long != -1L) Date(long) else null
    }

    override fun createFromParcel(parcel: Parcel): RegistroFinanceiro {
      return RegistroFinanceiro(parcel)
    }

    override fun newArray(size: Int): Array<RegistroFinanceiro?> {
      return arrayOfNulls(size)
    }
  }

  constructor(parcel: Parcel) : this(
    parcel.readString(),
    parcel.readDate(),
    parcel.readDate(),
    parcel.readDate(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readDouble(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readInt(),
    parcel.readInt(),
    parcel.readString(),
    parcel.readString()
  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(_id)
    parcel.writeDate(dataRegistro)
    parcel.writeDate(dataMovimentacao)
    parcel.writeDate(dataVencimento)
    parcel.writeString(codigoTipoRegistro)
    parcel.writeString(nomeTipoDeRegistro)
    parcel.writeString(codigoClassificacao)
    parcel.writeString(nomeClassificacao)
    parcel.writeString(descricao)
    parcel.writeDouble(valor)
    parcel.writeString(idFormaDePagamento)
    parcel.writeString(idConta)
    parcel.writeString(codigoConta)
    parcel.writeString(nomeConta)
    parcel.writeString(nomeFormaDePagamento)
    parcel.writeString(status)
    parcel.writeInt(numeroDaParcela)
    parcel.writeInt(totalDeParcelas)
    parcel.writeString(periodoRegistro)
    parcel.writeString(periodoVencimento)
  }

  override fun describeContents(): Int {
    return 0
  }


}