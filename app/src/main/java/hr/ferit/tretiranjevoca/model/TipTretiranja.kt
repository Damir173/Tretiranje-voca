package hr.ferit.tretiranjevoca.model

sealed class TipTretiranja{

    object Herbicid : TipTretiranja() {
        override fun toString(): String = "Herbicid"
    }
    object Fungicid : TipTretiranja() {
        override fun toString(): String = "Fungicid"
    }
    object Insekticid : TipTretiranja(){
        override fun toString(): String = "Insekticid"
    }

}
