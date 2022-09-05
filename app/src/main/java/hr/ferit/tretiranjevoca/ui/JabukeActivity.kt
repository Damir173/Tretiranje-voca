package hr.ferit.tretiranjevoca.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hr.ferit.tretiranjevoca.R
import hr.ferit.tretiranjevoca.databinding.FragmentJabukeBinding
import hr.ferit.tretiranjevoca.databinding.FragmentVlBinding

import hr.ferit.tretiranjevoca.di.TretiranjeRepositoryFactory
import hr.ferit.tretiranjevoca.model.Tretiranje
import hr.ferit.tretiranjevoca.ui.tretiranje_lista.*

class JabukeActivity: Fragment(), OnTretiranjeEventListener {




    private lateinit var binding: FragmentJabukeBinding
    private lateinit var adapter: TretiranjeAdapter2
    private val tretiranjeRepository = TretiranjeRepositoryFactory.tretiranjeRepository

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentJabukeBinding.inflate(layoutInflater)


        binding.tvUkupno.text = tretiranjeRepository.getJabuke().toString()

        if(tretiranjeRepository.getAktivneJabuke(System.currentTimeMillis()).compareTo(0) == 0){
            binding.tvAktivno.text = "Nema"
        }
        else {
            binding.tvAktivno.text = tretiranjeRepository.getAktivneJabuke(System.currentTimeMillis()).toString()
        }
        binding.tvFungicid.text = tretiranjeRepository.getJabukeFungicid().toString()
        binding.tvHerbicid.text = tretiranjeRepository.getJabukeHerbicid().toString()
        binding.tvInsekticid.text = tretiranjeRepository.getJabukeInsekticid().toString()


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
        adapter.setTretiranja(tretiranjeRepository.getAllJabuke())
        binding.tvUkupno.text = tretiranjeRepository.getJabuke().toString()

        if(tretiranjeRepository.getAktivneJabuke(System.currentTimeMillis()).compareTo(0) == 0){
            binding.tvAktivno.text = "Nema"
        }
        else {
            binding.tvAktivno.text = tretiranjeRepository.getAktivneJabuke(System.currentTimeMillis()).toString()
        }

        binding.tvFungicid.text = tretiranjeRepository.getJabukeFungicid().toString()
        binding.tvHerbicid.text = tretiranjeRepository.getJabukeHerbicid().toString()
        binding.tvInsekticid.text = tretiranjeRepository.getJabukeInsekticid().toString()
    }

    companion object {
        fun create(): Fragment {
            return JabukeActivity()
        }
    }

    override fun onItemSelected(id: Long?) {

        val action = JabukeActivityDirections.actionJabukeActivityToTretiranjeDetaljiFragment(id ?: -1)
        findNavController().navigate(action)
    }

    override fun onItemPress(tretiranje: Tretiranje?): Boolean {

        val builder = AlertDialog.Builder(context)
        builder.setMessage(R.string.brisanjeProvjera)
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