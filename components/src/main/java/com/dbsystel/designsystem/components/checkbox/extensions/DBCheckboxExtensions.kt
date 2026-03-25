package com.dbsystel.designsystem.components.checkbox.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.components.checkbox.DBCheckboxValidation
import com.dbsystel.designsystem.components.core.DBSize
import com.dbsystel.designsystem.foundation.theme.DBTheme

internal val DBSize.checkboxSize: Dp
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        DBSize.MEDIUM -> 24.dp
        DBSize.SMALL -> 20.dp
    }

internal val DBSize.spacing: Dp
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        DBSize.MEDIUM -> DBTheme.dimensions.spacing.fixedXs
        DBSize.SMALL -> DBTheme.dimensions.spacing.fixed2xs
    }

internal val DBSize.textStyle: TextStyle
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        DBSize.MEDIUM -> DBTheme.typography.bodyMd
        DBSize.SMALL -> DBTheme.typography.bodySm
    }

// region Colors
@Composable
@ReadOnlyComposable
private fun DBCheckboxValidation.validationColor() = when (this) {
    is DBCheckboxValidation.Invalid -> DBTheme.colors.critical
    is DBCheckboxValidation.Valid -> DBTheme.colors.successful
    DBCheckboxValidation.NoValidation -> DBTheme.activeColor
}

internal val DBCheckboxValidation.checkboxColor: Color
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        is DBCheckboxValidation.Invalid -> DBTheme.colors.critical.onBgBasicEmphasis70Default
        is DBCheckboxValidation.Valid -> DBTheme.colors.successful.onBgBasicEmphasis70Default
        DBCheckboxValidation.NoValidation -> DBTheme.activeColor.onBgBasicEmphasis100Default
    }

internal val DBCheckboxValidation.checkboxInvertedColor: Color
    @Composable
    @ReadOnlyComposable
    get() = validationColor().onBgInvertedDefault

internal val DBCheckboxValidation.checkboxTransparentColor: Color
    @Composable
    @ReadOnlyComposable
    get() = validationColor().bgBasicTransparentFullDefault

internal val DBCheckboxValidation.checkboxTransparentPressedColor: Color
    @Composable
    @ReadOnlyComposable
    get() = validationColor().bgBasicTransparentFullPressed

internal val DBCheckboxValidation.textColor: Color
    @Composable
    @ReadOnlyComposable
    get() = validationColor().let {
        if (this == DBCheckboxValidation.NoValidation) it.onBgBasicEmphasis100Default
        else it.onBgBasicEmphasis80Default
    }
// endregion
