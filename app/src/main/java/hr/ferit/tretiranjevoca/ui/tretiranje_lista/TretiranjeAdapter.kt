package hr.ferit.tretiranjevoca.ui.tretiranje_lista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.TypeConverter
import odabirSlikeVoca
import hr.ferit.tretiranjevoca.R
import hr.ferit.tretiranjevoca.model.Tretiranje
import hr.ferit.tretiranjevoca.databinding.ItemTaskBinding
import java.text.SimpleDateFormat
import java.util.*

class TretiranjeAdapter : RecyclerView.Adapter<TaskViewHolder>() {

    private val tretiranjes = mutableListOf<Tretiranje>()

    var onTaskSelectedListener: OnTaskEventListener? = null

    fun setTasks(tretiranjes: List<Tretiranje>) {
        this.tretiranjes.clear()
        this.tretiranjes.addAll(tretiranjes)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tretiranjes[position]
        holder.bind(task)
        onTaskSelectedListener?.let { listener ->
            holder.itemView.setOnClickListener { listener.onTaskSelected(task.id) }
            holder.itemView.setOnLongClickListener { listener.onTaskLongPress(task) }
        }
    }

    override fun getItemCount(): Int = tretiranjes.count()
}

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val dateDisplayFormat = SimpleDateFormat("dd.MM.yyyy")

    fun bind(tretiranje: Tretiranje) {
        val binding = ItemTaskBinding.bind(itemView)
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