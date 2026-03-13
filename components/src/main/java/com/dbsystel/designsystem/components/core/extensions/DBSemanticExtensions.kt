package com.dbsystel.designsystem.components.core.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.dbsystel.designsystem.components.R
import com.dbsystel.designsystem.components.core.DBEmphasis
import com.dbsystel.designsystem.components.core.DBEmphasis.STRONG
import com.dbsystel.designsystem.components.core.DBSemantic
import com.dbsystel.designsystem.components.core.DBSemantic.ADAPTIVE
import com.dbsystel.designsystem.components.core.DBSemantic.CRITICAL
import com.dbsystel.designsystem.components.core.DBSemantic.INFORMATIONAL
import com.dbsystel.designsystem.components.core.DBSemantic.NEUTRAL
import com.dbsystel.designsystem.components.core.DBSemantic.SUCCESSFUL
import com.dbsystel.designsystem.components.core.DBSemantic.WARNING
import com.dbsystel.designsystem.foundation.theme.DBTheme


// region Colors
@Composable
@ReadOnlyComposable
internal fun DBSemantic.backgroundColor(emphasis: DBEmphasis? = null): Color =
    when (this) {
        ADAPTIVE -> DBTheme.activeColor
        NEUTRAL -> DBTheme.colors.neutral
        CRITICAL -> DBTheme.colors.critical
        INFORMATIONAL -> DBTheme.colors.informational
        WARNING -> DBTheme.colors.warning
        SUCCESSFUL -> DBTheme.colors.successful
    }.let {
        if (emphasis == STRONG) it.bgVibrantDefault
        else it.Basic.Background.Level3.Default
    }


@Composable
@ReadOnlyComposable
internal fun DBSemantic.iconColor(emphasis: DBEmphasis? = null): Color =
    when (this) {
        ADAPTIVE -> DBTheme.activeColor
        NEUTRAL -> DBTheme.colors.neutral
        CRITICAL -> DBTheme.colors.critical
        INFORMATIONAL -> DBTheme.colors.informational
        WARNING -> DBTheme.colors.warning
        SUCCESSFUL -> DBTheme.colors.successful
    }.let {
        if (emphasis == STRONG) it.onBgVibrantDefault
        else it.Basic.Icon.Emphasis100.Default
    }

@Composable
@ReadOnlyComposable
internal fun DBSemantic.textColor(emphasis: DBEmphasis? = null): Color =
    when (this) {
        ADAPTIVE -> DBTheme.activeColor
        NEUTRAL -> DBTheme.colors.neutral
        CRITICAL -> DBTheme.colors.critical
        INFORMATIONAL -> DBTheme.colors.informational
        WARNING -> DBTheme.colors.warning
        SUCCESSFUL -> DBTheme.colors.successful
    }.let {
        if (emphasis == STRONG) it.onBgVibrantDefault
        else it.Basic.Text.Emphasis80.Default
    }

internal val DBSemantic.borderColor: Color
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        ADAPTIVE -> DBTheme.activeColor
        NEUTRAL -> DBTheme.colors.neutral
        CRITICAL -> DBTheme.colors.critical
        INFORMATIONAL -> DBTheme.colors.informational
        WARNING -> DBTheme.colors.warning
        SUCCESSFUL -> DBTheme.colors.successful
    }.Basic.Border.Emphasis70.Default
// endregion

// region Icons
internal val DBSemantic.icon: ImageVector
    @Composable
    get() = when (this) {
        ADAPTIVE, NEUTRAL, INFORMATIONAL -> R.drawable.dbux_ic_info
        CRITICAL -> R.drawable.dbux_ic_critical
        WARNING -> R.drawable.dbux_ic_warning
        SUCCESSFUL -> R.drawable.dbux_ic_successful
    }.let { ImageVector.vectorResource(id = it) }
// endregion
