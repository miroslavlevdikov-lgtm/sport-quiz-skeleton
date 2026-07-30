package sport.quiz.skeleton.ui.composable.sceens.high_scores

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import sport.quiz.skeleton.data.entity.PrefixHighScore
import sport.quiz.skeleton.data.model.HighScoreSortType
import sport.quiz.skeleton.ui.viewmodel.PrefixHighScoreViewModel

@Composable
fun PrefixHighScoresScreen(
    modifier: Modifier = Modifier,
    viewModel: PrefixHighScoreViewModel = koinViewModel(),
    onBackClick: () -> Unit,
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    val isLoading by viewModel.isLoadingState.collectAsStateWithLifecycle()
    val highScores by viewModel.highScoresState.collectAsStateWithLifecycle()
    val selectedSortType by viewModel.selectedSortTypeState.collectAsStateWithLifecycle()

    //[@AGENT][High scores screen container. Render AppTopBar with a clear scores action, handle ConfirmationDialog visibility, and host HighScoresList filling available space.]
}

@Composable
fun HighScoresList(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    scores: List<PrefixHighScore>,
    selectedSortType: HighScoreSortType,
    onSortTypeSelected: (HighScoreSortType) -> Unit,
) {
    //[@AGENT][High scores list view. Render sorting option chips at the top and conditionally display a loading indicator, empty state text, or LazyColumn of score items based on state.]
}