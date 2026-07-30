package sport.quiz.skeleton.data.model

data class PrefixGameFinishResult(
    val totalQuestions: Int,
    val player1Correct: Int,
    val player2Correct: Int,
    val isTwoPlayerMode: Boolean,
)