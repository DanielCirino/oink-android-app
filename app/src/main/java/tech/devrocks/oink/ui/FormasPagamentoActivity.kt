package tech.devrocks.oink.ui

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_contas_bancarias.container_sem_dados
import kotlinx.android.synthetic.main.activity_formas_pagamento.*
import tech.devrocks.oink.R
import tech.devrocks.oink.adapters.FormaPagamentoAdapter
import tech.devrocks.oink.mock.FormaDePagamentoMock
import tech.devrocks.oink.model.FormaDePagamento

class FormasPagamentoActivity : AppCompatActivity() {
  private lateinit var recyclerView: RecyclerView
  private lateinit var formaDePagamentoAdapter: FormaPagamentoAdapter
  private lateinit var viewManager: RecyclerView.LayoutManager
  private var listaDeFormasDePagamento: ArrayList<FormaDePagamento> = ArrayList()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_formas_pagamento)

    configurarControles()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_novo_atualizar_fechar, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
        R.id.acao_menu_pedido_fechar -> {
            finish()
        }
        R.id.acao_menu_calculo_atualizar -> {
//                obterContasCadastradas()
        }
        R.id.acao_menu_calculo_incluir -> {
            abrirFormularioFormaPagamento()
        }

    }
    return super.onOptionsItemSelected(item)
  }

  private fun configurarControles() {
//        indicadorCarregamento = DialogFactory(this).dialogLoading
    configurarToolbar()
    configurarRecyclerview()

    fab_nova_forma_pagamento.setOnClickListener {
      abrirFormularioFormaPagamento()
    }
  }

  private fun configurarToolbar() {
    if (Build.VERSION.SDK_INT >= 21) window.statusBarColor =
      ContextCompat.getColor(this, R.color.background)
    setSupportActionBar(toolbar_formas_pagamento)

    val actionBar = supportActionBar

    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(false)
      actionBar.setHomeButtonEnabled(true)
      actionBar.setHomeAsUpIndicator(R.drawable.ic_fechar)
    }


  }

  private fun configurarRecyclerview() {

    viewManager = LinearLayoutManager(this)
    formaDePagamentoAdapter = FormaPagamentoAdapter(listaDeFormasDePagamento) {
      Toast.makeText(this, it.nome, Toast.LENGTH_LONG).show()
    }

    recyclerView = rv_lista_formas_pagamento.apply {
      setHasFixedSize(true)
      layoutManager = viewManager
      adapter = formaDePagamentoAdapter
    }

    obterFormasPagamentoCadastradas()


  }

  private fun abrirFormularioFormaPagamento() {
    val formularioFormaDePagamento = FormularioFormaPagamentoFragment()

    supportFragmentManager.setFragmentResultListener(formularioFormaDePagamento.CHAVE_REQUISICAO,
        this,
        { _, bundle ->

            val formaPagamento =
                bundle.getParcelable<FormaDePagamento>(formularioFormaDePagamento.CHAVE_RESPOSTA)
            if (formaPagamento != null) {
                processarDadosFormaPagamento(formaPagamento)
            }

        })


    formularioFormaDePagamento.exibir(supportFragmentManager)
  }

  private fun processarDadosFormaPagamento(formaPagamento: FormaDePagamento) {
    Toast.makeText(this, formaPagamento.nome, Toast.LENGTH_LONG).show()
  }

  private fun obterFormasPagamentoCadastradas() {

    listaDeFormasDePagamento = FormaDePagamentoMock().obterListaFormasDePagamento()
    exibirFormasPagamento()

//        indicadorCarregamento.show()
//
//        RetrofitClient.contaBancariaService.obterContasBancariasCadastradas()
//            .enqueue(object : Callback<RespostaPadraoApi> {
//                override fun onResponse(
//
//                    call: Call<RespostaPadraoApi>,
//                    response: Response<RespostaPadraoApi>
//                ) {
//                    if (response.code() == 200) {
//                        val dadosResposta = response.body()!!
//
//                        listaContasBancarias = ArrayList(
//                            Gson().fromJson(
//                                dadosResposta.dados.getAsJsonArray("contasBancarias"),
//                                Array<ContaBancaria>::class.java
//                            ).toList()
//                        )
//
//                        contaBancariaAdapter.atualizarListaContas(listaContasBancarias)
//                    } else {
//                        Toast.makeText(
//                            applicationContext,
//                            response.errorBody().toString(),
//                            Toast.LENGTH_LONG
//                        )
//                            .show()
//                    }
//
//                    indicadorCarregamento.dismiss()
//                }
//
//                override fun onFailure(call: Call<RespostaPadraoApi>, t: Throwable) {
//                    indicadorCarregamento.dismiss()
//                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
//                }
//
//            })
  }

  private fun exibirFormasPagamento() {
    if (listaDeFormasDePagamento.size > 0) {
      formaDePagamentoAdapter.atualizarLista(listaDeFormasDePagamento)
      container_sem_dados.visibility = View.GONE
      recyclerView.visibility = View.VISIBLE
      return
    }

    recyclerView.visibility = View.GONE
    container_sem_dados.visibility = View.VISIBLE
  }
}