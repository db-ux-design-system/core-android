package com.dbsystel.designsystem.components.button.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.components.button.DBButtonVariant
import com.dbsystel.designsystem.components.button.DBButtonVariant.BRAND
import com.dbsystel.designsystem.components.button.DBButtonVariant.FILLED
import com.dbsystel.designsystem.components.button.DBButtonVariant.GHOST
import com.dbsystel.designsystem.components.button.DBButtonVariant.OUTLINED
import com.dbsystel.designsystem.components.core.DBSize
import com.dbsystel.designsystem.components.core.DBSize.MEDIUM
import com.dbsystel.designsystem.components.core.DBSize.SMALL
import com.dbsystel.designsystem.foundation.theme.DBTheme

// region Variant
internal val DBButtonVariant.background: Color
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        OUTLINED,
        GHOST,
            -> DBTheme.activeColor.Basic.Background.Transparent.Full

        FILLED -> DBTheme.activeColor.Basic.Background.Transparent.Semi
        BRAND -> DBTheme.colors.brand.Origin.Default
    }

internal val DBButtonVariant.color: Color
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        OUTLINED,
        GHOST,
        FILLED,
            -> DBTheme.activeColor.Basic.Text.Default.Default

        BRAND -> DBTheme.colors.brand.OnOrigin
    }
internal val DBButtonVariant.hasBorder: Boolean
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        OUTLINED -> true
        GHOST,
        FILLED,
        BRAND,
            -> false
    }
// endregion

// region Size
internal val DBSize.size: Dp
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        MEDIUM -> DBTheme.dimensions.sizing.baseMd
        SMALL -> DBTheme.dimensions.sizing.baseSm
    }

internal val DBSize.paddingFull: Dp
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        MEDIUM -> DBTheme.dimensions.spacing.fixedXs
        SMALL -> DBTheme.dimensions.spacing.fixed3xs
    }

internal val DBSize.paddingH: Dp
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        MEDIUM -> DBTheme.dimensions.spacing.fixedMd
        SMALL -> DBTheme.dimensions.spacing.fixedSm
    }

internal val DBSize.spacing: Dp
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        MEDIUM -> DBTheme.dimensions.spacing.fixedXs
        SMALL -> DBTheme.dimensions.spacing.fixed2xs
    }

internal val DBSize.iconSize: Dp
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        MEDIUM -> 24.dp
        SMALL -> 20.dp
    }

internal val DBSize.textStyle: TextStyle
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        MEDIUM -> DBTheme.typography.bodyMd
        SMALL -> DBTheme.typography.bodySm
    }
// endregion
