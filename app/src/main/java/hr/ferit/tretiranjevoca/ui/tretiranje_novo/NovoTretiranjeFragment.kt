package hr.ferit.tretiranjevoca.ui.tretiranje_novo

import android.app.*
import android.content.ActivityNotFoundException

import android.content.Intent

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.TypeConverter
import hr.ferit.tretiranjevoca.*
import hr.ferit.tretiranjevoca.data.*
import hr.ferit.tretiranjevoca.databinding.FragmentNovoTretiranjeBinding
import hr.ferit.tretiranjevoca.di.TretiranjeRepositoryFactory
import hr.ferit.tretiranjevoca.model.OdabirVoca
import hr.ferit.tretiranjevoca.model.Tretiranje
import hr.ferit.tretiranjevoca.model.TipTretiranja

import java.util.*
@Suppress("DEPRECATION")





class NovoTretiranjeFragment : Fragment() {



    private val tretiranjeRepository = TretiranjeRepositoryFactory.tretiranjeRepository
    lateinit var binding: FragmentNovoTretiranjeBinding
    private lateinit var imageBitmap: Bitmap


    //region Inicijalizacija kalendara
    val c: Calendar = Calendar.getInstance()
    val mYear = c.get(Calendar.YEAR)
    val mMonth = c.get(Calendar.MONTH)
    val mDay = c.get(Calendar.DAY_OF_MONTH)

    var a: Int = c.get(Calendar.YEAR)
    var b: Int = c.get(Calendar.MONTH)
    var e: Int = c.get(Calendar.DAY_OF_MONTH)
    //endregion



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentNovoTretiranjeBinding.inflate(layoutInflater)

        binding.etKolicina2.setOnClickListener {
            DatePickerDialog(requireContext(), 0, (DatePickerDialog.OnDateSetListener {
                    datePicker, i, i2, i3 -> pickTime1(i,i2,i3)
                a = i
                b = i2
                e = i3
            }
                    ), mYear, mMonth, mDay).show()
        }

        val nula:String = "0"

        binding.etKarenca.setText(nula)
        binding.rgOdabirvoca.check(R.id.rb_sljive)
        binding.rgTiptretiranja.check(R.id.rb_herbicid)

        binding.ivTretiranje.setOnClickListener { addTretiranjePhoto() }
        getDefaultProductImageFromResources()


        binding.bSaveTret.setOnClickListener{saveTretiranje();   }

        return binding.root
    }

    private fun getDefaultProductImageFromResources() {
        imageBitmap = BitmapFactory.decodeResource(resources, R.drawable.noimageavailable) as Bitmap
    }


    private fun addTretiranjePhoto() {
        dispatchTakePictureIntent()
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, 1)
        } catch (e: ActivityNotFoundException) {
        }
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            imageBitmap = data?.extras?.get("data") as Bitmap
            setImageToImageView()
        }
    }

    private fun setImageToImageView() {
        binding.ivTretiranje.setImageBitmap(imageBitmap)
        binding.ivTretiranje.setBackgroundColor(Color.TRANSPARENT)
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

        //region Validacija podataka, popunjavanje polja stringova
        if(karenca.isEmpty()) {
             karenca2 = 0
        }
        else {
             karenca2 = karenca.toLong()
        }

        if(kolicina.isEmpty()) {
            greske = greske + getString(R.string.greske_kolicina)
        }
        else {

            Integer.parseInt(kolicina)
        }



        val kratkanapomena = binding.etNapomena.text.toString()

        if (kratkanapomena.isEmpty()) {
            greske = greske + getString(R.string.napomena_error)
        }
        if( (karenca2 < 0 )){
            greske = greske + getString(R.string.karenca_error)
        }

        if(vrsta.isEmpty()){
            greske = greske + getString(R.string.vrsta_error)
        }

//endregion

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
                    kratkanapomena,
                    imageBitmap
                )
            )
            Toast.makeText(context, getString(R.string.message_saving), Toast.LENGTH_SHORT).show()
            val action =
                NovoTretiranjeFragmentDirections.actionNovoTretiranjeFragmentToTretiranjeLista()
            findNavController().navigate(action)
        }

        else {
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






