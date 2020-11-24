package tech.devrocks.oink.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class Conta(
  val _id: String?,
  val idTipoDeConta: String?,
  val codigoTipoDeConta: String?,
  val nomeTipoDeConta: String?,
  val nomeConta: String?,
  val codigoBanco: String?,
  val numeroAgencia: String?,
  val numeroConta: String?,
  val numeroDocumento: String?,
  val dataCadastro: Date?,
//  val permiteParcelamento: Boolean,
//  val diaVencimento: Int

) : Parcelable {

  companion object CREATOR : Parcelable.Creator<Conta> {
    fun Parcel.writeDate(date: Date?) {
      writeLong(date?.time ?: -1)
    }

    fun Parcel.readDate(): Date? {
      val long = readLong()
      return if (long != -1L) Date(long) else null
    }

    fun Parcel.readBool(): Boolean {
      return readInt() == 1
    }

    fun Parcel.writeBool(bool: Boolean) {
      writeInt(if (bool) 1 else 0)
    }


    override fun createFromParcel(parcel: Parcel): Conta {
      return Conta(parcel)
    }

    override fun newArray(size: Int): Array<Conta?> {
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
    parcel.readString(),
    parcel.readDate(),
//    parcel.readBool(),
//    parcel.readInt()

  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(_id)
    parcel.writeString(nomeConta)
    parcel.writeString(idTipoDeConta)
    parcel.writeString(codigoTipoDeConta)
    parcel.writeString(nomeTipoDeConta)
    parcel.writeString(codigoBanco)
    parcel.writeString(numeroAgencia)
    parcel.writeString(numeroConta)
    parcel.writeString(numeroDocumento)
    parcel.writeDate(dataCadastro)
//    parcel.writeBool(permiteParcelamento)
//    parcel.writeInt(diaVencimento)

  }

  override fun describeContents(): Int {
    return 0
  }


}