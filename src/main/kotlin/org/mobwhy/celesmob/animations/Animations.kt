package org.mobwhy.celesmob.animations

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

// Common animation specs
val DefaultSpringSpec = spring<Float>(
    dampingRatio = Spring.DampingRatioMediumBouncy,
    stiffness = Spring.StiffnessLow
)

val FastSpringSpec = spring<Float>(
    dampingRatio = Spring.DampingRatioHighBouncy,
    stiffness = Spring.StiffnessMedium
)

val SlowSpringSpec = spring<Float>(
    dampingRatio = Spring.DampingRatioNoBouncy,
    stiffness = Spring.StiffnessLow
)

// Fade in/out animations
@Composable
fun FadeInTransition(content: @Composable () -> Unit) {
    AnimatedContent(targetState = content) { targetContent ->
        targetContent()
    }
}

// Slide in/out animations
@Composable
fun SlideInTransition(content: @Composable () -> Unit) {
    AnimatedContent(targetState = content) { targetContent ->
        targetContent()
    }
}

// Scale animations for interactive elements
@Composable
fun ScaleAnimatedContent(
    targetState: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedContent(targetState = targetState, modifier = modifier) { targetContent ->
        targetContent()
    }
}

// Pulse animation for loading indicators
@Composable
fun PulseAnimation(
    content: @Composable () -> Unit,
    durationMillis: Int = 1500
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_scale"
    )

    content()
}

// Expand/collapse animation for cards
@Composable
fun ExpandCollapseCard(
    expanded: Boolean,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = expanded,
        enter = expandVertically(
            animationSpec = tween(300)
        ) + fadeIn(
            animationSpec = tween(200)
        ),
        exit = shrinkVertically(
            animationSpec = tween(200)
        ) + fadeOut(
            animationSpec = tween(150)
        )
    ) {
        content()
    }
}

// Hover effect animation
@Composable
fun HoverScaleAnimation(
    enabled: Boolean = true,
    scaleValue: Float = 1.05f,
    content: @Composable (animatedScale: Float) -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = if (enabled) scaleValue else 1f,
        animationSpec = tween(200),
        label = "hover_scale"
    )

    content(scale)
}

// Page transition animation
@Composable
fun PageTransition(
    targetState: @Composable () -> Unit
) {
    AnimatedContent(targetState = targetState) { currentState ->
        currentState()
    }
}