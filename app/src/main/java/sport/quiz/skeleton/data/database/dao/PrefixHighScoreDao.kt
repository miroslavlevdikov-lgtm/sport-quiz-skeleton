package sport.quiz.skeleton.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import sport.quiz.skeleton.data.entity.PrefixHighScore

@Dao
interface PrefixHighScoreDao {

    @Query("SELECT * FROM prefix_high_scores WHERE questions = :questions")
    suspend fun getByQuestions(questions: Int): PrefixHighScore?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(score: PrefixHighScore)

    @Transaction
    suspend fun saveIfBetter(score: PrefixHighScore) {
        if (score.rightAnswers <= 0) return

        val currentBest = getByQuestions(score.questions)
        if (currentBest == null || score.rightAnswers > currentBest.rightAnswers) {
            insert(score)
        }
    }

    @Query("SELECT * FROM prefix_high_scores")
    suspend fun getAll(): List<PrefixHighScore>

    @Query("DELETE FROM prefix_high_scores")
    suspend fun deleteAll()
}