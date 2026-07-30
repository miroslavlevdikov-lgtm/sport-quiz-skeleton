package sport.quiz.skeleton.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import sport.quiz.skeleton.ui.viewmodel.PrefixGameViewModel
import sport.quiz.skeleton.ui.viewmodel.PrefixHighScoreViewModel
import sport.quiz.skeleton.ui.viewmodel.PrefixTopicsSelectionViewModel

val viewModule = module {
    viewModel {
        PrefixTopicsSelectionViewModel(topicRepository = get())
    }

    viewModel {
        PrefixGameViewModel(
            savedStateHandle = get(),
            topicRepository = get(),
            highScoreRepository = get()
        )
    }

    viewModel {
        PrefixHighScoreViewModel(
            highScoreRepository = get()
        )
    }
}