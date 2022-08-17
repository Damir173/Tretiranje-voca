package hr.ferit.tretiranjevoca.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hr.ferit.tretiranjevoca.databinding.FragmentVlBinding

import hr.ferit.tretiranjevoca.di.TretiranjeRepositoryFactory
import hr.ferit.tretiranjevoca.model.Tretiranje
import hr.ferit.tretiranjevoca.ui.tretiranje_lista.*

class VLActivity: Fragment(), OnTretiranjeEventListener {




    private lateinit var binding: FragmentVlBinding
    private lateinit var adapter: TretiranjeAdapter2
    private val tretiranjeRepository = TretiranjeRepositoryFactory.tretiranjeRepository

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentVlBinding.inflate(layoutInflater)
        binding.tvUkupno.text = tretiranjeRepository.getVinovaLoza().toString()

        if(tretiranjeRepository.getAktivneVinova(System.currentTimeMillis()).compareTo(0) == 0){
            binding.tvAktivno.text = "Nema"
        }
        else {
            binding.tvAktivno.text = tretiranjeRepository.getAktivneVinova(System.currentTimeMillis()).toString()
        }
        binding.tvFungicid.text = tretiranjeRepository.getVinovaFungicid().toString()
        binding.tvHerbicid.text = tretiranjeRepository.getVinovaHerbicid().toString()
        binding.tvInsekticid.text = tretiranjeRepository.getVinovaInsekticid().toString()


        setupRecyclerView()
        updateData()
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.rvVinova.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = TretiranjeAdapter2()
        adapter.onTretiranjaSelectedListener = this
        binding.rvVinova.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    private fun updateData() {
        adapter.setTretiranja(tretiranjeRepository.getAllVinova())
        binding.tvUkupno.text = tretiranjeRepository.getVinovaLoza().toString()

        if(tretiranjeRepository.getAktivneVinova(System.currentTimeMillis()).compareTo(0) == 0){
            binding.tvAktivno.text = "Nema"
        }
        else {
            binding.tvAktivno.text = tretiranjeRepository.getAktivneVinova(System.currentTimeMillis()).toString()
        }
        binding.tvFungicid.text = tretiranjeRepository.getVinovaFungicid().toString()
        binding.tvHerbicid.text = tretiranjeRepository.getVinovaHerbicid().toString()
        binding.tvInsekticid.text = tretiranjeRepository.getVinovaInsekticid().toString()
    }

    companion object {
        val Tag = "TasksList"

        fun create(): Fragment {
            return VLActivity()
        }
    }

    override fun onItemSelected(id: Long?) {

        val action = VLActivityDirections.actionVLActivityToTretiranjeDetaljiFragment(id ?: -1)
        findNavController().navigate(action)
    }

    override fun onItemPress(tretiranje: Tretiranje?): Boolean {

        val builder = AlertDialog.Builder(context)
        builder.setMessage("Jeste li sigurni da želite obrisati tretiranje?")
            .setCancelable(false)
            .setPositiveButton("Da") { dialog, id ->
                tretiranje?.let { it ->
                    tretiranjeRepository.delete(it)
                    adapter.setTretiranja(tretiranjeRepository.getAllTretiranja(System.currentTimeMillis()))
                    updateData()
                }
            }
            .setNegativeButton("Ne") { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()


        return true
    }

}