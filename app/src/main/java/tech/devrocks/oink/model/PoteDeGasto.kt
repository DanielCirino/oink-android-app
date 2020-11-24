package tech.devrocks.oink.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class PoteDeGasto(
  val codigo: String?,
  val nome: String?,
  val descricao: String?,
  val capacidade:Int

  ) : Parcelable {

  companion object CREATOR : Parcelable.Creator<PoteDeGasto> {
    fun Parcel.writeDate(date: Date?) {
      writeLong(date?.time ?: -1)
    }


    override fun createFromParcel(parcel: Parcel): PoteDeGasto {
      return PoteDeGasto(parcel)
    }

    override fun newArray(size: Int): Array<PoteDeGasto?> {
      return arrayOfNulls(size)
    }
  }

  constructor(parcel: Parcel) : this(
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readInt()

  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(codigo)
    parcel.writeString(descricao)
    parcel.writeString(nome)
    parcel.writeInt(capacidade)

  }

  override fun describeContents(): Int {
    return 0
  }


}