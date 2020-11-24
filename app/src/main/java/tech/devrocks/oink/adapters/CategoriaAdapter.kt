package tech.devrocks.oink.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_categoria.view.*
import tech.devrocks.oink.R
import tech.devrocks.oink.model.Categoria


class CategoriaAdapter(
  private val dataset: ArrayList<Categoria>?,
  private val listener: (Categoria) -> Unit
) :
  RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder>() {

  class CategoriaViewHolder(categoriaView: View) : RecyclerView.ViewHolder(categoriaView) {
    private val tvNomeCategoria = categoriaView.tv_nome_categoria
    private val tvNomeGrupo = categoriaView.tv_nome_grupo_categoria
    private val ivIconeTipoRegistro = categoriaView.iv_icone_tipo_registro

    private val context = categoriaView.context

    fun configurarView(categoria: Categoria) {
      tvNomeCategoria.text = categoria.nome
      tvNomeGrupo.text = categoria.nomeGrupoCategoria

      if(categoria.codigoTipoRegistro == "E"){
       ivIconeTipoRegistro.setImageResource(R.drawable.ic_caret_para_cima)
        ivIconeTipoRegistro.setColorFilter(ContextCompat.getColor(context,R.color.green))
      }

      if(categoria.codigoTipoRegistro == "S"){
        ivIconeTipoRegistro.setImageResource(R.drawable.ic_caret_para_baixo)
        ivIconeTipoRegistro.setColorFilter(ContextCompat.getColor(context,R.color.red))
      }

    }
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): CategoriaViewHolder {
    val producaoView = LayoutInflater.from(parent.context)
      .inflate(R.layout.list_item_categoria, parent, false)

    return CategoriaViewHolder(producaoView)
  }

  override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
    holder.configurarView(dataset!!.get(position))
    holder.itemView.setOnClickListener { listener(dataset[position]) }
  }

  override fun getItemCount(): Int = dataset?.size!!
  fun atualizarLista(listaDeCategorias: ArrayList<Categoria>) {
    this.dataset!!.clear()
    this.dataset!!.addAll(listaDeCategorias)
    notifyDataSetChanged()
  }
}
