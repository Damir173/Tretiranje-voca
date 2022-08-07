package hr.ferit.tretiranjevoca.data

import hr.ferit.tretiranjevoca.model.Tretiranje

class TretiranjeRepositoryImpl(val tretiranjeDao: TretiranjeDao) : TretiranjeRepository {
    override fun save(tretiranje: Tretiranje) = tretiranjeDao.save(tretiranje)
    override fun delete(tretiranje: Tretiranje) = tretiranjeDao.delete(tretiranje)
    override fun getTaskById(id: Long): Tretiranje? = tretiranjeDao.getTaskById(id)
    override fun getAllTasks(datum:Long): List<Tretiranje> = tretiranjeDao.getAllTasks(datum)
   // override fun getNoviQuery(): List<Task> = taskDao.getNoviQuery()
    override fun getNumber(): Int = tretiranjeDao.getNumber()
    override fun getSljive(): Int = tretiranjeDao.getSljive()
    override fun getKruske(): Int = tretiranjeDao.getKruske()
    override fun getJabuke(): Int = tretiranjeDao.getJabuke()
    override fun getVinovaLoza(): Int = tretiranjeDao.getVinovaLoza()

    override fun getLastSljive(): Long = tretiranjeDao.getLastSljive()
    override fun getLastKruska(): Long = tretiranjeDao.getLastKruska()
    override fun getLastJabuka(): Long = tretiranjeDao.getLastJabuka()
    override fun getLastVinovaLoza(): Long = tretiranjeDao.getLastVinovaLoza()

    override fun getAktivneKarence(datum:Long): Int = tretiranjeDao.getAktivneKarence(datum)


}