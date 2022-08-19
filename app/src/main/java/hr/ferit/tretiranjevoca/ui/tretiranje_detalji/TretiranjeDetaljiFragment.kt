package hr.ferit.tretiranjevoca.ui.tretiranje_detalji

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import hr.ferit.tretiranjevoca.model.Tretiranje
import hr.ferit.tretiranjevoca.di.TretiranjeRepositoryFactory
import hr.ferit.tretiranjevoca.databinding.FragmentTretiranjeDetaljiBinding
import java.text.SimpleDateFormat

class TretiranjeDetaljiFragment : Fragment() {

    private val dateDisplayFormat = SimpleDateFormat("yyyy-MM-dd")
    private lateinit var binding: FragmentTretiranjeDetaljiBinding
    private val tretiranjeRepository = TretiranjeRepositoryFactory.tretiranjeRepository
    private val args: TretiranjeDetaljiFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTretiranjeDetaljiBinding.inflate(layoutInflater)
;
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tretiranje = tretiranjeRepository.getTretId(args.taskId)
        display(tretiranje)
    }

    private fun display(tretiranje: Tretiranje?) {
        tretiranje?.let {
            binding.apply {
            binding.ivDetalj.setImageBitmap(tretiranje.photo)
            }
        }
    }

    companion object {
        val Tag = "TasksDetails"
        val TaskIdKey = "TaskId"

        fun create(id: Long): Fragment {
            val fragment = TretiranjeDetaljiFragment()
            return fragment
        }
    }
}