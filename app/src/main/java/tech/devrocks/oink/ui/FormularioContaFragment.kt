package tech.devrocks.oink.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.conta_bancaria_formulario.*
import tech.devrocks.oink.R
import tech.devrocks.oink.model.Conta
import tech.devrocks.oink.model.TipoDeConta
import java.util.*

class FormularioContaFragment : DialogFragment() {
  val TAG = "FORMULARIO_CONTAS"
  val CHAVE_REQUISICAO = "DADOS_CONTA"
  val CHAVE_RESPOSTA = "CONTA"

  val selecaoTipoConta = SelecaoTipoContaFragment()
  lateinit var tipoContaSelecionado: TipoDeConta

  fun exibir(fragmentManager: FragmentManager) {
    FormularioContaFragment().show(fragmentManager, TAG)
  }


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)

    parentFragmentManager.setFragmentResultListener(selecaoTipoConta.CHAVE_REQUISICAO, this,
      { _, bundle ->

        val result = bundle.getParcelable<TipoDeConta>(selecaoTipoConta.CHAVE_RESPOSTA)
        if (result != null) {
          exibirInformacoesTipoConta(result)
        }

      })
  }

  private fun exibirInformacoesTipoConta(tipoDeConta: TipoDeConta) {
//        Toast.makeText(context, tipoDeConta.nome, Toast.LENGTH_LONG).show()
    tipoContaSelecionado = tipoDeConta
    container_tipo_conta.visibility = View.VISIBLE
    btn_selecionar_tipo_conta.visibility = View.GONE

    tv_nome_tipo_conta.text = tipoContaSelecionado.nome
    tv_codigo_tipo_conta.text = tipoDeConta.codigo

    container_tipo_conta.setOnClickListener {
      selecaoTipoConta.exibir(parentFragmentManager)
    }


  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    super.onCreateView(inflater, container, savedInstanceState)

    return inflater.inflate(R.layout.conta_bancaria_formulario, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    iv_fechar_formulario_conta.setOnClickListener {
      dismiss()
    }

    btn_selecionar_tipo_conta.setOnClickListener {
      selecaoTipoConta.exibir(parentFragmentManager)
    }

    btn_salvar_conta.setOnClickListener {
      val conta = Conta(
        null,
        tipoContaSelecionado._id,
        tipoContaSelecionado.codigo,
        tipoContaSelecionado.nome,
        et_nome_conta.text.toString(),
        et_codigo_banco.text.toString(),
        et_codigo_agencia.text.toString(),
        et_numero_conta.text.toString(),
        et_numero_documento.text.toString(),

        Date(),


        )
      val result = Bundle()
      result.putParcelable(CHAVE_RESPOSTA, conta)
      parentFragmentManager.setFragmentResult(CHAVE_REQUISICAO, result)
      dismiss()
    }
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


}