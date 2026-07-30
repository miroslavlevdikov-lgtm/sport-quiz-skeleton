package sport.quiz.skeleton.ui.composable.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import sport.quiz.skeleton.R

@Composable
fun ConfirmationDialog(
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    confirmText: String = stringResource(R.string.dialog_action_confirm),
    dismissText: String = stringResource(R.string.dialog_action_cancel),
) {
    //[@AGENT][Reusable modal confirmation dialog. Display title, message body, confirm button, and dismiss/cancel button using Material3 AlertDialog.]
}
