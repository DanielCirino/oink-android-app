package tech.devrocks.oink.ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*


class SeletorDataFragment() : DialogFragment(), DatePickerDialog.OnDateSetListener {
  val TAG = "SELETOR_DATA"
  var CHAVE_REQUISICAO = "SELECIONAR_DATA"
  var CHAVE_RESPOSTA = "DATA"

  constructor(chaveRequisicao: String?, chaveResposta: String?) : this() {
    if (chaveRequisicao != null) {
      this.CHAVE_REQUISICAO = chaveRequisicao
    }
    if (chaveResposta != null) {
      this.CHAVE_RESPOSTA = chaveResposta
    }
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

    val calendario = Calendar.getInstance()
    val ano = calendario.get(Calendar.YEAR)
    val mes = calendario.get(Calendar.MONTH)
    val dia = calendario.get(Calendar.DAY_OF_MONTH)

    return DatePickerDialog(requireContext(), this, ano, mes, dia)
  }

  override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
    dismiss()
    val cal = Calendar.getInstance()
    cal[Calendar.YEAR] = year
    cal[Calendar.MONTH] = month
    cal[Calendar.DAY_OF_MONTH] = day
    cal[Calendar.HOUR_OF_DAY] = 0
    cal[Calendar.MINUTE] = 0
    cal[Calendar.SECOND] = 0
    cal[Calendar.MILLISECOND] = 0
    val result = Bundle().apply {
      this.putLong(CHAVE_RESPOSTA, cal.timeInMillis)
    }
    parentFragmentManager.setFragmentResult(CHAVE_REQUISICAO, result)

  }


}


