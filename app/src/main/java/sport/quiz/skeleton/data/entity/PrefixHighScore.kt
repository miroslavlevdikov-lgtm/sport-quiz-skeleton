package sport.quiz.skeleton.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity("prefix_high_scores")
data class PrefixHighScore(
    @PrimaryKey val questions: Int,
    @ColumnInfo(name = "right_answers") val rightAnswers: Int,
    val timestamp: LocalDateTime,
)