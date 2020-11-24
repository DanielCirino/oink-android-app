package tech.devrocks.oink.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_forma_pagamento.view.*
import tech.devrocks.oink.R
import tech.devrocks.oink.model.FormaDePagamento


class FormaPagamentoAdapter(
  private val dataset: ArrayList<FormaDePagamento>?,
  private val listener: (FormaDePagamento) -> Unit
) :
    RecyclerView.Adapter<FormaPagamentoAdapter.FormaPagametnoViewHolder>() {

    class FormaPagametnoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvNomeFormaPagamento = view.tv_nome_forma_pagamento
        private val tvCodigoFormaPaggamento = view.tv_codigo_forma_pagamento

        fun configurarView(formaDePagamento: FormaDePagamento) {
            tvNomeFormaPagamento.text = formaDePagamento.nome
            tvCodigoFormaPaggamento.text = formaDePagamento.codigo
        }
    }

    override fun onCreateViewHolder(
      parent: ViewGroup,
      viewType: Int
    ): FormaPagametnoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_forma_pagamento, parent, false)

        return FormaPagametnoViewHolder(view)
    }

    override fun onBindViewHolder(holder: FormaPagametnoViewHolder, position: Int) {
        holder.configurarView(dataset!![position])
        holder.itemView.setOnClickListener { listener(dataset[position]) }
    }

    override fun getItemCount(): Int = dataset?.size!!

    fun atualizarLista(listaDeFormasDePagamento: ArrayList<FormaDePagamento>) {
        this.dataset!!.clear()
        this.dataset.addAll(listaDeFormasDePagamento)
        notifyDataSetChanged()
    }
}
