package sport.quiz.skeleton.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import sport.quiz.skeleton.data.database.dao.PrefixHighScoreDao
import sport.quiz.skeleton.data.entity.PrefixHighScore

class PrefixHighScoreRepository(
    private val highScoreDao: PrefixHighScoreDao,
) {
    suspend fun saveIfBetter(score: PrefixHighScore) {
        withContext(Dispatchers.IO) {
            highScoreDao.saveIfBetter(score)
        }
    }

    suspend fun getAll(): List<PrefixHighScore> {
        return withContext(Dispatchers.IO) {
            highScoreDao.getAll()
        }
    }

    suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            highScoreDao.deleteAll()
        }
    }
}