package hr.ferit.tretiranjevoca.data.room

import androidx.room.TypeConverter
import hr.ferit.tretiranjevoca.model.OdabirVoca
import hr.ferit.tretiranjevoca.model.TipTretiranja
import java.util.*

class TretiranjeConverteri {


    @TypeConverter
    fun toStringVoce(voce: OdabirVoca): String{
        return voce.toString()
    }

    @TypeConverter
    fun fromStringVoce(voce: String): OdabirVoca{
        return when(voce){
            OdabirVoca.Sljive.toString() -> OdabirVoca.Sljive
            OdabirVoca.Jabuke.toString() -> OdabirVoca.Jabuke
            OdabirVoca.Kruske.toString() -> OdabirVoca.Kruske
            OdabirVoca.VinovaLoza.toString() -> OdabirVoca.VinovaLoza
            else -> OdabirVoca.Sljive
        }
    }


    @TypeConverter
    fun toStringTip(tip: TipTretiranja): String{
        return tip.toString()
    }


    @TypeConverter
    fun fromStringTip(tip: String): TipTretiranja{
        return when(tip){
            TipTretiranja.Herbicid.toString() -> TipTretiranja.Herbicid
            TipTretiranja.Fungicid.toString() -> TipTretiranja.Fungicid
            TipTretiranja.Insekticid.toString() -> TipTretiranja.Insekticid
            else -> TipTretiranja.Herbicid
        }
    }


    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }



}