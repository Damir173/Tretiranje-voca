package hr.ferit.tretiranjevoca.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tretiranja")
data class Tretiranje(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @ColumnInfo(name = "odabirvoca")
    val odabirVoca: OdabirVoca,
    @ColumnInfo(name = "tiptretiranja")
    val tipTretiranja: TipTretiranja,
    @ColumnInfo(name = "vrsta")
    val vrsta: String,
    @ColumnInfo(name = "kolicina")
    val kolicina: Int,
    @ColumnInfo(name = "datumtretiranja")
    val datumtretiranja: Date,
    @ColumnInfo(name = "karenca")
    val karenca: Long,
    @ColumnInfo(name = "napomena")
    val napomena: String
) {

}