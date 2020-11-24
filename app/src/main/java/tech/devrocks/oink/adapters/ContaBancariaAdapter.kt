package tech.devrocks.oink.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_conta_bancaria.view.*
import tech.devrocks.oink.R
import tech.devrocks.oink.model.Conta


class ContaBancariaAdapter(
  private val dataset: ArrayList<Conta>?,
  private val listener: (Conta) -> Unit
) :
  RecyclerView.Adapter<ContaBancariaAdapter.ContaBancariaViewHolder>() {

  class ContaBancariaViewHolder(contaView: View) : RecyclerView.ViewHolder(contaView) {
    private val tvNomeConta = contaView.tv_nome_conta_bancaria
    private val tvBanco = contaView.tv_dados_bancarios_banco
    private val tvAgencia = contaView.tv_dados_bancarios_agencia
    private val tvConta = contaView.tv_dados_bancarios_conta
    private val tvDocumento = contaView.tv_documento_conta_bancaria


    private val context = contaView.context

    fun configurarView(contaBancaria: Conta) {
      tvNomeConta.text = contaBancaria.nomeConta
      tvBanco.text = "Banco. ${contaBancaria.codigoBanco}"
      tvDocumento.text = contaBancaria.numeroDocumento

      tvAgencia.text = "Agência: ${contaBancaria.numeroAgencia}"
      tvDocumento.text = contaBancaria.numeroDocumento

      tvConta.text = "Conta. ${contaBancaria.numeroConta}"
      tvDocumento.text = contaBancaria.numeroDocumento


    }
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ContaBancariaViewHolder {
    val producaoView = LayoutInflater.from(parent.context)
      .inflate(R.layout.list_item_conta_bancaria, parent, false)

    return ContaBancariaViewHolder(producaoView)
  }

  override fun onBindViewHolder(holder: ContaBancariaViewHolder, position: Int) {
    holder.configurarView(dataset!!.get(position))
    holder.itemView.setOnClickListener { listener(dataset!!.get(position)) }
  }

  override fun getItemCount(): Int = dataset?.size!!
  fun atualizarListaContas(litaContas: ArrayList<Conta>) {
    this.dataset!!.clear()
    this.dataset!!.addAll(litaContas)
    notifyDataSetChanged()
  }
}
