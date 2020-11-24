package tech.devrocks.oink.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_contas_bancarias.container_sem_dados
import kotlinx.android.synthetic.main.activity_registro_financeiro.*
import tech.devrocks.oink.R
import tech.devrocks.oink.adapters.CategoriaAdapter
import tech.devrocks.oink.model.Categoria

class RegistroFinanceiroActivity : AppCompatActivity() {
  private lateinit var recyclerView: RecyclerView
  private lateinit var categoriaAdapter: CategoriaAdapter
  private lateinit var viewManager: RecyclerView.LayoutManager
  private var listaDeCategorias: ArrayList<Categoria> = ArrayList()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_registro_financeiro)

    configurarControles()
    abrirFormularioRegistroFinanceiro()
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
        obterCategoriasCadastradas()
      }
      R.id.acao_menu_calculo_incluir -> {
        abrirFormularioRegistroFinanceiro()
      }

    }
    return super.onOptionsItemSelected(item)
  }

  private fun configurarControles() {
//        indicadorCarregamento = DialogFactory(this).dialogLoading
    configurarToolbar()
    configurarRecyclerview()

    fab_novo_registro_financeiro.setOnClickListener {
      abrirFormularioRegistroFinanceiro()
    }
  }

  private fun configurarToolbar() {
    if (Build.VERSION.SDK_INT >= 21) window.statusBarColor =
      ContextCompat.getColor(this, R.color.background)

    setSupportActionBar(toolbar_registro_financeiro)

    val actionBar = supportActionBar

    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(false)
      actionBar.setHomeButtonEnabled(true)
      actionBar.setHomeAsUpIndicator(R.drawable.ic_fechar)
    }


  }

  private fun configurarRecyclerview() {


    viewManager = LinearLayoutManager(this)
    categoriaAdapter = CategoriaAdapter(listaDeCategorias) {
      Toast.makeText(this, it.nome, Toast.LENGTH_LONG).show()
    }

    recyclerView = rv_lista_registro_financeiro.apply {
      setHasFixedSize(true)
      layoutManager = viewManager
      adapter = categoriaAdapter
    }

    obterCategoriasCadastradas()


  }

  private fun abrirFormularioRegistroFinanceiro() {
    val formularioRegistroFinanceiro = FormularioRegistroFinanceiroFragment()

    supportFragmentManager.setFragmentResultListener(formularioRegistroFinanceiro.CHAVE_REQUISICAO, this,
      { _, bundle ->

        val categoria = bundle.getParcelable<Categoria>(formularioRegistroFinanceiro.CHAVE_RESPOSTA)
        if (categoria != null) {
          processarDadosCategoria(categoria)
        }

      })

    formularioRegistroFinanceiro.exibir(supportFragmentManager)

  }

  private fun processarDadosCategoria(categoria: Categoria) {
    Toast.makeText(this,categoria.nome, Toast.LENGTH_LONG).show()
  }

  private fun obterCategoriasCadastradas() {

//    listaDeCategorias = CategoriaMock().obterListaDeCategorias()
    exibirRegistrosFinanceiros()

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

  private fun exibirRegistrosFinanceiros() {
    if(listaDeCategorias.size>0){
      categoriaAdapter.atualizarLista(listaDeCategorias)
      container_sem_dados.visibility = View.GONE
      recyclerView.visibility = View.VISIBLE
      return
    }

    recyclerView.visibility = View.GONE
    container_sem_dados.visibility = View.VISIBLE
  }
}