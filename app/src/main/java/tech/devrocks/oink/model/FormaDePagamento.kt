package tech.devrocks.oink.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class FormaDePagamento(
  val _id: String?,
  val codigo:String?,
  val nome: String?,
  val dataCadastro: Date?

) : Parcelable {

  companion object CREATOR : Parcelable.Creator<FormaDePagamento> {
    fun Parcel.writeDate(date: Date?) {
      writeLong(date?.time ?: -1)
    }

    fun Parcel.readDate(): Date? {
      val long = readLong()
      return if (long != -1L) Date(long) else null
    }


    override fun createFromParcel(parcel: Parcel): FormaDePagamento {
      return FormaDePagamento(parcel)
    }

    override fun newArray(size: Int): Array<FormaDePagamento?> {
      return arrayOfNulls(size)
    }
  }

  constructor(parcel: Parcel) : this(
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readDate()

  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(_id)
    parcel.writeString(codigo)
    parcel.writeString(nome)
    parcel.writeDate(dataCadastro)

  }

  override fun describeContents(): Int {
    return 0
  }


}