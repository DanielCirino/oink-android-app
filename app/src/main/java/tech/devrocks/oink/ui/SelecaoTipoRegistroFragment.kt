package tech.devrocks.oink.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.selecao_tipo_registro_fragment.*
import tech.devrocks.oink.R
import tech.devrocks.oink.model.TipoDeRegistro


class SelecaoTipoRegistroFragment : DialogFragment() {
  val TAG = "SELECAO_TIPO_REGISTRO"
  val CHAVE_REQUISICAO = "SELECIONAR_TIPO_REGISTRO"
  val CHAVE_RESPOSTA = "TIPO_REGISTRO_SELECIONADO"

  lateinit var tipoRegistroSelecionado: TipoDeRegistro

  fun exibir(fragmentManager: FragmentManager) {
    SelecaoTipoRegistroFragment().show(fragmentManager, TAG)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
//    setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    super.onCreateView(inflater, container, savedInstanceState)
    val view: View = inflater.inflate(R.layout.selecao_tipo_registro_fragment, container, false)

    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    configurarControles()


  }

  override fun onStart() {
    super.onStart()
    val dialog: Dialog? = dialog
    if (dialog != null) {
      val width = ViewGroup.LayoutParams.MATCH_PARENT
      val height = ViewGroup.LayoutParams.MATCH_PARENT
      dialog.window!!.setLayout(width, height)
      dialog.window!!.setWindowAnimations(R.style.AppTheme_Slide)
    }
  }

  private fun configurarControles() {

    iv_fechar_selecao_pote_gasto.setOnClickListener {
      dismiss()
    }

    container_tipo_registro_entrada.setOnClickListener {
      tipoRegistroSelecionado = TipoDeRegistro(
        "E", "Entrada"
      )
      atualizarIndicadorSelecao("E")
    }

    container_tipo_registro_saida.setOnClickListener {
      tipoRegistroSelecionado = TipoDeRegistro(
        "S", "Saída"
      )

      atualizarIndicadorSelecao("S")
    }

    btn_confirmar_selecao_tipo_registro.setOnClickListener {
      if (::tipoRegistroSelecionado.isInitialized) {
        retornarTipoRegistroSelecionado(tipoRegistroSelecionado)
      } else {

        Toast.makeText(requireContext(), "Selecione um tipo de registro!", Toast.LENGTH_LONG)
          .show()
      }
    }


  }

  private fun atualizarIndicadorSelecao(codigo: String) {
    when (codigo) {
      "E" -> {
        iv_indicador_entrada.setColorFilter(ContextCompat.getColor(requireContext(),R.color.green))
        iv_indicador_saida.setColorFilter(ContextCompat.getColor(requireContext(),R.color.comment))
      }
      "S" -> {
        iv_indicador_entrada.setColorFilter(ContextCompat.getColor(requireContext(),R.color.comment))
        iv_indicador_saida.setColorFilter(ContextCompat.getColor(requireContext(),R.color.green))
      }
    }
  }

  private fun retornarTipoRegistroSelecionado(tipoRegistro: TipoDeRegistro) {
    val result = Bundle()
    result.putParcelable(CHAVE_RESPOSTA, tipoRegistro)
    parentFragmentManager.setFragmentResult(CHAVE_REQUISICAO, result)
    dismiss()
  }


}