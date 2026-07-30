package sport.quiz.skeleton.ui.composable.sceens.main_menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import sport.quiz.skeleton.R

@Composable
fun PrefixMainMenuScreen(
    onTopicsSelectionScreenNavigation: (Boolean) -> Unit,
    onAboutScreenNavigation: () -> Unit,
    onHighScoresScreenNavigation: () -> Unit,
    onExit: () -> Unit,
) {
    //[@AGENT][Main menu screen. Display app logo with title at the top, followed by navigation buttons for Single Game, Two Player Mode, High Scores, About, and Exit.]

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MenuButton(
            text = stringResource(R.string.menu_single_game),
            icon = Icons.Default.Person,
            onClick = { onTopicsSelectionScreenNavigation(false) }
        )

        MenuButton(
            text = stringResource(R.string.menu_two_player_mode),
            icon = Icons.Default.People,
            onClick = { onTopicsSelectionScreenNavigation(true) }
        )

        MenuButton(
            text = stringResource(R.string.menu_high_scores),
            icon = Icons.Default.EmojiEvents,
            onClick = onHighScoresScreenNavigation
        )

        MenuButton(
            text = stringResource(R.string.menu_about),
            icon = Icons.Default.Info,
            onClick = onAboutScreenNavigation
        )

        MenuButton(
            text = stringResource(R.string.menu_exit),
            icon = Icons.AutoMirrored.Filled.ExitToApp,
            onClick = onExit,
            isSecondary = true
        )
    }
}

@Composable
fun MenuButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSecondary: Boolean = false
) {
    //[@AGENT][Reusable main menu button. Render full-width button with icon and label, adjusting container colors based on isSecondary flag.]
}