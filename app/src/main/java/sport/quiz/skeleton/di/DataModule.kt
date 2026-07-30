package sport.quiz.skeleton.di

import androidx.room.Room
import org.koin.dsl.module
import sport.quiz.skeleton.data.database.PrefixDatabase
import sport.quiz.skeleton.data.repository.PrefixHighScoreRepository
import sport.quiz.skeleton.data.repository.PrefixTopicRepository

private const val PREFIX_DB_NAME = "prefix_db"

val dataModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = PrefixDatabase::class.java,
            name = PREFIX_DB_NAME
        ).build()
    }

    single { get<PrefixDatabase>().highScoreDao() }

    single { PrefixHighScoreRepository(highScoreDao = get()) }

    single { PrefixTopicRepository() }
}