package tech.devrocks.oink.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_formas_pagamento.rv_lista_formas_pagamento
import kotlinx.android.synthetic.main.selecao_categoria.*
import tech.devrocks.oink.R
import tech.devrocks.oink.adapters.CategoriaAdapter
import tech.devrocks.oink.mock.CategoriaMock
import tech.devrocks.oink.model.Categoria


class SelecaoCategoriaFragment : DialogFragment() {
    val TAG = "SELECAO_CATEGORIA"
    val CHAVE_REQUISICAO = "SELECIONAR_CATEGORIA"
    val CHAVE_RESPOSTA = "CATEGORIA_SELECIONADA"

    private lateinit var recyclerView: RecyclerView
    private lateinit var categoriaAdapter: CategoriaAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var listaCategorias: ArrayList<Categoria> = ArrayList()


    fun exibir(fragmentManager: FragmentManager) {
        SelecaoCategoriaFragment().show(fragmentManager, TAG)
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
        val view: View = inflater.inflate(R.layout.selecao_categoria, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarRecyclerview()
        iv_fechar_selecao_categoria.setOnClickListener {
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

    private fun configurarRecyclerview() {

        viewManager = LinearLayoutManager(requireContext())
        categoriaAdapter = CategoriaAdapter(listaCategorias) {
            val result = Bundle()
            result.putParcelable(CHAVE_RESPOSTA, it)
            parentFragmentManager.setFragmentResult(CHAVE_REQUISICAO, result)
            dismiss()
        }

        recyclerView = rv_lista_categorias.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = categoriaAdapter
        }

        obterFormasPagamentoCadastradas()
    }

    private fun obterFormasPagamentoCadastradas() {
        val mock = CategoriaMock()
        listaCategorias = mock.obterListaDeCategorias()
        categoriaAdapter.atualizarLista(listaCategorias)

//    val indicadorCarregamento = DialogFactory(requireContext()).dialogLoading
//    indicadorCarregamento.show()
//
//    RetrofitClient.contaBancariaService.obterContasBancariasCadastradas()
//      .enqueue(object : Callback<RespostaPadraoApi> {
//        override fun onResponse(
//          call: Call<RespostaPadraoApi>,
//          response: Response<RespostaPadraoApi>
//        ) {
//          if (response.code() == 200) {
//            val dadosResposta = response.body()!!
//
//            listaContasBancarias = ArrayList(
//              Gson().fromJson(
//                dadosResposta.dados.getAsJsonArray("contasBancarias"),
//                Array<ContaBancaria>::class.java
//              ).toList()
//            )
//
//            contaBancariaAdapter.atualizarListaContas(listaContasBancarias)
//          } else {
//            Toast.makeText(requireContext(), response.errorBody().toString(), Toast.LENGTH_LONG)
//          }
//
//          indicadorCarregamento.dismiss()
//        }
//
//        override fun onFailure(call: Call<RespostaPadraoApi>, t: Throwable) {
//          Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
//        }
//
//      })
    }


}