package com.example.schoolapp.Presentation.Screens.ScreensPieces

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.schoolapp.Presentation.VM.MainViewModel
import androidx.compose.animation.core.LinearEasing
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(viewModel: MainViewModel, modifier: Modifier, Title: String) {
    val state by viewModel.Marksstate.collectAsState()
    AnimatedVisibility(
        visible = state.isTopBarVisible,
        enter = slideInVertically(
            animationSpec = tween(durationMillis = 300),
            initialOffsetY = { -50 }
        ),
        exit = slideOutVertically(
            animationSpec = tween(durationMillis = 900, easing = LinearEasing),
            targetOffsetY = { it }
        )
    ) {
        LargeTopAppBar(
            title = { Text(Title, fontStyle = MaterialTheme.typography.titleLarge.fontStyle, fontSize = 50.sp) },
            modifier = modifier,
            colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
            ),
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null,
                        modifier = Modifier.size(80.dp), tint = MaterialTheme.colorScheme.background)

                }

            }
        )
    }
}