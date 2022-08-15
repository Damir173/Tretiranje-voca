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

    private val tretiranjeRepository = TretiranjeRepositoryFactory.tretiranjeRepository
    lateinit var binding: FragmentNovoTretiranjeBinding


    val c: Calendar = Calendar.getInstance()
    val mYear = c.get(Calendar.YEAR)
    val mMonth = c.get(Calendar.MONTH)
    val mDay = c.get(Calendar.DAY_OF_MONTH)

    var a: Int = c.get(Calendar.YEAR)
    var b: Int = c.get(Calendar.MONTH)
    var e: Int = c.get(Calendar.DAY_OF_MONTH)



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNovoTretiranjeBinding.inflate(layoutInflater)
        binding.bSaveTret.setOnClickListener{saveTretiranje()}

        binding.etKolicina2.setOnClickListener {
            DatePickerDialog(requireContext(), 0, (DatePickerDialog.OnDateSetListener {
                    datePicker, i, i2, i3 -> pickTime1(i,i2,i3)
                a = i
                b = i2
                e = i3
            }
                    ), mYear, mMonth, mDay).show()
        }

        var nula:String = "0"
        binding.etKarenca.setText(nula)

        binding.rgOdabirvoca.check(R.id.rb_sljive)
        binding.rgTiptretiranja.check(R.id.rb_herbicid)

        return binding.root
    }


    private fun pickTime1(y: Int,m: Int,d: Int): Date{

        val cc = Calendar.getInstance()
          cc.set(y, m, d)
        binding.etKolicina2.setText(
            StringBuilder().append(d).append("/").append(m + 1).append("/").append(y))

        return cc.time
    }



    private fun saveTretiranje() {



        var greske = arrayOf<String>()
        val odabirVoca = when (binding.rgOdabirvoca.checkedRadioButtonId) {
            R.id.rb_sljive -> OdabirVoca.Sljive
            R.id.rb_jabuke -> OdabirVoca.Jabuke
            R.id.rb_kruske -> OdabirVoca.Kruske
            R.id.rb_vinovaloza -> OdabirVoca.VinovaLoza
            else -> OdabirVoca.Sljive
        }

        val tipTretiranja = when (binding.rgTiptretiranja.checkedRadioButtonId) {
            R.id.rb_herbicid -> TipTretiranja.Herbicid
            R.id.rb_fungicid -> TipTretiranja.Fungicid
            R.id.rb_insekticid -> TipTretiranja.Insekticid
            else -> TipTretiranja.Herbicid
        }

        var karenca2: Long
        val vrsta = binding.etVrsta.text.toString()
        val kolicina = binding.etKolicina.text.toString()
        val datumtretiranja = pickTime1(a, b, e)
        val karenca = binding.etKarenca.text.toString()
        if(karenca.isEmpty()) {
             karenca2 = 0
        }
        else {
             karenca2 = karenca.toLong()
        }

        if(kolicina.isEmpty()) {
            greske = greske + "Molimo unesite kolicinu!"
        }
        else {

            Integer.parseInt(kolicina)
        }



        val kratkanapomena = binding.etNapomena.text.toString()

        if (kratkanapomena.isEmpty()) {
            greske = greske + "Napomena ne moze biti prazna!"
        }
        if( (karenca2 < 0 )){
            greske = greske + "Karenca ne moze biti negativna!"
        }

        if(vrsta.isEmpty()){
            greske = greske + "Unesite vrstu i proizvođača!"
        }




        if (greske.isEmpty()) {
            tretiranjeRepository.save(
                Tretiranje(
                    0,
                    odabirVoca,
                    tipTretiranja,
                    vrsta,
                    Integer.parseInt(kolicina),
                    datumtretiranja,
                    karenca2,
                    kratkanapomena
                )
            )
            Toast.makeText(context, getString(R.string.message_saving), Toast.LENGTH_SHORT).show()
            val action =
                NovoTretiranjeFragmentDirections.actionNovoTretiranjeFragmentToTretiranjeLista()
            findNavController().navigate(action)
        }

        else{

            var errorstring = ""
            greske.forEach {
                errorstring = errorstring + it
                errorstring = errorstring + "\n"

            }
            Toast.makeText(context, errorstring , Toast.LENGTH_SHORT).show()

        }
    }






    companion object {

        fun create(): Fragment {
            return NovoTretiranjeFragment()
        }
    }
}