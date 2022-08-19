package hr.ferit.tretiranjevoca.data

import androidx.room.*
import hr.ferit.tretiranjevoca.model.Tretiranje


@Dao
interface TretiranjeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(tretiranje: Tretiranje)

    @Delete
    fun delete(tretiranje: Tretiranje)

    @Query("SELECT * FROM tretiranja WHERE id =:id")
    fun getTretId(id: Long): Tretiranje?


    @Query("SELECT * FROM tretiranja WHERE (datumtretiranja + (karenca*86400000) )>:datum")
    fun getAllTretiranja(datum: Long): List<Tretiranje>



    @Query("SELECT COUNT(id) from tretiranja")
    fun getNumber(): Int


    //region Dohvaćanje ukupnog broja tretiranja pojedinih kultura
    @Query("SELECT COUNT(id) from tretiranja WHERE odabirvoca ='Sljive' ")
    fun getSljive(): Int

    @Query("SELECT COUNT(id) from tretiranja WHERE odabirvoca ='Kruske' ")
    fun getKruske(): Int

    @Query("SELECT COUNT(id) from tretiranja WHERE odabirvoca ='Jabuke' ")
    fun getJabuke(): Int

    @Query("SELECT COUNT(id) from tretiranja WHERE odabirvoca ='VinovaLoza' ")
    fun getVinovaLoza(): Int

    @Query("SELECT COUNT(id) from tretiranja WHERE (datumtretiranja + (karenca*86400000) )>:datum")
    fun getAktivneKarence(datum: Long): Int
    //endregion


    // region Dohvaćanje zadnjeg tretiranja pojedinih kultura
    @Query("SELECT MAX(datumtretiranja) from tretiranja WHERE odabirvoca ='Sljive'")
    fun getLastSljive(): Long
    @Query("SELECT MAX(datumtretiranja) from tretiranja WHERE odabirvoca ='Jabuke'")
    fun getLastJabuka(): Long
    @Query("SELECT MAX(datumtretiranja) from tretiranja WHERE odabirvoca ='Kruske'")
    fun getLastKruska(): Long
    @Query("SELECT MAX(datumtretiranja) from tretiranja WHERE odabirvoca ='VinovaLoza'")
    fun getLastVinovaLoza(): Long


    //endregion

    @Query("SELECT * FROM tretiranja WHERE odabirvoca='Sljive'")
    fun getAllSljive(): List<Tretiranje>
    @Query("SELECT * FROM tretiranja WHERE odabirvoca='VinovaLoza'")
    fun getAllVinova(): List<Tretiranje>
    @Query("SELECT * FROM tretiranja WHERE odabirvoca='Jabuke'")
    fun getAllJabuke(): List<Tretiranje>
    @Query("SELECT * FROM tretiranja WHERE odabirvoca='Kruske'")
    fun getAllKruske(): List<Tretiranje>


    // region Dohvacanje broja aktivnih karenca pojedinih kultura

    @Query("SELECT COUNT(id) from tretiranja WHERE (datumtretiranja + (karenca*86400000) )>:datum and odabirvoca = 'Sljive'" )
    fun getAktivneSljive(datum: Long): Int
    @Query("SELECT COUNT(id) from tretiranja WHERE (datumtretiranja + (karenca*86400000) )>:datum and odabirvoca = 'Jabuke'" )
    fun getAktivneJabuke(datum: Long): Int
    @Query("SELECT COUNT(id) from tretiranja WHERE (datumtretiranja + (karenca*86400000) )>:datum and odabirvoca = 'Kruske'" )
    fun getAktivneKruske(datum: Long): Int
    @Query("SELECT COUNT(id) from tretiranja WHERE (datumtretiranja + (karenca*86400000) )>:datum and odabirvoca = 'VinovaLoza'" )
    fun getAktivneVinova(datum: Long): Int
    //endregion


    //region Dohvaćanje tretiranja fungicidom pojedinih kultura

    @Query("SELECT COUNT(id) from tretiranja WHERE odabirvoca='Sljive' AND  tiptretiranja='Fungicid' ")
    fun getSljiveFungicid(): Int
    @Query("SELECT COUNT(id) from tretiranja WHERE odabirvoca='Jabuke' AND  tiptretiranja='Fungicid' ")
    fun getJabukeFungicid(): Int
    @Query("SELECT COUNT(id) from tretiranja WHERE odabirvoca='Kruske' AND  tiptretiranja='Fungicid' ")
    fun getKruskeFungicid(): Int
    @Query("SELECT COUNT(id) from tretiranja WHERE odabirvoca='VinovaLoza' AND  tiptretiranja='Fungicid' ")
    fun getVinovaFungicid(): Int

    //endregion
    //region Dohvaćanje tretiranja herbicidom pojedinih kultura

    @Query("SELECT COUNT(id) from tretiranja WHERE odabirvoca='Sljive' AND  tiptretiranja='Herbicid' ")
    fun getSljiveHerbicid(): Int
    @Query("SELECT COUNT(id) from tretiranja WHERE odabirvoca='Jabuke' AND  tiptretiranja='Herbicid' ")
    fun getJabukeHerbicid(): Int
    @Query("SELECT COUNT(id) from tretiranja WHERE odabirvoca='Kruske' AND  tiptretiranja='Herbicid' ")
    fun getKruskeHerbicid(): Int
    @Query("SELECT COUNT(id) from tretiranja WHERE odabirvoca='VinovaLoza' AND  tiptretiranja='Herbicid' ")
    fun getVinovaHerbicid(): Int

    //endregion
    //region Dohvaćanje tretiranja insekticidom pojedinih kultura

    @Query("SELECT COUNT(id) from tretiranja WHERE odabirvoca='Sljive' AND  tiptretiranja='Insekticid' ")
    fun getSljiveInsekticid(): Int
    @Query("SELECT COUNT(id) from tretiranja WHERE odabirvoca='Jabuke' AND  tiptretiranja='Insekticid' ")
    fun getJabukeInsekticid(): Int
    @Query("SELECT COUNT(id) from tretiranja WHERE odabirvoca='Kruske' AND  tiptretiranja='Insekticid' ")
    fun getKruskeInsekticid(): Int
    @Query("SELECT COUNT(id) from tretiranja WHERE odabirvoca='VinovaLoza' AND  tiptretiranja='Insekticid' ")
    fun getVinovaInsekticid(): Int

    //endregion
}

