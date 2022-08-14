package hr.ferit.tretiranjevoca.data

import hr.ferit.tretiranjevoca.model.Tretiranje


interface TretiranjeRepository {

    fun save(tretiranje: Tretiranje)
    fun delete(tretiranje: Tretiranje)
    fun getTretId(id: Long): Tretiranje?
    fun getAllTretiranja(datum: Long): List<Tretiranje>
    fun getNumber(): Int

    fun getSljive(): Int
    fun getKruske(): Int
    fun getJabuke(): Int
    fun getVinovaLoza(): Int

    fun getLastSljive(): Long
    fun getLastKruska(): Long
    fun getLastJabuka(): Long
    fun getLastVinovaLoza(): Long

    fun getAktivneKarence(datum: Long): Int


    fun getAllSljive():List<Tretiranje>
    fun getAllJabuke():List<Tretiranje>
    fun getAllVinova():List<Tretiranje>
    fun getAllKruske():List<Tretiranje>


    fun getAktivneSljive(datum: Long): Int
    fun getAktivneKruske(datum: Long): Int
    fun getAktivneJabuke(datum: Long): Int
    fun getAktivneVinova(datum: Long): Int


    fun getVinovaFungicid(): Int
    fun getJabukeFungicid(): Int
    fun getKruskeFungicid(): Int
    fun getSljiveFungicid(): Int

    fun getVinovaHerbicid(): Int
    fun getJabukeHerbicid(): Int
    fun getKruskeHerbicid(): Int
    fun getSljiveHerbicid(): Int

    fun getVinovaInsekticid(): Int
    fun getJabukeInsekticid(): Int
    fun getKruskeInsekticid(): Int
    fun getSljiveInsekticid(): Int
}

