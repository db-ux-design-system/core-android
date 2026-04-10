package com.dbsystel.designsystem.components.switch.extensions

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.components.core.DBSize
import com.dbsystel.designsystem.foundation.theme.DBTheme

// region Thumb
@Composable
internal fun DBSize.animatedThumbSize(checked: Boolean) = animateDpAsState(
    targetValue = when (this) {
        DBSize.MEDIUM -> if (checked) 20.dp else 16.dp
        DBSize.SMALL -> if (checked) 16.dp else 12.dp
    },
    label = "Switch Thumb Size",
)
// endregion

internal val DBSize.textStyle: TextStyle
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        DBSize.MEDIUM -> DBTheme.typography.bodyMd
        DBSize.SMALL -> DBTheme.typography.bodySm
    }