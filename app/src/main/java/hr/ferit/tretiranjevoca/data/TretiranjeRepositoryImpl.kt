package hr.ferit.tretiranjevoca.data

import hr.ferit.tretiranjevoca.model.Tretiranje

class TretiranjeRepositoryImpl(val tretiranjeDao: TretiranjeDao) : TretiranjeRepository {
    override fun save(tretiranje: Tretiranje) = tretiranjeDao.save(tretiranje)
    override fun delete(tretiranje: Tretiranje) = tretiranjeDao.delete(tretiranje)
    override fun getTretId(id: Long): Tretiranje? = tretiranjeDao.getTretId(id)
    override fun getAllTretiranja(datum:Long): List<Tretiranje> = tretiranjeDao.getAllTretiranja(datum)
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

  override fun getAllSljive(): List<Tretiranje> = tretiranjeDao.getAllSljive()
  override fun getAllJabuke(): List<Tretiranje> = tretiranjeDao.getAllJabuke()
  override fun getAllVinova(): List<Tretiranje> = tretiranjeDao.getAllVinova()
  override fun getAllKruske(): List<Tretiranje> = tretiranjeDao.getAllKruske()


 override fun getAktivneJabuke(datum:Long): Int = tretiranjeDao.getAktivneJabuke(datum)
    override fun getAktivneSljive(datum:Long): Int = tretiranjeDao.getAktivneSljive(datum)
    override fun getAktivneKruske(datum:Long): Int = tretiranjeDao.getAktivneKruske(datum)
    override fun getAktivneVinova(datum:Long): Int = tretiranjeDao.getAktivneVinova(datum)

    override fun getJabukeFungicid(): Int = tretiranjeDao.getJabukeFungicid()
    override fun getKruskeFungicid(): Int = tretiranjeDao.getKruskeFungicid()
    override fun getSljiveFungicid(): Int = tretiranjeDao.getSljiveFungicid()
    override fun getVinovaFungicid(): Int = tretiranjeDao.getVinovaFungicid()

    override fun getJabukeHerbicid(): Int = tretiranjeDao.getJabukeHerbicid()
    override fun getKruskeHerbicid(): Int = tretiranjeDao.getKruskeHerbicid()
    override fun getSljiveHerbicid(): Int = tretiranjeDao.getSljiveHerbicid()
    override fun getVinovaHerbicid(): Int = tretiranjeDao.getVinovaHerbicid()

    override fun getJabukeInsekticid(): Int = tretiranjeDao.getJabukeInsekticid()
    override fun getKruskeInsekticid(): Int = tretiranjeDao.getKruskeInsekticid()
    override fun getSljiveInsekticid(): Int = tretiranjeDao.getSljiveInsekticid()
    override fun getVinovaInsekticid(): Int = tretiranjeDao.getVinovaInsekticid()

}