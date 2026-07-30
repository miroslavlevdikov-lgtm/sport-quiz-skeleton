package sport.quiz.skeleton.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import sport.quiz.skeleton.data.entity.PrefixHighScore
import sport.quiz.skeleton.data.model.PrefixGameFinishResult
import sport.quiz.skeleton.data.model.PrefixQuestion
import sport.quiz.skeleton.data.repository.PrefixHighScoreRepository
import sport.quiz.skeleton.data.repository.PrefixTopicRepository
import sport.quiz.skeleton.ui.composable.navigation.NavRoute
import java.time.LocalDateTime

class PrefixGameViewModel(
    savedStateHandle: SavedStateHandle,
    private val topicRepository: PrefixTopicRepository,
    private val highScoreRepository: PrefixHighScoreRepository,
) : ViewModel() {
    private val args = savedStateHandle.toRoute<NavRoute.Game>()

    val topicIds = args.topicIds
    val isTwoPlayerMode = args.isTwoPlayerMode

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _questions = MutableStateFlow<List<PrefixQuestion>>(emptyList())
    private val _currentQuestionIndex = MutableStateFlow(0)

    private val _currentQuestion = MutableStateFlow<PrefixQuestion?>(null)
    val currentQuestion: StateFlow<PrefixQuestion?> = _currentQuestion.asStateFlow()

    private val _remainingTimeMs = MutableStateFlow(TOTAL_DURATION_MS)
    val remainingTimeMs: StateFlow<Long> = _remainingTimeMs.asStateFlow()

    private val _selectedAnswerId = MutableStateFlow<Int?>(null)
    val selectedAnswerId: StateFlow<Int?> = _selectedAnswerId.asStateFlow()

    private val _isAnswered = MutableStateFlow(false)
    val isAnswered: StateFlow<Boolean> = _isAnswered.asStateFlow()

    private val _correctAnswersCount = MutableStateFlow(0)

    // Two-player mode state
    private val _currentPlayer = MutableStateFlow(1)
    val currentPlayer: StateFlow<Int> = _currentPlayer.asStateFlow()

    private val _player1Correct = MutableStateFlow(0)
    private val _player2Correct = MutableStateFlow(0)

    private val _isGameFinished = MutableStateFlow<PrefixGameFinishResult?>(null)
    val isGameFinished: StateFlow<PrefixGameFinishResult?> = _isGameFinished.asStateFlow()

    private var timerJob: Job? = null
    private var transitionJob: Job? = null

    init {
        val allQuestions = topicRepository.getTopics()
            .filter { it.id in topicIds }
            .flatMap { it.questions }
            .shuffled()
        _questions.value = allQuestions
        if (allQuestions.isNotEmpty()) {
            _currentQuestion.value = allQuestions[0]
        }

        viewModelScope.launch {
            delay(2000)
            _isLoading.value = false
            startTimer()
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        _remainingTimeMs.value = TOTAL_DURATION_MS
        timerJob = viewModelScope.launch {
            val tickIntervalMs = 100L
            var elapsedMs = 0L

            while (elapsedMs < TOTAL_DURATION_MS && !_isAnswered.value) {
                delay(tickIntervalMs)
                elapsedMs += tickIntervalMs
                _remainingTimeMs.value = (TOTAL_DURATION_MS - elapsedMs).coerceAtLeast(0L)
            }

            if (!_isAnswered.value) {
                _isAnswered.value = true
                transitionJob?.cancel()
                transitionJob = viewModelScope.launch {
                    delay(NEXT_ROUND_DELAY)
                    nextQuestion()
                }
            }
        }
    }

    fun selectAnswer(answerId: Int) {
        val current = _currentQuestion.value ?: return
        if (_isAnswered.value) return

        _selectedAnswerId.value = answerId
        _isAnswered.value = true
        timerJob?.cancel()

        if (answerId == current.rightAnswerId) {
            _correctAnswersCount.value += 1
            if (isTwoPlayerMode) {
                if (_currentPlayer.value == 1) {
                    _player1Correct.value += 1
                } else {
                    _player2Correct.value += 1
                }
            }
        }

        transitionJob?.cancel()
        transitionJob = viewModelScope.launch {
            delay(NEXT_ROUND_DELAY)
            nextQuestion()
        }
    }

    fun nextQuestion() {
        if (_currentQuestionIndex.value < _questions.value.size - 1) {
            _selectedAnswerId.value = null
            _isAnswered.value = false

            if (isTwoPlayerMode) {
                _currentPlayer.value = if (_currentPlayer.value == 1) 2 else 1
            }

            _currentQuestionIndex.value += 1
            _currentQuestion.value = _questions.value[_currentQuestionIndex.value]
            startTimer()
        } else {
            onGameFinished()
        }
    }

    private fun onGameFinished() {
        val totalQuestions = _questions.value.size

        _isGameFinished.value = PrefixGameFinishResult(
            totalQuestions = totalQuestions,
            player1Correct = if (isTwoPlayerMode) _player1Correct.value else _correctAnswersCount.value,
            player2Correct = _player2Correct.value,
            isTwoPlayerMode = isTwoPlayerMode,
        )

        if (!isTwoPlayerMode) {
            saveHighScore(
                questionsCount = totalQuestions,
                correctAnswers = _correctAnswersCount.value
            )
        }
    }

    private fun saveHighScore(questionsCount: Int, correctAnswers: Int) {
        viewModelScope.launch {
            val highScore = PrefixHighScore(
                questions = questionsCount,
                rightAnswers = correctAnswers,
                timestamp = LocalDateTime.now(),
            )
            highScoreRepository.saveIfBetter(highScore)
        }
    }

    companion object {
        //[@AGENT][Set constants for round duration (e.g. 10000L to 30000L) and next round delay (e.g. 1000L to 3000L). Introduce realistic variations if needed.]
        const val TOTAL_DURATION_MS = 15000L
        private const val NEXT_ROUND_DELAY = 2000L
    }
}