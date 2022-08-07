package hr.ferit.tretiranjevoca.data

import hr.ferit.tretiranjevoca.model.Tretiranje


interface TretiranjeRepository {

    fun save(tretiranje: Tretiranje)
    fun delete(tretiranje: Tretiranje)
    fun getTaskById(id: Long): Tretiranje?
    fun getAllTasks(datum: Long): List<Tretiranje>
  //  fun getNoviQuery(): List<Task>
    fun getNumber(): Int

    //region Dohvaćanje ukupnog broja tretiranja pojedinih kultura
    fun getSljive(): Int
    fun getKruske(): Int
    fun getJabuke(): Int
    fun getVinovaLoza(): Int

    //endregion
    fun getLastSljive(): Long
    fun getLastKruska(): Long
    fun getLastJabuka(): Long
    fun getLastVinovaLoza(): Long

    fun getAktivneKarence(datum: Long): Int
}

