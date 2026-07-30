package sport.quiz.skeleton.ui.composable.sceens.game_over

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PrefixGameOverScreen(
    totalQuestions: Int,
    player1CorrectAnswers: Int,
    player2CorrectAnswers: Int,
    isTwoPlayerMode: Boolean,
    onMainMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    //[@AGENT][Game over screen. Display title, conditional result content based on isTwoPlayerMode (TwoPlayerResults or SinglePlayerResults), and a full-width navigation button back to Main Menu.]
}

@Composable
private fun SinglePlayerResults(
    totalQuestions: Int,
    correctAnswers: Int,
) {
    //[@AGENT][Single player summary card. Calculate accuracy percentage and display total questions, correct answers, and final success rate.]
}

@Composable
private fun TwoPlayerResults(
    totalQuestions: Int,
    player1Correct: Int,
    player2Correct: Int,
) {
    //[@AGENT][Two-player summary view. Determine winner text/color and display side-by-side player result cards comparing both players.]
}