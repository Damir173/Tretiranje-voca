package hr.ferit.tretiranjevoca.di

import hr.ferit.tretiranjevoca.TretiranjeVoca
import hr.ferit.tretiranjevoca.data.TretiranjeRepository
import hr.ferit.tretiranjevoca.data.TretiranjeRepositoryImpl
import hr.ferit.tretiranjevoca.data.room.TretiranjeBaza

object TretiranjeRepositoryFactory {

    val roomDb = TretiranjeBaza.getDatabase(TretiranjeVoca.application)
    val tretiranjeRepository: TretiranjeRepository = TretiranjeRepositoryImpl(roomDb.getTaskDao())
}