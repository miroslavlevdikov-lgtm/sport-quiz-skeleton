package sport.quiz.skeleton.ui.composable.sceens.topics_selection

import android.adservices.topics.Topic
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import sport.quiz.skeleton.ui.viewmodel.PrefixTopicsSelectionViewModel

@Composable
fun PrefixTopicsSelectionScreen(
    modifier: Modifier = Modifier,
    viewModel: PrefixTopicsSelectionViewModel = koinViewModel(),
    isTwoPlayerMode: Boolean,
    onGameScreenNavigation: (Boolean, List<Int>) -> Unit,
    onBackClick: () -> Unit,
) {
    val topics by viewModel.topicsState.collectAsStateWithLifecycle()
    val selectedTopicIds by viewModel.selectedTopicIdsState.collectAsStateWithLifecycle()

    //[@AGENT][Main topic selection screen. Combine AppTopBar with shuffle action, TopicsList filling available space, and a confirmation button at the bottom.]
}

@Composable
fun TopicsList(
    topics: List<Topic>,
    selectedTopicIds: List<Int>,
    onTopicClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    //[@AGENT][Vertical scrollable list (LazyColumn) for topics. Render item cards with selection animation, topic title, question count, and click listener.]
}
