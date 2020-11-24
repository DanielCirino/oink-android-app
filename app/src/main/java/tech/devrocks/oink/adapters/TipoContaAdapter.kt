package tech.devrocks.oink.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_tipo_conta.view.*
import tech.devrocks.oink.R
import tech.devrocks.oink.model.TipoDeConta


class TipoContaAdapter(
  private val dataset: ArrayList<TipoDeConta>?,
  private val listener: (TipoDeConta) -> Unit
) :
    RecyclerView.Adapter<TipoContaAdapter.TipoDeContaViewHolder>() {

    class TipoDeContaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvNomeTipo = view.tv_nome_tipo_conta
        private val tvCodigoTipo = view.tv_codigo_tipo_conta

        fun configurarView(tipoConta: TipoDeConta) {
            tvNomeTipo.text = tipoConta.nome
            tvCodigoTipo.text = tipoConta.codigo
        }
    }

    override fun onCreateViewHolder(
      parent: ViewGroup,
      viewType: Int
    ): TipoDeContaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_tipo_conta, parent, false)

        return TipoDeContaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TipoDeContaViewHolder, position: Int) {
        holder.configurarView(dataset!![position])
        holder.itemView.setOnClickListener { listener(dataset[position]) }
    }

    override fun getItemCount(): Int = dataset?.size!!

    fun atualizarLista(listaDeTipos: ArrayList<TipoDeConta>) {
        this.dataset!!.clear()
        this.dataset.addAll(listaDeTipos)
        notifyDataSetChanged()
    }
}
