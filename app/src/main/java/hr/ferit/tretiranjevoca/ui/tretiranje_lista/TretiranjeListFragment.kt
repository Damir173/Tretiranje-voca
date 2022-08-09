package hr.ferit.tretiranjevoca.ui.tretiranje_lista

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hr.ferit.tretiranjevoca.R
import hr.ferit.tretiranjevoca.databinding.FragmentTretiranjeListaBinding
import hr.ferit.tretiranjevoca.di.TretiranjeRepositoryFactory
import hr.ferit.tretiranjevoca.model.Tretiranje
import java.text.SimpleDateFormat
import java.util.*


class TretiranjeListFragment : Fragment(), OnTaskEventListener {

    private lateinit var binding: FragmentTretiranjeListaBinding
    private val dateDisplayFormat = SimpleDateFormat("dd.MM.YYYY")
    private lateinit var adapter: TretiranjeAdapter
    private val tretiranjeRepository = TretiranjeRepositoryFactory.tretiranjeRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        if (!(isTimeAutomatic(context!!))) {
            Toast.makeText(context,"Datum i vrijeme nisu \npostavljeni na automatsko!", Toast.LENGTH_SHORT).show()

            Handler().postDelayed({
                android.os.Process.killProcess(android.os.Process.myPid())
            }, 5000)
        }

        binding = FragmentTretiranjeListaBinding.inflate(layoutInflater)
        setupRecyclerView()
        binding.fabAddNote.setOnClickListener { showCreateNewTaskFragment() }
       binding.buttonPrijelaz.setOnClickListener{showNoviFragment()}
        binding.textView.text = tretiranjeRepository.getNumber().toString()
    //    binding.view.isClickable = false


        if(tretiranjeRepository.getLastJabuka().compareTo(0) == 0) {
            binding.textView17.text = "/"
        }
        else {
            binding.textView17.text = dateDisplayFormat.format(fromTimestamp(tretiranjeRepository.getLastJabuka()))
        }

        binding.textView8.text = dateDisplayFormat.format(fromTimestamp(tretiranjeRepository.getLastSljive()))
        binding.tvVinovaLozaLast.text = dateDisplayFormat.format(fromTimestamp(tretiranjeRepository.getLastVinovaLoza()))
        binding.tvKruskaLast.text = dateDisplayFormat.format(fromTimestamp(tretiranjeRepository.getLastKruska()))


        val fadeIn = AnimationUtils.loadAnimation(context, R.anim.slideright)
        val anim2 = AnimationUtils.loadAnimation(context, R.anim.slideleftg)
       // fadeIn.duration = 5000
       // anim2.duration = 3000
        anim2.duration = 1500
//        fadeIn.startOffset = 300
        fadeIn.duration = 1500

        binding.statistikatv.startAnimation(fadeIn)
     //   binding.view.startAnimation(anim2)
        binding.rvTretiranje.startAnimation(anim2)
        binding.statistikatv2.startAnimation(anim2)

        //region Animacije za statistiku
        binding.imageView2.startAnimation(fadeIn)
        binding.imageView3.startAnimation(fadeIn)
        binding.imageView4.startAnimation(fadeIn)
        binding.imageView5.startAnimation(fadeIn)
        binding.view2.startAnimation(fadeIn)
        binding.textView2.startAnimation(fadeIn)
        binding.textView3.startAnimation(fadeIn)
        binding.textView4.startAnimation(fadeIn)
        binding.textView5.startAnimation(fadeIn)
        binding.textView6.startAnimation(fadeIn)
        binding.textView7.startAnimation(fadeIn)
        binding.textView8.startAnimation(fadeIn)
        binding.textView9.startAnimation(fadeIn)
        binding.tvKruskaUkupno.startAnimation(fadeIn)
        binding.tvVinovaLozaUkupno.startAnimation(fadeIn)
        binding.textView13.startAnimation(fadeIn)
        binding.textView14.startAnimation(fadeIn)
        binding.textView15.startAnimation(fadeIn)
        binding.textView17.startAnimation(fadeIn)
        binding.tvKruskaLast.startAnimation(fadeIn)
        binding.tvVinovaLozaLast.startAnimation(fadeIn)
        binding.statistikatv3.startAnimation(anim2)
        //endregion

        binding.textView6.text = tretiranjeRepository.getSljive().toString()
        binding.textView9.text = tretiranjeRepository.getJabuke().toString()
        binding.tvKruskaUkupno.text = tretiranjeRepository.getKruske().toString()
        binding.tvVinovaLozaUkupno.text = tretiranjeRepository.getVinovaLoza().toString()


        binding.statistikatv3.text = "(" +  tretiranjeRepository.getAktivneKarence(System.currentTimeMillis()).toString() + ")"


        return binding.root
    }

    private fun setupRecyclerView() {
        binding.rvTretiranje.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = TretiranjeAdapter()
        adapter.onTaskSelectedListener = this
        binding.rvTretiranje.adapter = adapter


    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    private fun updateData() {
        adapter.setTasks(tretiranjeRepository.getAllTasks(System.currentTimeMillis()))
    }



    companion object {
        val Tag = "TasksList"

        fun create(): Fragment {
            return TretiranjeListFragment()
        }
    }

    override fun onTaskSelected(id: Long?) {
        val action =
            TretiranjeListFragmentDirections.actionTestiranjeListFragmentToTretiranjeDetaljiFragment(id ?: -1)
        findNavController().navigate(action)
    }

    override fun onTaskLongPress(tretiranje: Tretiranje?): Boolean {
        tretiranje?.let { it ->
            tretiranjeRepository.delete(it)
            adapter.setTasks(tretiranjeRepository.getAllTasks(System.currentTimeMillis()))
        }
        return true
    }

    private fun showCreateNewTaskFragment() {
        val action = TretiranjeListFragmentDirections.actionTretiranjeListFragmentToNovoTretiranjeFragment()
        findNavController().navigate(action)
    }

    private fun showNoviFragment() {
        val action = TretiranjeListFragmentDirections.actionTretiranjeListFragmentToNoviActivity()
        findNavController().navigate(action)
    }

    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }


    fun isTimeAutomatic(c: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Settings.Global.getInt(c.contentResolver, Settings.Global.AUTO_TIME, 0) === 1
        } else {
            Settings.System.getInt(c.contentResolver, Settings.System.AUTO_TIME, 0) == 1
        }
    }
}

