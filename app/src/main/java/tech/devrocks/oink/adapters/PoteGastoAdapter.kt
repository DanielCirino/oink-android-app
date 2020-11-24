package tech.devrocks.oink.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_pote_gasto.view.*
import tech.devrocks.oink.R
import tech.devrocks.oink.model.PoteDeGasto


class PoteGastoAdapter(
    private val dataset: ArrayList<PoteDeGasto>?,
    private val listener: (PoteDeGasto) -> Unit
) :
  RecyclerView.Adapter<PoteGastoAdapter.PoteGastoViewHolder>() {

  class PoteGastoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvNomePoteGasto = view.tv_nome_pote
    private val tvDescricaoPoteGasto = view.tv_descricao_pote
    private val tvCapacidadePoteGasto = view.tv_capacidade_pote

    fun configurarView(poteDeGasto: PoteDeGasto) {
      tvNomePoteGasto.text = poteDeGasto.nome
      tvDescricaoPoteGasto.text = poteDeGasto.descricao
      tvCapacidadePoteGasto.text = poteDeGasto.capacidade.toString()
    }
  }

  override fun onCreateViewHolder(
      parent: ViewGroup,
      viewType: Int
  ): PoteGastoViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.list_item_pote_gasto, parent, false)

    return PoteGastoViewHolder(view)
  }

  override fun onBindViewHolder(holder: PoteGastoViewHolder, position: Int) {
    holder.configurarView(dataset!![position])
    holder.itemView.setOnClickListener { listener(dataset[position]) }
  }

  override fun getItemCount(): Int = dataset?.size!!

  fun atualizarLista(listaPotesGasto: ArrayList<PoteDeGasto>) {
    this.dataset!!.clear()
    this.dataset.addAll(listaPotesGasto)
    notifyDataSetChanged()
  }
}
