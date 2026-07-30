package sport.quiz.skeleton.ui.composable.sceens.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import sport.quiz.skeleton.data.model.PrefixAnswer
import sport.quiz.skeleton.data.model.PrefixGameFinishResult
import sport.quiz.skeleton.ui.viewmodel.PrefixGameViewModel

@Composable
fun PrefixGameScreen(
    onGameOverNavigation: (PrefixGameFinishResult) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    gameViewModel: PrefixGameViewModel = koinViewModel(),
) {
    val isLoading by gameViewModel.isLoading.collectAsStateWithLifecycle()
    val currentQuestion by gameViewModel.currentQuestion.collectAsStateWithLifecycle()
    val remainingTimeMs by gameViewModel.remainingTimeMs.collectAsStateWithLifecycle()
    val timerProgress = remainingTimeMs.toFloat() / PrefixGameViewModel.TOTAL_DURATION_MS
    val selectedAnswerId by gameViewModel.selectedAnswerId.collectAsStateWithLifecycle()
    val isAnswered by gameViewModel.isAnswered.collectAsStateWithLifecycle()
    val currentPlayer by gameViewModel.currentPlayer.collectAsStateWithLifecycle()
    val isGameFinished by gameViewModel.isGameFinished.collectAsStateWithLifecycle()

    var showExitDialog by remember { mutableStateOf(false) }

    //[@AGENT][Main active game screen. Intercept system back press to show confirmation dialog, handle game finish navigation, and display top bar, loading state, question card with optional image, progress timer, and AnswersGrid.]
}

@Composable
fun AnswersGrid(
    answers: List<PrefixAnswer>,
    rightAnswerId: Int,
    selectedAnswerId: Int?,
    isAnswered: Boolean,
    onAnswerClick: (PrefixAnswer) -> Unit,
    modifier: Modifier = Modifier
) {
    //[@AGENT][Grid container for answer options. Dynamic layout (row pair or 3-item grid) that highlights correct/wrong selections and passes user clicks to the view model.]
}