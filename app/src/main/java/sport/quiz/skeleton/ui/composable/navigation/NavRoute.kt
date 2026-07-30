package sport.quiz.skeleton.ui.composable.navigation

import kotlinx.serialization.Serializable

sealed class NavRoute {
    @Serializable
    object Splash : NavRoute()

    @Serializable
    object MainMenu : NavRoute()

    @Serializable
    object About : NavRoute()

    @Serializable
    data class TopicsSelection(val isTwoPlayerMode: Boolean) : NavRoute()

    @Serializable
    data class Game(
        val isTwoPlayerMode: Boolean,
        val topicIds: List<Int>,
    ) : NavRoute()

    @Serializable
    data class GameOverScreen(
        val totalQuestions: Int,
        val correctAnswers: Int,
        val player2CorrectAnswers: Int = 0,
        val isTwoPlayerMode: Boolean = false,
    ) : NavRoute()

    @Serializable
    object HighScores : NavRoute()
}