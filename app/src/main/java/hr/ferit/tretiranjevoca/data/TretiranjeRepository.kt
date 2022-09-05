package hr.ferit.tretiranjevoca.data

import hr.ferit.tretiranjevoca.model.Tretiranje


interface TretiranjeRepository {

    fun save(tretiranje: Tretiranje)
    fun delete(tretiranje: Tretiranje)
    fun getTretId(id: Long): Tretiranje?
    fun getAllTretiranja(datum: Long): List<Tretiranje>
    fun getNumber(): Int
    fun getAktivneKarence(datum: Long): Int

    //region Dohvaćanje ukupnog broja tretiranja pojedinih kultura
    fun getSljive(): Int
    fun getKruske(): Int
    fun getJabuke(): Int
    fun getVinovaLoza(): Int
//endregion

    //region Dohvaćanje poslijednog tretiranja pojedinih kultura
    fun getLastSljive(): Long
    fun getLastKruska(): Long
    fun getLastJabuka(): Long
    fun getLastVinovaLoza(): Long
    //endregion

    //region Dohvaćanje liste pojedinih kultura
    fun getAllSljive():List<Tretiranje>
    fun getAllJabuke():List<Tretiranje>
    fun getAllVinova():List<Tretiranje>
    fun getAllKruske():List<Tretiranje>
//endregion

    //region Dohvaćanje broja aktivnih karenci pojedinih kultura
    fun getAktivneSljive(datum: Long): Int
    fun getAktivneKruske(datum: Long): Int
    fun getAktivneJabuke(datum: Long): Int
    fun getAktivneVinova(datum: Long): Int
    //endregion

    //region Dohvaćanja tretiranja fungicidom pojedinih kultura
    fun getVinovaFungicid(): Int
    fun getJabukeFungicid(): Int
    fun getKruskeFungicid(): Int
    fun getSljiveFungicid(): Int
    //endregion

    //region Dohvaćanja tretiranja herbicidom pojedinih kutlura
    fun getVinovaHerbicid(): Int
    fun getJabukeHerbicid(): Int
    fun getKruskeHerbicid(): Int
    fun getSljiveHerbicid(): Int
    //endregion

    //region Dohvaćanje tretiranje insekticidom pojedinih kultura
    fun getVinovaInsekticid(): Int
    fun getJabukeInsekticid(): Int
    fun getKruskeInsekticid(): Int
    fun getSljiveInsekticid(): Int
    //endregion
}

