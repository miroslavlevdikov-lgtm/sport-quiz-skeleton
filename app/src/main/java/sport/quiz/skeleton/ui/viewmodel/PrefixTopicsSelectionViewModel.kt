package sport.quiz.skeleton.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import sport.quiz.skeleton.data.model.PrefixTopic
import sport.quiz.skeleton.data.repository.PrefixTopicRepository

class PrefixTopicsSelectionViewModel(
    private val topicRepository: PrefixTopicRepository,
) : ViewModel() {
    private val _topicsState = MutableStateFlow<List<PrefixTopic>>(emptyList())
    val topicsState: StateFlow<List<PrefixTopic>> = _topicsState.asStateFlow()

    private val _selectedTopicIdsState = MutableStateFlow<List<Int>>(emptyList())
    val selectedTopicIdsState: StateFlow<List<Int>> = _selectedTopicIdsState.asStateFlow()

    init {
        loadTopics()
    }

    private fun loadTopics() {
        _topicsState.value = topicRepository.getTopics()
    }

    fun selectTopic(topicId: Int) {
        _selectedTopicIdsState.value = if (topicId in _selectedTopicIdsState.value) {
            _selectedTopicIdsState.value - topicId
        } else {
            _selectedTopicIdsState.value + topicId
        }
    }

    fun selectRandomTopics() {
        val topics = _topicsState.value
        if (topics.size <= 1) return

        val currentSelection = _selectedTopicIdsState.value.toSet()

        var newSelection: List<Int>

        do {
            val maxCount = minOf(5, topics.size - 1)
            val count = (1..maxCount).random()

            newSelection = topics
                .shuffled()
                .take(count)
                .map(PrefixTopic::id)
        } while (newSelection.toSet() == currentSelection)

        _selectedTopicIdsState.value = newSelection
    }
}