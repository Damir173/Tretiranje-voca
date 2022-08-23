package hr.ferit.tretiranjevoca.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hr.ferit.tretiranjevoca.data.TretiranjeDao
import hr.ferit.tretiranjevoca.model.Tretiranje


@Database(
    entities = [Tretiranje::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TretiranjeConverteri::class)
abstract class TretiranjeBaza : RoomDatabase() {

    abstract fun getTretiranjeDao(): TretiranjeDao

    companion object {

        private const val databaseName = "tretiranjeBaza"

        @Volatile
        private var INSTANCE: TretiranjeBaza? = null

        fun getDatabase(context: Context): TretiranjeBaza {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): TretiranjeBaza {
            return Room.databaseBuilder(
                context.applicationContext,
                TretiranjeBaza::class.java,
                databaseName
            )
                    .allowMainThreadQueries()
                .build()
        }
    }
}