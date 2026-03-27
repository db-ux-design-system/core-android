package com.dbsystel.designsystem.components.checkbox.extensions

import androidx.compose.animation.animateColorAsState
import androidx.compose.material3.CheckboxColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.components.core.DBSize
import com.dbsystel.designsystem.components.core.DBValidation
import com.dbsystel.designsystem.components.core.extensions.backgroundColorCheckedDefault
import com.dbsystel.designsystem.components.core.extensions.backgroundColorCheckedPressed
import com.dbsystel.designsystem.components.core.extensions.backgroundColorDefault
import com.dbsystel.designsystem.components.core.extensions.backgroundColorInverted
import com.dbsystel.designsystem.components.core.extensions.backgroundColorTransparent
import com.dbsystel.designsystem.components.core.extensions.backgroundColorTransparentPressed
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


@Composable
internal fun DBValidation.checkboxColors(
    checked: Boolean,
    indeterminate: Boolean,
    pressed: Boolean,
): CheckboxColors {
    // Used for unchecked or indeterminate checkbox background
    val checkboxBackgroundColor by animateColorAsState(
        targetValue = when {
            pressed -> backgroundColorTransparentPressed
            else -> backgroundColorTransparent
        },
        label = "Checkbox background color animation",
    )

    // Used for border, indeterminate icon and checked background color
    val checkboxColor by animateColorAsState(
        targetValue = when {
            pressed && checked && !indeterminate -> backgroundColorCheckedPressed
            checked && !indeterminate -> backgroundColorCheckedDefault
            else -> backgroundColorDefault
        },
        label = "Checkbox color animation",
    )

    return CheckboxColors(
        checkedCheckmarkColor = if (indeterminate) checkboxColor else backgroundColorInverted,
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
