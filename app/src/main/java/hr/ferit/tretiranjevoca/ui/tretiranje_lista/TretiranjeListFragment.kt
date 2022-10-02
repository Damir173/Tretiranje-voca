package hr.ferit.tretiranjevoca.ui.tretiranje_lista

import android.app.*
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
import hr.ferit.tretiranjevoca.*
import hr.ferit.tretiranjevoca.databinding.FragmentTretiranjeListaBinding
import hr.ferit.tretiranjevoca.di.TretiranjeRepositoryFactory
import hr.ferit.tretiranjevoca.model.Tretiranje

import java.text.SimpleDateFormat
import java.util.*


class TretiranjeListFragment : Fragment(), OnTretiranjeEventListener {

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
            Toast.makeText(
                context,
                "Datum i vrijeme nisu \npostavljeni na automatsko!",
                Toast.LENGTH_SHORT
            ).show()

            Handler().postDelayed({
                android.os.Process.killProcess(android.os.Process.myPid())
            }, 5000)
        }

        binding = FragmentTretiranjeListaBinding.inflate(layoutInflater)
        setupRecyclerView()

        //region OnClickListeneri za buttone
        binding.fabAddNote.setOnClickListener { showCreateNewTretFragment() }
        binding.buttonPrijelaz.setOnClickListener { showSljiveFragment() }
        binding.buttonPrijelaz2.setOnClickListener { showVLFragment() }
        binding.buttonPrijelaz3.setOnClickListener { showJabukeFragment() }
        binding.buttonPrijelaz4.setOnClickListener { showKruskeFragment() }
        //endregion

        //region Provjere ima li postojećih tretiranja
        if (tretiranjeRepository.getLastJabuka().compareTo(0) == 0) {
            binding.textView17.text = "/"
        } else {
            binding.textView17.text =
                dateDisplayFormat.format(fromTimestamp(tretiranjeRepository.getLastJabuka()))
        }


        if (tretiranjeRepository.getLastSljive().compareTo(0) == 0) {
            binding.textView16.text = "/"
        } else {
            binding.textView16.text =
                dateDisplayFormat.format(fromTimestamp(tretiranjeRepository.getLastSljive()))
        }

        if (tretiranjeRepository.getLastVinovaLoza().compareTo(0) == 0) {
            binding.tvVinovaLozaLast.text = "/"
        } else {
            binding.tvVinovaLozaLast.text =
                dateDisplayFormat.format(fromTimestamp(tretiranjeRepository.getLastVinovaLoza()))
        }

        if (tretiranjeRepository.getLastKruska().compareTo(0) == 0) {
            binding.tvKruskaLast.text = "/"
        } else {
            binding.tvKruskaLast.text =
                dateDisplayFormat.format(fromTimestamp(tretiranjeRepository.getLastKruska()))
        }

        //endregion

        //region Animacije setup
        val fadeIn = AnimationUtils.loadAnimation(context, R.anim.slideright)
        val anim2 = AnimationUtils.loadAnimation(context, R.anim.slideleftg)

        anim2.duration = 1500
        fadeIn.duration = 1500

        binding.statistikatv.startAnimation(fadeIn)
        binding.rvTretiranje.startAnimation(anim2)
        binding.statistikatv2.startAnimation(anim2)

        //endregion

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

        binding.textView16.startAnimation(fadeIn)
        binding.textView17.startAnimation(fadeIn)
        binding.tvKruskaLast.startAnimation(fadeIn)
        binding.tvVinovaLozaLast.startAnimation(fadeIn)
        //endregion


        //region Ukupan broj tretiranja postavljanje
        binding.textView6.text = tretiranjeRepository.getSljive().toString()
        binding.textView9.text = tretiranjeRepository.getJabuke().toString()
        binding.tvKruskaUkupno.text = tretiranjeRepository.getKruske().toString()
        binding.tvVinovaLozaUkupno.text = tretiranjeRepository.getVinovaLoza().toString()
        //endregion

        //region Estetika
        binding.statistikatv3.text =
            "(" + tretiranjeRepository.getAktivneKarence(System.currentTimeMillis())
                .toString() + ")"
        //endregion

        return binding.root
    }



    private fun setupRecyclerView() {
        binding.rvTretiranje.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = TretiranjeAdapter()
        adapter.onTretiranjeSelectedListener = this
        binding.rvTretiranje.adapter = adapter


    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    private fun updateData() {
        adapter.setTretiranja(tretiranjeRepository.getAllTretiranja(System.currentTimeMillis()))

        //region Provjera ima li postojećih tretiranja (update)
        if (tretiranjeRepository.getLastJabuka().compareTo(0) == 0) {
            binding.textView17.text = "/"
        } else {
            binding.textView17.text =
                dateDisplayFormat.format(fromTimestamp(tretiranjeRepository.getLastJabuka()))
        }


        if (tretiranjeRepository.getLastSljive().compareTo(0) == 0) {
            binding.textView16.text = "/"
        } else {
            binding.textView16.text =
                dateDisplayFormat.format(fromTimestamp(tretiranjeRepository.getLastSljive()))
        }

        if (tretiranjeRepository.getLastVinovaLoza().compareTo(0) == 0) {
            binding.tvVinovaLozaLast.text = "/"
        } else {
            binding.tvVinovaLozaLast.text =
                dateDisplayFormat.format(fromTimestamp(tretiranjeRepository.getLastVinovaLoza()))
        }

        if (tretiranjeRepository.getLastKruska().compareTo(0) == 0) {
            binding.tvKruskaLast.text = "/"
        } else {
            binding.tvKruskaLast.text =
                dateDisplayFormat.format(fromTimestamp(tretiranjeRepository.getLastKruska()))
        }

        //endregion

        //region Postavljanje ukupnog broja tretiranja (update)
        binding.textView6.text = tretiranjeRepository.getSljive().toString()
        binding.textView9.text = tretiranjeRepository.getJabuke().toString()
        binding.tvKruskaUkupno.text = tretiranjeRepository.getKruske().toString()
        binding.tvVinovaLozaUkupno.text = tretiranjeRepository.getVinovaLoza().toString()
        //endregion
    }


    companion object {

        fun create(): Fragment {
            return TretiranjeListFragment()
        }
    }

    override fun onItemSelected(id: Long?) {
        val action =
            TretiranjeListFragmentDirections.actionTestiranjeListFragmentToTretiranjeDetaljiFragment(
                id ?: -1
            )
        findNavController().navigate(action)
    }

    override fun onItemPress(tretiranje: Tretiranje?): Boolean {

        val builder = AlertDialog.Builder(context)
        builder.setMessage(getString(R.string.brisanjeProvjera))
            .setCancelable(false)
            .setPositiveButton("Da") { dialog, id ->
                tretiranje?.let { it ->
                    tretiranjeRepository.delete(it)
                    adapter.setTretiranja(tretiranjeRepository.getAllTretiranja(System.currentTimeMillis()))
                    updateData();

                }
            }
            .setNegativeButton("Ne") { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()

        return true
    }

    //region showXXXFragments
    private fun showCreateNewTretFragment() {
        val action =
            TretiranjeListFragmentDirections.actionTretiranjeListFragmentToNovoTretiranjeFragment()
        findNavController().navigate(action)
    }

    private fun showSljiveFragment() {
        val action = TretiranjeListFragmentDirections.actionTretiranjeListFragmentToSljiveActivity()
        findNavController().navigate(action)
    }

    private fun showVLFragment() {
        val action = TretiranjeListFragmentDirections.actionTretiranjeListFragmentToVLActivity()
        findNavController().navigate(action)
    }

    private fun showJabukeFragment() {
        val action = TretiranjeListFragmentDirections.actionTretiranjeListFragmentToJabukeActivity()
        findNavController().navigate(action)
    }

    private fun showKruskeFragment() {
        val action = TretiranjeListFragmentDirections.actionTretiranjeListFragmentToKruskeActivity()
        findNavController().navigate(action)
    }
//endregion



    fun isTimeAutomatic(c: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Settings.Global.getInt(c.contentResolver, Settings.Global.AUTO_TIME, 0) === 1
        } else {
            Settings.System.getInt(c.contentResolver, Settings.System.AUTO_TIME, 0) == 1
        }
    }




}











