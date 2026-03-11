package com.dbsystel.designsystem.components.badge.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.dbsystel.designsystem.components.core.DBSize
import com.dbsystel.designsystem.components.core.DBSize.MEDIUM
import com.dbsystel.designsystem.components.core.DBSize.SMALL
import com.dbsystel.designsystem.foundation.theme.DBTheme

// region Size
internal val DBSize.dotSize: Dp
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        SMALL -> DBTheme.dimensions.sizing.base3xs
        MEDIUM -> DBTheme.dimensions.sizing.base2xs
    }

internal val DBSize.iconSize: Dp
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        SMALL -> DBTheme.dimensions.sizing.baseXs
        MEDIUM -> DBTheme.dimensions.sizing.baseSm
    }

internal val DBSize.horizontalPadding: Dp
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        SMALL -> DBTheme.dimensions.spacing.fixed2xs
        MEDIUM -> DBTheme.dimensions.spacing.fixedXs
    }

internal val DBSize.paddingFull: Dp
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        SMALL -> DBTheme.dimensions.spacing.fixed3xs
        MEDIUM -> DBTheme.dimensions.spacing.fixed2xs
    }

internal val DBSize.textStyle: TextStyle
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        SMALL -> DBTheme.typography.body2xs
        MEDIUM -> DBTheme.typography.bodySm
    }
// endregion
