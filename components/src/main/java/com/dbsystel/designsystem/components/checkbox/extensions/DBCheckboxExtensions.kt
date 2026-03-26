package com.dbsystel.designsystem.components.checkbox.extensions

import androidx.compose.animation.animateColorAsState
import androidx.compose.material3.CheckboxColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
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
internal fun DBCheckboxValidation.checkboxColors(
    checked: Boolean,
    indeterminate: Boolean,
    pressed: Boolean,
): CheckboxColors {
    // Used for unchecked or indeterminate checkbox background
    val checkboxBackgroundColor by animateColorAsState(
        targetValue = when {
            pressed -> checkboxColorTransparentPressed
            else -> checkboxColorTransparent
        },
        label = "Checkbox background color animation",
    )

    // Used for border, indeterminate icon and checked background color
    val checkboxColor by animateColorAsState(
        targetValue = when {
            pressed && checked && !indeterminate -> checkboxColorCheckedPressed
            checked && !indeterminate -> checkboxColorCheckedDefault
            else -> checkboxColorDefault
        },
        label = "Checkbox color animation",
    )

    return CheckboxColors(
        checkedCheckmarkColor = if (indeterminate) checkboxColor else checkboxColorInverted,
        uncheckedCheckmarkColor = checkboxColor,
        checkedBoxColor = if (indeterminate) checkboxBackgroundColor else checkboxColor,
        uncheckedBoxColor = checkboxBackgroundColor,
        disabledCheckedBoxColor = checkboxColor,
        disabledUncheckedBoxColor = checkboxBackgroundColor,
        disabledIndeterminateBoxColor = checkboxColor,
        checkedBorderColor = checkboxColor,
        uncheckedBorderColor = checkboxColor,
        disabledBorderColor = checkboxColor,
        disabledUncheckedBorderColor = checkboxColor,
        disabledIndeterminateBorderColor = checkboxColor,
    )
}

@Composable
@ReadOnlyComposable
private fun DBCheckboxValidation.validationColor() = when (this) {
    is DBCheckboxValidation.Invalid -> DBTheme.colors.critical
    is DBCheckboxValidation.Valid -> DBTheme.colors.successful
    DBCheckboxValidation.NoValidation -> DBTheme.activeColor
}

private val DBCheckboxValidation.checkboxColorDefault: Color
    @Composable
    @ReadOnlyComposable
    get() = validationColor().let {
        if (this == DBCheckboxValidation.NoValidation) it.onBgBasicEmphasis100Default
        else it.onBgBasicEmphasis70Default
    }

private val DBCheckboxValidation.checkboxColorCheckedDefault: Color
    @Composable
    @ReadOnlyComposable
    get() = validationColor().let {
        if (this == DBCheckboxValidation.NoValidation) it.bgInvertedContrastMaxDefault
        else it.bgInvertedContrastLowDefault
    }

private val DBCheckboxValidation.checkboxColorCheckedPressed: Color
    @Composable
    @ReadOnlyComposable
    get() = validationColor().let {
        if (this == DBCheckboxValidation.NoValidation) it.bgInvertedContrastMaxPressed
        else it.bgInvertedContrastLowPressed
    }

private val DBCheckboxValidation.checkboxColorInverted: Color
    @Composable
    @ReadOnlyComposable
    get() = validationColor().onBgInvertedDefault

private val DBCheckboxValidation.checkboxColorTransparent: Color
    @Composable
    @ReadOnlyComposable
    get() = validationColor().bgBasicTransparentFullDefault

internal val DBCheckboxValidation.checkboxColorTransparentPressed: Color
    @Composable
    @ReadOnlyComposable
    get() = validationColor().bgBasicTransparentFullPressed

@Composable
internal fun DBCheckboxValidation.textColor(pressed: Boolean): Color {
    return animateColorAsState(
        targetValue = when {
            pressed -> textColorPressed
            else -> textColorDefault
        },
        label = "Text color animation",
    ).value
}

private val DBCheckboxValidation.textColorDefault: Color
    @Composable
    @ReadOnlyComposable
    get() = validationColor().let {
        if (this == DBCheckboxValidation.NoValidation) it.onBgBasicEmphasis100Default
        else it.onBgBasicEmphasis80Default
    }

private val DBCheckboxValidation.textColorPressed: Color
    @Composable
    @ReadOnlyComposable
    get() = validationColor().let {
        if (this == DBCheckboxValidation.NoValidation) it.onBgBasicEmphasis100Pressed
        else it.onBgBasicEmphasis80Pressed
    }
// endregion
