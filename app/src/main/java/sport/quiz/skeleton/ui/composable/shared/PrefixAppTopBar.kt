package sport.quiz.skeleton.ui.composable.shared

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PrefixAppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    actions: @Composable (RowScope.() -> Unit)? = null,
) {
    //[@AGENT][Reusable top navigation bar. Render back navigation button, screen title text, and optional trailing action icons.]
}