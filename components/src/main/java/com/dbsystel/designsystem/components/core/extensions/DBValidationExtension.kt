package com.dbsystel.designsystem.components.core.extensions

import androidx.compose.animation.animateColorAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import com.dbsystel.designsystem.components.core.DBValidation
import com.dbsystel.designsystem.foundation.theme.DBTheme

@Composable
@ReadOnlyComposable
private fun DBValidation.validationColor() = when (this) {
    is DBValidation.Invalid -> DBTheme.colors.critical
    is DBValidation.Valid -> DBTheme.colors.successful
    DBValidation.NoValidation -> DBTheme.activeColor
}

// region Background colors
internal val DBValidation.backgroundColorDefault: Color
    @Composable
    @ReadOnlyComposable
    get() = validationColor().let {
        if (this == DBValidation.NoValidation) it.onBgBasicEmphasis100Default
        else it.onBgBasicEmphasis70Default
    }

internal val DBValidation.backgroundColorCheckedDefault: Color
    @Composable
    @ReadOnlyComposable
    get() = validationColor().let {
        if (this == DBValidation.NoValidation) it.bgInvertedContrastMaxDefault
        else it.bgInvertedContrastLowDefault
    }

internal val DBValidation.backgroundColorCheckedPressed: Color
    @Composable
    @ReadOnlyComposable
    get() = validationColor().let {
        if (this == DBValidation.NoValidation) it.bgInvertedContrastMaxPressed
        else it.bgInvertedContrastLowPressed
    }

internal val DBValidation.backgroundColorInverted: Color
    @Composable
    @ReadOnlyComposable
    get() = validationColor().onBgInvertedDefault

internal val DBValidation.backgroundColorTransparent: Color
    @Composable
    @ReadOnlyComposable
    get() = validationColor().bgBasicTransparentFullDefault

internal val DBValidation.backgroundColorTransparentPressed: Color
    @Composable
    @ReadOnlyComposable
    get() = validationColor().bgBasicTransparentFullPressed
// endregion

// region Text colors
@Composable
internal fun DBValidation.textColor(pressed: Boolean): Color {
    return animateColorAsState(
        targetValue = when {
            pressed -> textColorPressed
            else -> textColorDefault
        },
        label = "Text color animation",
    ).value
}

private val DBValidation.textColorDefault: Color
    @Composable
    @ReadOnlyComposable
    get() = validationColor().let {
        if (this == DBValidation.NoValidation) it.onBgBasicEmphasis100Default
        else it.onBgBasicEmphasis80Default
    }

private val DBValidation.textColorPressed: Color
    @Composable
    @ReadOnlyComposable
    get() = validationColor().let {
        if (this == DBValidation.NoValidation) it.onBgBasicEmphasis100Pressed
        else it.onBgBasicEmphasis80Pressed
    }
//endregion
