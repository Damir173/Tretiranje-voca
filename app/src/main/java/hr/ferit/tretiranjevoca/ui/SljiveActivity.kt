package hr.ferit.tretiranjevoca.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hr.ferit.tretiranjevoca.databinding.FragmentSljiveBinding

import hr.ferit.tretiranjevoca.di.TretiranjeRepositoryFactory
import hr.ferit.tretiranjevoca.model.Tretiranje
import hr.ferit.tretiranjevoca.ui.tretiranje_lista.*

class SljiveActivity: Fragment(), OnTretiranjeEventListener {




    private lateinit var binding: FragmentSljiveBinding
    private lateinit var adapter: TretiranjeAdapter2
    private val tretiranjeRepository = TretiranjeRepositoryFactory.tretiranjeRepository

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSljiveBinding.inflate(layoutInflater)
        binding.tvUkupnoSljive.text = tretiranjeRepository.getSljive().toString()

        if(tretiranjeRepository.getAktivneSljive(System.currentTimeMillis()).compareTo(0) == 0){
            binding.tvAktivneSljive.text = "Nema"
        }
        else {
            binding.tvAktivneSljive.text = tretiranjeRepository.getAktivneSljive(System.currentTimeMillis()).toString()
        }
        binding.tvFungicidSljive.text = tretiranjeRepository.getSljiveFungicid().toString()
        binding.tvHerbicidSljive.text = tretiranjeRepository.getSljiveHerbicid().toString()
        binding.tvInsekticidSljive.text = tretiranjeRepository.getSljiveInsekticid().toString()

        setupRecyclerView()
        updateData()
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.rvSljive.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = TretiranjeAdapter2()
        adapter.onTretiranjaSelectedListener = this
        binding.rvSljive.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    private fun updateData() {
        adapter.setTretiranja(tretiranjeRepository.getAllSljive())
    }

    companion object {
        val Tag = "TasksList"

        fun create(): Fragment {
            return SljiveActivity()
        }
    }

    override fun onItemSelected(id: Long?) {

        val action = SljiveActivityDirections.actionSljiveActivityToTretiranjeDetails(id ?: -1)
        findNavController().navigate(action)
    }

    override fun onItemPress(tretiranje: Tretiranje?): Boolean {
        tretiranje?.let { it ->
            tretiranjeRepository.delete(it)
        }
        return true
    }


}