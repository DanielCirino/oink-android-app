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
import kotlinx.android.synthetic.main.activity_contas_bancarias.*
import tech.devrocks.oink.R
import tech.devrocks.oink.adapters.CategoriaAdapter
import tech.devrocks.oink.adapters.ContaBancariaAdapter
import tech.devrocks.oink.mock.ContaMock
import tech.devrocks.oink.model.Conta

class ContasBancariasActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var contaAdapter: ContaBancariaAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var listaDeContas: ArrayList<Conta> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contas_bancarias)

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
                obterContasCadastradas()
            }
            R.id.acao_menu_calculo_incluir -> {
                abrirFormularioConta()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun configurarControles() {
//        indicadorCarregamento = DialogFactory(this).dialogLoading
        configurarToolbar()
        configurarRecyclerview()

        fab_nova_conta_bancaria.setOnClickListener {
            abrirFormularioConta()
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
        contaAdapter = ContaBancariaAdapter(listaDeContas) {
            Toast.makeText(this, it.nomeConta, Toast.LENGTH_LONG).show()
        }

        recyclerView = rv_lista_contas_bancarias.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = contaAdapter
        }

        obterContasCadastradas()


    }

    private fun abrirFormularioConta() {
        val formularioConta = FormularioContaFragment()

        supportFragmentManager.setFragmentResultListener(formularioConta.CHAVE_REQUISICAO, this,
            { _, bundle ->

                val conta = bundle.getParcelable<Conta>(formularioConta.CHAVE_RESPOSTA)
                if (conta != null) {
                    processarDadosConta(conta)
                }

            })

        formularioConta.exibir(supportFragmentManager)

    }

    private fun processarDadosConta(conta: Conta) {
        Toast.makeText(this,conta.nomeConta,Toast.LENGTH_LONG).show()
    }

    private fun obterContasCadastradas() {

        listaDeContas = ContaMock().obterListaContas()
        exibirContas()

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

    private fun exibirContas() {
        if(listaDeContas.size>0){
            contaAdapter.atualizarListaContas(listaDeContas)
            container_sem_dados.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            return
        }

        recyclerView.visibility = View.GONE
        container_sem_dados.visibility = View.VISIBLE
    }
}