package tech.devrocks.oink.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_cabecalho_lista.view.*
import kotlinx.android.synthetic.main.list_item_grupo_categoria.view.*
import tech.devrocks.oink.R
import tech.devrocks.oink.model.GrupoDeCategoria

class GrupoCagegoriaAdapter(
  private val dataset: MutableList<Any>?,
  private val listener: (GrupoDeCategoria) -> Unit
) :
  RecyclerView.Adapter<GrupoCagegoriaAdapter.BaseViewHolder<*>>() {
  companion object {
    private val VIEW_TYPE_CLASSIFICAO = 1
    private val VIEW_TYPE_GRUPO = 2
  }

  abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun configurarView(item: T)
  }

  inner class GrupoCategoriaViewHolder(view: View) : BaseViewHolder<GrupoDeCategoria>(view) {
    private val tvNomeGrupo = view.tv_nome_grupo_categoria
    private val tvCodigoClassificacao = view.tv_codigo_classificacao

    override fun configurarView(grupo: GrupoDeCategoria) {
      tvNomeGrupo.text = grupo.nome
      tvCodigoClassificacao.text = grupo.codigoClassificacao
    }
  }

  inner class CabecalhoViewHolder(view: View) : BaseViewHolder<String>(view) {
    private val tvTituloCabecalho = view.tv_titulo_cabecalho

    override fun configurarView(texto: String) {
      tvTituloCabecalho.text = texto

    }
  }

  override fun getItemViewType(position: Int): Int {
    return if (dataset!![position] is GrupoDeCategoria) {
      VIEW_TYPE_GRUPO
    } else {
      VIEW_TYPE_CLASSIFICAO
    }


  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): BaseViewHolder<*> {

    return when (viewType) {
      VIEW_TYPE_CLASSIFICAO -> {

        val view = LayoutInflater.from(parent.context)
          .inflate(R.layout.list_item_cabecalho_lista, parent, false)

        CabecalhoViewHolder(view)

      }
      VIEW_TYPE_GRUPO -> {

        val view = LayoutInflater.from(parent.context)
          .inflate(R.layout.list_item_grupo_categoria, parent, false)
        return GrupoCategoriaViewHolder(view)

      }

      else -> throw IllegalArgumentException("View type inválido!")
    }


  }

  override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
    when(holder){
     is GrupoCategoriaViewHolder->{
       holder.configurarView(dataset!![position] as GrupoDeCategoria)
       holder.itemView.setOnClickListener { listener(dataset[position] as GrupoDeCategoria) }
     }
      is CabecalhoViewHolder->{
        holder.configurarView(dataset!![position] as String)
      }
    }


  }

  override fun getItemCount(): Int = dataset?.size!!

  fun atualizarLista(listaDeGrupos: List<Any>) {
    this.dataset!!.clear()
    this.dataset.addAll(listaDeGrupos)
    notifyDataSetChanged()
  }
}
