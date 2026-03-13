package com.dbsystel.designsystem.components.card.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.components.card.DBCardElevation
import com.dbsystel.designsystem.components.card.DBCardElevation.LEVEL_1
import com.dbsystel.designsystem.components.card.DBCardElevation.LEVEL_2
import com.dbsystel.designsystem.components.card.DBCardElevation.LEVEL_3
import com.dbsystel.designsystem.components.card.DBCardSpacing
import com.dbsystel.designsystem.components.card.DBCardSpacing.LARGE
import com.dbsystel.designsystem.components.card.DBCardSpacing.MEDIUM
import com.dbsystel.designsystem.components.card.DBCardSpacing.NONE
import com.dbsystel.designsystem.components.card.DBCardSpacing.SMALL
import com.dbsystel.designsystem.foundation.theme.DBTheme

// region Spacing
internal val DBCardSpacing.padding: Dp
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        SMALL -> DBTheme.dimensions.spacing.fixedSm
        MEDIUM -> DBTheme.dimensions.spacing.fixedMd
        LARGE -> DBTheme.dimensions.spacing.fixedLg
        NONE -> 0.dp
    }
// endregion

// region Elevation
internal val DBCardElevation.color: Color
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        LEVEL_1 -> DBTheme.activeColor.bgBasicLevel1Default
        LEVEL_2 -> DBTheme.activeColor.bgBasicLevel2Default
        LEVEL_3 -> DBTheme.activeColor.bgBasicLevel3Default
    }
// endregion
