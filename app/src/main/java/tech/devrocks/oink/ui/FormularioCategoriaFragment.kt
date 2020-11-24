package tech.devrocks.oink.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.fragment_formulario_categoria.*
import kotlinx.android.synthetic.main.fragment_formulario_forma_pagamento.*
import kotlinx.android.synthetic.main.fragment_formulario_forma_pagamento.btn_salvar_registro_financeiro
import kotlinx.android.synthetic.main.fragment_formulario_forma_pagamento.et_nome_forma_pagamento
import kotlinx.android.synthetic.main.fragment_formulario_forma_pagamento.iv_fechar_formulario_registro_financeiro
import tech.devrocks.oink.R
import tech.devrocks.oink.model.Classificacao
import tech.devrocks.oink.model.FormaDePagamento
import tech.devrocks.oink.model.GrupoDeCategoria
import tech.devrocks.oink.model.TipoDeRegistro
import java.util.*

class FormularioCategoriaFragment : DialogFragment() {
  val TAG = "FORMULARIO_CATEGORIA"
  val CHAVE_REQUISICAO = "DADOS_CATEGORIA"
  val CHAVE_RESPOSTA = "CATEGORIA"

  lateinit var tipoRegistroSelecionado: TipoDeRegistro
  lateinit var classificacaoSelecionada: Classificacao
  lateinit var grupoSelecionado:GrupoDeCategoria

  fun exibir(fragmentManager: FragmentManager) {
    FormularioCategoriaFragment().show(fragmentManager, TAG)
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
    return inflater.inflate(R.layout.fragment_formulario_categoria, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    iv_fechar_formulario_registro_financeiro.setOnClickListener {
      dismiss()
    }

    btn_selecionar_categoria_registro.setOnClickListener {
      abrirSelecaoTipoDeRegistro()
    }

    btn_selecionar_pote_gasto.setOnClickListener {
      abrirSelecaoClassificacao()
    }

    btn_selecionar_grupo_categoria.setOnClickListener {
      abrirSelecaoDeGrupo()
    }

    btn_salvar_registro_financeiro.setOnClickListener {
      val formaPagamento = FormaDePagamento(
          null,
          et_codigo_forma_pagamento.text.toString(),
          et_nome_forma_pagamento.text.toString(),
          Date(),
      )
      val result = Bundle()
      result.putParcelable(CHAVE_RESPOSTA, formaPagamento)
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

  private fun abrirSelecaoTipoDeRegistro() {
    val selecaoTipoRegistroFragment = SelecaoTipoRegistroFragment()

    parentFragmentManager.setFragmentResultListener(selecaoTipoRegistroFragment.CHAVE_REQUISICAO,
        this,
        { _, bundle ->

            val tipoRegistro =
                bundle.getParcelable<TipoDeRegistro>(selecaoTipoRegistroFragment.CHAVE_RESPOSTA)
            if (tipoRegistro != null) {
                processarDadosTipoRegistro(tipoRegistro)
            }

        })

    selecaoTipoRegistroFragment.exibir(parentFragmentManager)

  }

  private fun abrirSelecaoClassificacao() {
    val selecaoClassificacao = SelecaoClassificaoFragment()

    parentFragmentManager.setFragmentResultListener(selecaoClassificacao.CHAVE_REQUISICAO,
      this,
      { _, bundle ->

        val classificacao =
          bundle.getParcelable<Classificacao>(selecaoClassificacao.CHAVE_RESPOSTA)
        if (classificacao != null) {
          processarDadosClassificacao(classificacao)
        }

      })

    selecaoClassificacao.exibir(parentFragmentManager)

  }

  private fun abrirSelecaoDeGrupo() {
    val selecaoDeGrupo = SelecaoGrupoCategoriaFragment()

    parentFragmentManager.setFragmentResultListener(selecaoDeGrupo.CHAVE_REQUISICAO,
      this,
      { _, bundle ->

        val grupoDeCategoria =
          bundle.getParcelable<GrupoDeCategoria>(selecaoDeGrupo.CHAVE_RESPOSTA)
        if (grupoDeCategoria != null) {
          processarDadosGrupo(grupoDeCategoria)
        }

      })

    selecaoDeGrupo.exibir(parentFragmentManager)

  }

  private fun processarDadosTipoRegistro(tipoRegistro: TipoDeRegistro) {
    tipoRegistroSelecionado = tipoRegistro
    tv_tipo_registro.text = tipoRegistro.nome
  }

  private fun processarDadosClassificacao(classificacao: Classificacao) {
    classificacaoSelecionada = classificacao
    tv_classificacao.text = classificacao.nome
  }

  private fun processarDadosGrupo(grupoDeCategoria: GrupoDeCategoria) {
    grupoSelecionado=grupoDeCategoria
    tv_grupo_categoria.text = grupoDeCategoria.nome
  }


}