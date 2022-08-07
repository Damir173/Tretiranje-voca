package hr.ferit.tretiranjevoca.model

sealed class OdabirVoca{

    object Sljive : OdabirVoca() {
        override fun toString(): String = "Sljive"
    }
    object Jabuke : OdabirVoca() {
        override fun toString(): String = "Jabuke"
    }
    object Kruske : OdabirVoca(){
        override fun toString(): String = "Kruske"
    }
    object VinovaLoza : OdabirVoca(){
        override fun toString(): String = "VinovaLoza"
    }

}
