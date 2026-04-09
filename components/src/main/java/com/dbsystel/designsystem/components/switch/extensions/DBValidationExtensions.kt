package com.dbsystel.designsystem.components.switch.extensions

import androidx.compose.animation.animateColorAsState
import androidx.compose.runtime.Composable
import com.dbsystel.designsystem.components.core.DBValidation
import com.dbsystel.designsystem.components.core.extensions.backgroundColorCheckedDefault
import com.dbsystel.designsystem.components.core.extensions.backgroundColorCheckedPressed
import com.dbsystel.designsystem.components.core.extensions.backgroundColorDefault
import com.dbsystel.designsystem.components.core.extensions.backgroundColorInverted
import com.dbsystel.designsystem.components.core.extensions.backgroundColorTransparent
import com.dbsystel.designsystem.components.core.extensions.backgroundColorTransparentPressed

//region Colors
@Composable
internal fun DBValidation.animatedBorderColor(checked: Boolean, pressed: Boolean) =
    animateColorAsState(
        targetValue = when (checked) {
            true if pressed -> backgroundColorCheckedPressed
            true -> backgroundColorCheckedDefault
            else -> backgroundColorDefault
        },
        label = "Switch Border Color",
    )

@Composable
internal fun DBValidation.animatedBackgroundColor(checked: Boolean, pressed: Boolean) =
    animateColorAsState(
        targetValue = when(checked) {
            true if pressed -> backgroundColorCheckedPressed
            true -> backgroundColorCheckedDefault
            false if pressed -> backgroundColorTransparentPressed
            else -> backgroundColorTransparent
        },
        label = "Switch Background Color",
    )

@Composable
internal fun DBValidation.animatedThumbColor(checked: Boolean) = animateColorAsState(
    targetValue = when {
        checked -> backgroundColorInverted
        else -> backgroundColorDefault
    },
    label = "Switch Thumb Color",
)
//endregion
