package sport.quiz.skeleton.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import sport.quiz.skeleton.data.entity.PrefixHighScore
import sport.quiz.skeleton.data.model.HighScoreSortType
import sport.quiz.skeleton.data.repository.PrefixHighScoreRepository
import sport.quiz.skeleton.di.DispatcherProvider

class PrefixHighScoreViewModel(
    private val highScoreRepository: PrefixHighScoreRepository,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    private val _highScoresState = MutableStateFlow<List<PrefixHighScore>>(emptyList())
    private val _selectedSortTypeState = MutableStateFlow(HighScoreSortType.RIGHT_ANSWERS)

    private val _isLoadingState = MutableStateFlow(true)
    val isLoadingState: StateFlow<Boolean> = _isLoadingState.asStateFlow()

    val selectedSortTypeState: StateFlow<HighScoreSortType> = _selectedSortTypeState.asStateFlow()

    val highScoresState: StateFlow<List<PrefixHighScore>> =
        combine(_highScoresState, _selectedSortTypeState) { scores, sortType ->
            when (sortType) {
                HighScoreSortType.RIGHT_ANSWERS -> scores.sortedByDescending { it.rightAnswers }
                HighScoreSortType.QUESTIONS -> scores.sortedByDescending { it.questions }
                HighScoreSortType.DATE -> scores.sortedByDescending { it.timestamp }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        loadHighScores()
    }

    fun loadHighScores() {
        viewModelScope.launch(dispatchers.main) {
            _isLoadingState.value = true
            _highScoresState.value = highScoreRepository.getAll()
            _isLoadingState.value = false
        }
    }

    fun onSortTypeSelected(sortType: HighScoreSortType) {
        _selectedSortTypeState.value = sortType
    }

    fun deleteHighScores() {
        viewModelScope.launch(dispatchers.main) {
            highScoreRepository.deleteAll()
            _highScoresState.value = emptyList()
        }
    }
}