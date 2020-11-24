package tech.devrocks.oink.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import app.tipsarena.extensions.formatarData
import kotlinx.android.synthetic.main.fragment_formulario_categoria.btn_selecionar_categoria_registro
import kotlinx.android.synthetic.main.fragment_formulario_categoria.btn_selecionar_pote_gasto
import kotlinx.android.synthetic.main.fragment_formulario_forma_pagamento.btn_salvar_registro_financeiro
import kotlinx.android.synthetic.main.fragment_formulario_forma_pagamento.iv_fechar_formulario_registro_financeiro
import kotlinx.android.synthetic.main.fragment_formulario_registro_financeiro.*
import tech.devrocks.oink.R
import tech.devrocks.oink.model.Categoria
import tech.devrocks.oink.model.Conta
import tech.devrocks.oink.model.FormaDePagamento
import tech.devrocks.oink.model.PoteDeGasto
import java.util.*

class FormularioRegistroFinanceiroFragment : DialogFragment() {

  val TAG = "FORMULARIO_REGISTRO_FINANCEIRO"
  val CHAVE_REQUISICAO = "DADOS_REGISTRO_FINANCEIRO"
  val CHAVE_RESPOSTA = "REGISTRO_FINANCEIRO"

  lateinit var dataMovimento: Date
  lateinit var dataVencimento: Date

  lateinit var categoriaSelecionada: Categoria
  lateinit var contaSelecionada: Conta
  lateinit var formaPagamentoSelecionada: FormaDePagamento
  lateinit var poteGastoSelecionada: PoteDeGasto

  fun exibir(fragmentManager: FragmentManager) {
    FormularioRegistroFinanceiroFragment().show(fragmentManager, TAG)
  }


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    super.onCreateView(inflater, container, savedInstanceState)
    return inflater.inflate(R.layout.fragment_formulario_registro_financeiro, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    configurarControles()
  }

  private fun configurarControles() {
    val seletorDataMovimento = SeletorDataFragment("SELETOR_DATA_MOVIMENTO", "DATA_MOVIMENTO")
    val seletorDataBaixa = SeletorDataFragment("SELETOR_DATA_BAIXA", "DATA_BAIXA")

    iv_fechar_formulario_registro_financeiro.setOnClickListener {
      dismiss()
    }

    btn_selecionar_categoria_registro.setOnClickListener {
      abrirSelecaoCategoria()
    }

    btn_selecionar_conta_registro.setOnClickListener {
      abrirSelecaoConta()
    }

    btn_selecionar_forma_pagamento_registro.setOnClickListener {
      abrirSelecaoFormaPagamento()
    }

    btn_selecionar_pote_gasto.setOnClickListener {
      abrirSelecaoPoteGasto()
    }

    btn_selecionar_data_movimentacao.setOnClickListener {
      seletorDataMovimento.show(parentFragmentManager, seletorDataMovimento.TAG)
      parentFragmentManager.setFragmentResultListener(seletorDataMovimento.CHAVE_REQUISICAO, this,
        FragmentResultListener { key, bundle ->
          val result = bundle.getLong(seletorDataMovimento.CHAVE_RESPOSTA)
          if (result != null) {
            val calendario = Calendar.getInstance()
            calendario.timeInMillis = result

            this.dataMovimento = calendario.time
            tv_data_movimentacao.text = dataMovimento.formatarData("dd/MM/yyyy")
          }

        })
    }

    btn_selecionar_data_vencimento_registro.setOnClickListener {
      seletorDataBaixa.show(parentFragmentManager, seletorDataBaixa.TAG)
      parentFragmentManager.setFragmentResultListener(seletorDataBaixa.CHAVE_REQUISICAO, this,
        FragmentResultListener { key, bundle ->
          val result = bundle.getLong(seletorDataBaixa.CHAVE_RESPOSTA)
          if (result != null) {
            val calendario = Calendar.getInstance()
            calendario.timeInMillis = result

            this.dataVencimento = calendario.time
            tv_data_vencimento_registro.text = dataVencimento.formatarData(("dd/MM/yyyy"))
          }

        })
    }

    btn_salvar_registro_financeiro.setOnClickListener {
////      val formaPagamento = FormaDePagamento(
////          null,
////          et_codigo_forma_pagamento.text.toString(),
////          et_nome_forma_pagamento.text.toString(),
////          Date(),
////      )
////      val result = Bundle()
////      result.putParcelable(CHAVE_RESPOSTA, formaPagamento)
////      parentFragmentManager.setFragmentResult(CHAVE_REQUISICAO, result)
//      dismiss()
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

  private fun abrirSelecaoCategoria() {
    val selecaoCategoria = SelecaoCategoriaFragment()

    parentFragmentManager.setFragmentResultListener(selecaoCategoria.CHAVE_REQUISICAO,
      this,
      { _, bundle ->

        val categoria =
          bundle.getParcelable<Categoria>(selecaoCategoria.CHAVE_RESPOSTA)
        if (categoria != null) {
          processarDadosCategoria(categoria)
        }

      })

    selecaoCategoria.exibir(parentFragmentManager)

  }

  private fun abrirSelecaoConta() {
    val selecaoConta = SelecaoContaBancariaFragment()

    parentFragmentManager.setFragmentResultListener(selecaoConta.CHAVE_REQUISICAO,
      this,
      { _, bundle ->

        val conta =
          bundle.getParcelable<Conta>(selecaoConta.CHAVE_RESPOSTA)
        if (conta != null) {
          processarDadosConta(conta)
        }

      })

    selecaoConta.exibir(parentFragmentManager)

  }

  private fun abrirSelecaoFormaPagamento() {
    val selecaoFormaPagamento = SelecaoFormaPagamentoFragment()

    parentFragmentManager.setFragmentResultListener(selecaoFormaPagamento.CHAVE_REQUISICAO,
      this,
      { _, bundle ->

        val formaDePagamento =
          bundle.getParcelable<FormaDePagamento>(selecaoFormaPagamento.CHAVE_RESPOSTA)
        if (formaDePagamento != null) {
          processarDadosFormaPagamento(formaDePagamento)
        }

      })

    selecaoFormaPagamento.exibir(parentFragmentManager)

  }

  private fun abrirSelecaoPoteGasto() {
    val selecaoPoteGasto = SelecaoPoteGastoFragment()

    parentFragmentManager.setFragmentResultListener(selecaoPoteGasto.CHAVE_REQUISICAO,
      this,
      { _, bundle ->
        val poteDeGasto =
          bundle.getParcelable<PoteDeGasto>(selecaoPoteGasto.CHAVE_RESPOSTA)
        if (poteDeGasto != null) {
          processarDadosPoteGasto(poteDeGasto)
        }

      })

    selecaoPoteGasto.exibir(parentFragmentManager)

  }

  private fun processarDadosCategoria(categoria: Categoria) {
    categoriaSelecionada = categoria
    tv_categoria_registro.text = categoria.nome
  }

  private fun processarDadosConta(conta: Conta) {
    contaSelecionada = conta
    tv_conta_registro.text = conta.nomeConta
  }

  private fun processarDadosFormaPagamento(formaDePagamento: FormaDePagamento) {
    formaPagamentoSelecionada = formaDePagamento
    tv_forma_pagamento_registro.text = formaDePagamento.nome
  }

  private fun processarDadosPoteGasto(poteGasto: PoteDeGasto) {
    poteGastoSelecionada = poteGasto
    tv_pote_gasto.text = poteGasto.nome
  }

}