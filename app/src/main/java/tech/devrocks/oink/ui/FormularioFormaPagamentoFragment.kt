package tech.devrocks.oink.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.fragment_formulario_forma_pagamento.*
import tech.devrocks.oink.R
import tech.devrocks.oink.model.FormaDePagamento
import java.util.*

class FormularioFormaPagamentoFragment : DialogFragment() {
    val TAG = "FORMULARIO_FORMA_PAGAMENTO"
    val CHAVE_REQUISICAO = "DADOS_FORMA_PAGAMENTO"
    val CHAVE_RESPOSTA = "FORMA_PAGAMENTO"

    fun exibir(fragmentManager: FragmentManager) {
        FormularioFormaPagamentoFragment().show(fragmentManager, TAG)
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
        return inflater.inflate(R.layout.fragment_formulario_forma_pagamento, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iv_fechar_formulario_registro_financeiro.setOnClickListener {
            dismiss()
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


}