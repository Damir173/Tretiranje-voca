package hr.ferit.tretiranjevoca.ui.tretiranje_lista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.TypeConverter
import odabirSlikeVoca
import hr.ferit.tretiranjevoca.R
import hr.ferit.tretiranjevoca.model.Tretiranje
import hr.ferit.tretiranjevoca.databinding.ItemTretiranjeBinding
import java.text.SimpleDateFormat
import java.util.*

class TretiranjeAdapter : RecyclerView.Adapter<TretiranjeViewHolder>() {

    private val tretiranjes = mutableListOf<Tretiranje>()

    var onTretiranjeSelectedListener: OnTretiranjeEventListener? = null

    fun setTretiranja(tretiranjes: List<Tretiranje>) {
        this.tretiranjes.clear()
        this.tretiranjes.addAll(tretiranjes)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TretiranjeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tretiranje, parent, false)
        return TretiranjeViewHolder(view)
    }

    override fun onBindViewHolder(holder: TretiranjeViewHolder, position: Int) {
        val tret = tretiranjes[position]
        holder.bind(tret)
        onTretiranjeSelectedListener?.let { listener ->
            holder.itemView.setOnClickListener { listener.onItemSelected(tret.id) }
            holder.itemView.setOnLongClickListener { listener.onItemPress(tret) }
        }
    }

    override fun getItemCount(): Int = tretiranjes.count()
}

class TretiranjeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val dateDisplayFormat = SimpleDateFormat("dd.MM.yyyy")

    fun bind(tretiranje: Tretiranje) {
        val binding = ItemTretiranjeBinding.bind(itemView)
      //  binding.itemTaskTitle.text = task.napomena
       // binding.itemTaskContent.text = task.karenca.toString()
        //binding.itemTaskContent2.text = dateDisplayFormat.format(task.datumtretiranja)
        binding.imageView7.setBackgroundResource(
            binding.imageView7.context.resources.odabirSlikeVoca(tretiranje.odabirVoca)
        )

        binding.textView20.text = dateDisplayFormat.format(fromTimestamp(toTimestamp(tretiranje.datumtretiranja)?.plus((tretiranje.karenca*86400000))))

        //val a = toTimestamp(task.datumtretiranja)?.plus((task.karenca*86400000))
       // binding.itemTaskContent3.text = dateDisplayFormat.format(fromTimestamp(a))



    }
}

@TypeConverter
fun fromTimestamp(value: Long?): Date? {
    return value?.let { Date(it) }
}

@TypeConverter
fun toTimestamp(date: Date?): Long? {
    return date?.time
}