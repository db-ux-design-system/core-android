package com.dbsystel.designsystem.components.infotext.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.components.core.DBSize
import com.dbsystel.designsystem.foundation.theme.DBTheme


internal val DBSize.iconSize: Dp
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        DBSize.MEDIUM -> 20.dp
        DBSize.SMALL -> 16.dp
    }

internal val DBSize.textStyle: TextStyle
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        DBSize.MEDIUM -> DBTheme.typography.bodySm
        DBSize.SMALL -> DBTheme.typography.bodyXs
    }
