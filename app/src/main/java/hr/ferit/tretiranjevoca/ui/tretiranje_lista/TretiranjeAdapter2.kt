package hr.ferit.tretiranjevoca.ui.tretiranje_lista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.tretiranjevoca.R
import hr.ferit.tretiranjevoca.model.Tretiranje
import hr.ferit.tretiranjevoca.databinding.ItemDetBinding
import java.text.SimpleDateFormat

class TretiranjeAdapter2 : RecyclerView.Adapter<TretiranjeViewHolder2>() {

    private val tretiranjes = mutableListOf<Tretiranje>()

    var onTretiranjaSelectedListener: OnTretiranjeEventListener? = null

    fun setTretiranja(tretiranjes: List<Tretiranje>) {
        this.tretiranjes.clear()
        this.tretiranjes.addAll(tretiranjes)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TretiranjeViewHolder2 {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_det, parent, false)
        return TretiranjeViewHolder2(view)
    }

    override fun onBindViewHolder(holder: TretiranjeViewHolder2, position: Int) {
        val tretiranje = tretiranjes[position]
        holder.bind(tretiranje)
        onTretiranjaSelectedListener?.let { listener ->
            holder.itemView.setOnClickListener { listener.onItemSelected(tretiranje.id) }
            holder.itemView.setOnLongClickListener { listener.onItemPress(tretiranje) }
        }
    }

    override fun getItemCount(): Int = tretiranjes.count()
}

class TretiranjeViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val dateDisplayFormat = SimpleDateFormat("dd.MM.yyyy")

    fun bind(tretiranje: Tretiranje) {
        val binding = ItemDetBinding.bind(itemView)
        binding.tvItemDatum.text = dateDisplayFormat.format(tretiranje.datumtretiranja)
        binding.tvItemKarenca.text = tretiranje.karenca.toString()
        binding.tvItemKolicina.text = tretiranje.kolicina.toString()
        binding.tvItemTip.text = tretiranje.vrsta.toString()
        binding.tvItemVrsta.text = tretiranje.tipTretiranja.toString()

    }
}

