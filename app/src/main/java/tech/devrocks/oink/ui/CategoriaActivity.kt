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
import kotlinx.android.synthetic.main.activity_categoria.*
import kotlinx.android.synthetic.main.activity_contas_bancarias.*
import kotlinx.android.synthetic.main.activity_contas_bancarias.container_sem_dados
import tech.devrocks.oink.R
import tech.devrocks.oink.adapters.CategoriaAdapter
import tech.devrocks.oink.mock.CategoriaMock
import tech.devrocks.oink.model.Categoria

class CategoriaActivity : AppCompatActivity() {
  private lateinit var recyclerView: RecyclerView
  private lateinit var categoriaAdapter: CategoriaAdapter
  private lateinit var viewManager: RecyclerView.LayoutManager
  private var listaDeCategorias: ArrayList<Categoria> = ArrayList()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_categoria)

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
        obterCategoriasCadastradas()
      }
      R.id.acao_menu_calculo_incluir -> {
        abrirFormularioCategoria()
      }

    }
    return super.onOptionsItemSelected(item)
  }

  private fun configurarControles() {
//        indicadorCarregamento = DialogFactory(this).dialogLoading
    configurarToolbar()
    configurarRecyclerview()

    fab_nova_categoria.setOnClickListener {
      abrirFormularioCategoria()
    }
  }

  private fun configurarToolbar() {
    if (Build.VERSION.SDK_INT >= 21) window.statusBarColor =
      ContextCompat.getColor(this, R.color.background)

    setSupportActionBar(toolbar_contas_bancarias)

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

    recyclerView = rv_lista_categorias.apply {
      setHasFixedSize(true)
      layoutManager = viewManager
      adapter = categoriaAdapter
    }

    obterCategoriasCadastradas()


  }

  private fun abrirFormularioCategoria() {
    val formularioCategoria = FormularioCategoriaFragment()

    supportFragmentManager.setFragmentResultListener(formularioCategoria.CHAVE_REQUISICAO, this,
      { _, bundle ->

        val categoria = bundle.getParcelable<Categoria>(formularioCategoria.CHAVE_RESPOSTA)
        if (categoria != null) {
          processarDadosCategoria(categoria)
        }

      })

    formularioCategoria.exibir(supportFragmentManager)

  }

  private fun processarDadosCategoria(categoria: Categoria) {
    Toast.makeText(this,categoria.nome, Toast.LENGTH_LONG).show()
  }

  private fun obterCategoriasCadastradas() {

    listaDeCategorias = CategoriaMock().obterListaDeCategorias()
    exibirCategorias()

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

  private fun exibirCategorias() {
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