package sport.quiz.skeleton.ui.composable.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import sport.quiz.skeleton.ui.composable.sceens.about.PrefixAboutScreen
import sport.quiz.skeleton.ui.composable.sceens.game.PrefixGameScreen
import sport.quiz.skeleton.ui.composable.sceens.game_over.PrefixGameOverScreen
import sport.quiz.skeleton.ui.composable.sceens.high_scores.PrefixHighScoresScreen
import sport.quiz.skeleton.ui.composable.sceens.main_menu.PrefixMainMenuScreen
import sport.quiz.skeleton.ui.composable.sceens.splash.PrefixSplashScreen
import sport.quiz.skeleton.ui.composable.sceens.topics_selection.PrefixTopicsSelectionScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val activity = LocalActivity.current

    NavHost(
        navController = navController,
        startDestination = NavRoute.Splash,
        modifier = modifier,
    ) {
        composable<NavRoute.Splash> {
            PrefixSplashScreen(
                onMainMenuScreenNavigation = {
                    navController.navigate(route = NavRoute.MainMenu) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<NavRoute.MainMenu> {
            PrefixMainMenuScreen(
                onTopicsSelectionScreenNavigation = { isTwoPlayerMode: Boolean ->
                    navController.navigate(
                        route = NavRoute.TopicsSelection(isTwoPlayerMode = isTwoPlayerMode)
                    )
                },
                onAboutScreenNavigation = { navController.navigate(route = NavRoute.About) },
                onHighScoresScreenNavigation = { navController.navigate(route = NavRoute.HighScores) },
                onExit = { activity?.finishAffinity() }
            )
        }

        composable<NavRoute.TopicsSelection> { backStackEntry ->
            val topicsSelectionRoute: NavRoute.TopicsSelection = backStackEntry.toRoute()
            PrefixTopicsSelectionScreen(
                isTwoPlayerMode = topicsSelectionRoute.isTwoPlayerMode,
                onGameScreenNavigation = { isTwoPlayerMode: Boolean, topicIds: List<Int> ->
                    navController.navigate(
                        route = NavRoute.Game(
                            isTwoPlayerMode = isTwoPlayerMode,
                            topicIds = topicIds,
                        )
                    )
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable<NavRoute.Game> {
            PrefixGameScreen(
                onGameOverNavigation = { result ->
                    navController.navigate(
                        route = NavRoute.GameOverScreen(
                            totalQuestions = result.totalQuestions,
                            correctAnswers = result.player1Correct,
                            player2CorrectAnswers = result.player2Correct,
                            isTwoPlayerMode = result.isTwoPlayerMode,
                        )
                    ) {
                        popUpTo(NavRoute.MainMenu) {
                            inclusive = false
                        }
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable<NavRoute.GameOverScreen> { backStackEntry ->
            val gameOverRoute: NavRoute.GameOverScreen = backStackEntry.toRoute()
            PrefixGameOverScreen(
                totalQuestions = gameOverRoute.totalQuestions,
                player1CorrectAnswers = gameOverRoute.correctAnswers,
                player2CorrectAnswers = gameOverRoute.player2CorrectAnswers,
                isTwoPlayerMode = gameOverRoute.isTwoPlayerMode,
                onMainMenu = {
                    navController.navigate(route = NavRoute.MainMenu) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<NavRoute.About> {
            PrefixAboutScreen(onBackClick = { navController.popBackStack() })
        }

        composable<NavRoute.HighScores> {
            PrefixHighScoresScreen(onBackClick = { navController.popBackStack() })
        }
    }
}