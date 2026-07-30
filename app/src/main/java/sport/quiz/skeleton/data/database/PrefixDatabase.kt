package sport.quiz.skeleton.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import sport.quiz.skeleton.data.database.dao.PrefixHighScoreDao
import sport.quiz.skeleton.data.database.converter.Converters
import sport.quiz.skeleton.data.entity.PrefixHighScore

@Database(
    entities = [PrefixHighScore::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PrefixDatabase : RoomDatabase() {

    abstract fun highScoreDao(): PrefixHighScoreDao
}

