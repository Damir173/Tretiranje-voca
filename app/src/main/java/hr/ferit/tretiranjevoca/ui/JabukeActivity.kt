package hr.ferit.tretiranjevoca.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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
    }

    companion object {
        val Tag = "TasksList"

        fun create(): Fragment {
            return JabukeActivity()
        }
    }

    override fun onItemSelected(id: Long?) {

        val action = VLActivityDirections.actionVLActivityToTretiranjeDetaljiFragment(id ?: -1)
        findNavController().navigate(action)
    }

    override fun onItemPress(tretiranje: Tretiranje?): Boolean {
        tretiranje?.let { it ->
            tretiranjeRepository.delete(it)
        }
        return true
    }


}