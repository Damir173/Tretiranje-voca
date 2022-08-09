
import android.content.res.Resources
import hr.ferit.tretiranjevoca.R
import hr.ferit.tretiranjevoca.model.OdabirVoca

fun Resources.odabirSlikeVoca(tip: OdabirVoca): Int{
    return when(tip){
        OdabirVoca.Sljive -> R.drawable.sljiva
        OdabirVoca.Jabuke -> R.drawable.jabuka
        OdabirVoca.VinovaLoza -> R.drawable.vinovaloza
        OdabirVoca.Kruske -> R.drawable.kruska
        else -> R.drawable.ic_launcher_background
    }
}



