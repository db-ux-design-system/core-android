package com.dbsystel.designsystem.components.core

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

fun Modifier.withAlphaForDisabledState(disabled: Boolean): Modifier = this.then(
    if (!disabled) {
        Modifier.alpha(1f)
    } else {
        Modifier.alpha(0.4f)
    }
)
