package hr.ferit.tretiranjevoca.data

import androidx.room.*
import hr.ferit.tretiranjevoca.model.Tretiranje


@Dao
interface TretiranjeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(tretiranje: Tretiranje)

    @Delete
    fun delete(tretiranje: Tretiranje)

    @Query("SELECT * FROM tasks WHERE id =:id")
    fun getTaskById(id: Long): Tretiranje?


    @Query("SELECT * FROM tasks WHERE (datumtretiranja + (karenca*86400000) )>:datum")
    fun getAllTasks(datum: Long): List<Tretiranje>



  //  @Query("SELECT * FROM tasks WHERE content='picka'")
   // fun getNoviQuery(): List<Task>


    @Query("SELECT COUNT(id) from tasks")
    fun getNumber(): Int


    //region Dohvaćanje ukupnog broja tretiranja pojedinih kultura
    @Query("SELECT COUNT(id) from tasks WHERE odabirvoca ='Sljive' ")
    fun getSljive(): Int

    @Query("SELECT COUNT(id) from tasks WHERE odabirvoca ='Kruske' ")
    fun getKruske(): Int

    @Query("SELECT COUNT(id) from tasks WHERE odabirvoca ='Jabuke' ")
    fun getJabuke(): Int

    @Query("SELECT COUNT(id) from tasks WHERE odabirvoca ='VinovaLoza' ")
    fun getVinovaLoza(): Int

    @Query("SELECT COUNT(id) from tasks WHERE (datumtretiranja + (karenca*86400000) )>:datum")
    fun getAktivneKarence(datum: Long): Int


    //endregion


    // region Dohvaćanje zadnjeg tretiranja pojedinih kultura
    @Query("SELECT MAX(datumtretiranja) from tasks WHERE odabirvoca ='Sljive'")
    fun getLastSljive(): Long
    @Query("SELECT MAX(datumtretiranja) from tasks WHERE odabirvoca ='Jabuke'")
    fun getLastJabuka(): Long
    @Query("SELECT MAX(datumtretiranja) from tasks WHERE odabirvoca ='Kruske'")
    fun getLastKruska(): Long
    @Query("SELECT MAX(datumtretiranja) from tasks WHERE odabirvoca ='VinovaLoza'")
    fun getLastVinovaLoza(): Long


    //endregion




}

