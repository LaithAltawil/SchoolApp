package com.example.schoolapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo

fun Modifier.safeClick(
    enabled: Boolean = true,
    onCancelled: () -> Unit = {},
    onClick: () -> Unit
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "safeClick"
        properties["enabled"] = enabled
    }
) {
    val interactionSource = remember { MutableInteractionSource() }
    clickable(
        interactionSource = interactionSource,
        indication = null,
        enabled = enabled,
        onClick = {
            try {
                onClick()
            } catch (e: Exception) {
                onCancelled()
            }
        }
    )
}