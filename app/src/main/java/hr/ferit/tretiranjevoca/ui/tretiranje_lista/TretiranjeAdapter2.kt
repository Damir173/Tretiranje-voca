package hr.ferit.tretiranjevoca.ui.tretiranje_lista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import odabirSlikeVoca
import hr.ferit.tretiranjevoca.R
import hr.ferit.tretiranjevoca.model.Tretiranje
import hr.ferit.tretiranjevoca.databinding.ItemDetBinding
import java.text.SimpleDateFormat
import java.util.*

class TretiranjeAdapter2 : RecyclerView.Adapter<TaskViewHolder2>() {

    private val tretiranjes = mutableListOf<Tretiranje>()

    var onTaskSelectedListener: OnTaskEventListener? = null

    fun setTasks(tretiranjes: List<Tretiranje>) {
        this.tretiranjes.clear()
        this.tretiranjes.addAll(tretiranjes)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder2 {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_det, parent, false)
        return TaskViewHolder2(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder2, position: Int) {
        val task = tretiranjes[position]
        holder.bind(task)
        onTaskSelectedListener?.let { listener ->
            holder.itemView.setOnClickListener { listener.onTaskSelected(task.id) }
            holder.itemView.setOnLongClickListener { listener.onTaskLongPress(task) }
        }
    }

    override fun getItemCount(): Int = tretiranjes.count()
}

class TaskViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val dateDisplayFormat = SimpleDateFormat("dd.MM.yyyy")

    fun bind(tretiranje: Tretiranje) {
        val binding = ItemDetBinding.bind(itemView)
        binding.tvItemDatum.text = dateDisplayFormat.format(tretiranje.datumtretiranja)
        binding.tvItemKarenca.text = tretiranje.karenca.toString()
        binding.tvItemKolicina.text = tretiranje.kolicina.toString()
        binding.tvItemTip.text = tretiranje.vrsta.toString()
        binding.tvItemVrsta.text = tretiranje.vrsta.toString()

    }
}

