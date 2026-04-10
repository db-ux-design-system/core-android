package com.dbsystel.designsystem.components.core.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.components.core.DBSize
import com.dbsystel.designsystem.components.core.DBSize.MEDIUM
import com.dbsystel.designsystem.components.core.DBSize.SMALL
import com.dbsystel.designsystem.foundation.theme.DBTheme


internal val DBSize.textStyle: TextStyle
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        MEDIUM -> DBTheme.typography.bodyMd
        SMALL -> DBTheme.typography.bodySm
    }

internal val DBSize.baseSize: Dp
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        MEDIUM -> 24.dp
        SMALL -> 20.dp
    }

internal val DBSize.spacing: Dp
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        MEDIUM -> DBTheme.dimensions.spacing.fixedXs
        SMALL -> DBTheme.dimensions.spacing.fixed2xs
    }
