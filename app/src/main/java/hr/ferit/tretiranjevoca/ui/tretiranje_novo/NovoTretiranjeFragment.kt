package hr.ferit.tretiranjevoca.ui.tretiranje_novo

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import hr.ferit.tretiranjevoca.R
import hr.ferit.tretiranjevoca.databinding.FragmentNovoTretiranjeBinding
import hr.ferit.tretiranjevoca.di.TretiranjeRepositoryFactory
import hr.ferit.tretiranjevoca.model.OdabirVoca
import hr.ferit.tretiranjevoca.model.Tretiranje
import hr.ferit.tretiranjevoca.model.TipTretiranja
import java.util.*

class NovoTretiranjeFragment : Fragment() {

    private val taskRepository = TretiranjeRepositoryFactory.tretiranjeRepository
    lateinit var binding: FragmentNovoTretiranjeBinding


    val c: Calendar = Calendar.getInstance()
    val mYear = c.get(Calendar.YEAR)
    val mMonth = c.get(Calendar.MONTH)
    val mDay = c.get(Calendar.DAY_OF_MONTH)

    var a: Int = 1
    var b: Int = 1
    var e: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNovoTretiranjeBinding.inflate(layoutInflater)
        binding.bSaveTask.setOnClickListener{saveTask()}

        binding.etKolicina2.setOnClickListener {
            DatePickerDialog(requireContext(), 0, (DatePickerDialog.OnDateSetListener {
                    datePicker, i, i2, i3 -> pickTime1(i,i2,i3)
              a = i
                b = i2
                e = i3
            }
                    ), mYear, mMonth, mDay).show()
        }

        return binding.root
    }


    private fun pickTime1(y: Int,m: Int,d: Int): Date{


      //  val date_selected: String = ((m + 1).toString() + "/" + d + "/" + y)
  //  binding.etKolicina2.setText(date_selected)

        val cc = Calendar.getInstance()
          cc.set(y, m, d)

        binding.etKolicina2.setText(
            StringBuilder().append(d).append("/").append(m + 1).append("/").append(y))

        return cc.time
    }



    private fun saveTask() {

        val odabirVoca = when(binding.rgOdabirvoca.checkedRadioButtonId){
            R.id.rb_sljive -> OdabirVoca.Sljive
            R.id.rb_jabuke -> OdabirVoca.Jabuke
            R.id.rb_kruske -> OdabirVoca.Kruske
            R.id.rb_vinovaloza -> OdabirVoca.VinovaLoza
            else -> OdabirVoca.Sljive
        }

        val tipTretiranja = when(binding.rgTiptretiranja.checkedRadioButtonId){
            R.id.rb_herbicid -> TipTretiranja.Herbicid
            R.id.rb_fungicid -> TipTretiranja.Fungicid
            R.id.rb_insekticid -> TipTretiranja.Insekticid
            else -> TipTretiranja.Herbicid
        }

        val vrsta = binding.etVrsta.text.toString()
        val kolicina = binding.etKolicina.text.toString()
        val kolicina2 = Integer.parseInt(kolicina)
      //  val datumtretiranja = binding.datum.getDate()
        val datumtretiranja = pickTime1(a,b,e)
        val karenca = binding.etKarenca.text.toString()
        val karenca2 = karenca.toLong()
        val kratkanapomena = binding.etNapomena.text.toString()



        taskRepository.save(Tretiranje(0,odabirVoca,tipTretiranja,vrsta,kolicina2,datumtretiranja,karenca2, kratkanapomena) )


        Toast.makeText(context, getString(R.string.message_saving), Toast.LENGTH_SHORT).show()
        val action = NovoTretiranjeFragmentDirections.actionNewTaskFragmentToTaskListFragment()
        findNavController().navigate(action)


    }



    companion object {
        val Tag = "NewTask"

        fun create(): Fragment {
            return NovoTretiranjeFragment()
        }
    }
}