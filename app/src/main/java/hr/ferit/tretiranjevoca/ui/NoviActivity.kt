package hr.ferit.tretiranjevoca.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hr.ferit.tretiranjevoca.databinding.FragmentTestBinding

import hr.ferit.tretiranjevoca.di.TretiranjeRepositoryFactory
import hr.ferit.tretiranjevoca.model.Tretiranje
import hr.ferit.tretiranjevoca.ui.tretiranje_lista.*

class NoviActivity: Fragment(), OnTaskEventListener {




    private lateinit var binding: FragmentTestBinding
    private lateinit var adapter: TretiranjeAdapter
    private val tretiranjeRepository = TretiranjeRepositoryFactory.tretiranjeRepository

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTestBinding.inflate(layoutInflater)
        setupRecyclerView()
        updateData()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.testTaskovi.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = TretiranjeAdapter()
        adapter.onTaskSelectedListener = this
        binding.testTaskovi.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    private fun updateData() {
        adapter.setTasks(tretiranjeRepository.getAllSljive())
    }

    companion object {
        val Tag = "TasksList"

        fun create(): Fragment {
            return NoviActivity()
        }
    }

    override fun onTaskSelected(id: Long?) {

        val action = NoviActivityDirections.actionNoviActivityToTaskDetailsFragment2(id ?: -1)
        findNavController().navigate(action)
    }

    override fun onTaskLongPress(tretiranje: Tretiranje?): Boolean {
        tretiranje?.let { it ->
            tretiranjeRepository.delete(it)
        //    adapter.setTasks(taskRepository.getNoviQuery())
        }
        return true
    }


}