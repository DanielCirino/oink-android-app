package tech.devrocks.oink.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_classificacao.view.*
import tech.devrocks.oink.R
import tech.devrocks.oink.model.Classificacao


class ClassificacaoAdapter(
  private val dataset: ArrayList<Classificacao>?,
  private val listener: (Classificacao) -> Unit
) :
    RecyclerView.Adapter<ClassificacaoAdapter.ClassificacaoViewHolder>() {

    class ClassificacaoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvNomeClassificacao = view.tv_nome_classificacao
        private val tvCodigoClassificacao = view.tv_codigo_classificacao

        fun configurarView(classificacao: Classificacao) {
            tvNomeClassificacao.text = classificacao.nome
            tvCodigoClassificacao.text = classificacao.codigo
        }
    }

    override fun onCreateViewHolder(
      parent: ViewGroup,
      viewType: Int
    ): ClassificacaoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_classificacao, parent, false)

        return ClassificacaoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClassificacaoViewHolder, position: Int) {
        holder.configurarView(dataset!![position])
        holder.itemView.setOnClickListener { listener(dataset[position]) }
    }

    override fun getItemCount(): Int = dataset?.size!!

    fun atualizarLista(listaDeClassificacao: ArrayList<Classificacao>) {
        this.dataset!!.clear()
        this.dataset.addAll(listaDeClassificacao)
        notifyDataSetChanged()
    }
}
