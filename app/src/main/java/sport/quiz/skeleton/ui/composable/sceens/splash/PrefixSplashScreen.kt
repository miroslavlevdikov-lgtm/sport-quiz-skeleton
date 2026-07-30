package sport.quiz.skeleton.ui.composable.sceens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun PrefixSplashScreen(
    onMainMenuScreenNavigation: () -> Unit,
) {
    val scale = remember { Animatable(0f) }
    val rotation = remember { Animatable(0f) }
    val textAlpha = remember { Animatable(0f) }
    val textTranslationX = remember { Animatable(-50f) }

    //[@AGENT][Splash screen component. Run parallel enter animations for logo scale/rotation and app title alpha/slide-in, then trigger onMainMenuScreenNavigation after delay. Render logo and title centered inside a full-screen Box.]
}