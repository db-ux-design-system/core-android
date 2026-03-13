package com.dbsystel.designsystem.foundation.theme.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalConfiguration
import com.dbsystel.designsystem.foundation.theme.DBColorVariant
import com.dbsystel.designsystem.foundation.theme.DBDimensions
import com.dbsystel.designsystem.foundation.theme.DBDimensions.Companion.getDimensionsExpressiveMobile
import com.dbsystel.designsystem.foundation.theme.DBDimensions.Companion.getDimensionsExpressiveTablet
import com.dbsystel.designsystem.foundation.theme.DBDimensions.Companion.getDimensionsFunctionalMobile
import com.dbsystel.designsystem.foundation.theme.DBDimensions.Companion.getDimensionsFunctionalTablet
import com.dbsystel.designsystem.foundation.theme.DBDimensions.Companion.getDimensionsRegularMobile
import com.dbsystel.designsystem.foundation.theme.DBDimensions.Companion.getDimensionsRegularTablet
import com.dbsystel.designsystem.foundation.theme.DBTheme
import com.dbsystel.designsystem.foundation.theme.LocalActiveColor
import com.dbsystel.designsystem.foundation.theme.LocalDimensions
import com.dbsystel.designsystem.foundation.theme.LocalTheme

@Composable
fun DBAdaptiveLayout(
    color: DBColorVariant = DBTheme.activeColor,
    density: DBDensity = DBDensity.REGULAR,
    content: @Composable () -> Unit,
) {
    val isTablet = LocalConfiguration.current.smallestScreenWidthDp >= 600
    val theme = LocalTheme.current

    val dimensions: DBDimensions = when (isTablet) {
        true ->
            when (density) {
                DBDensity.FUNCTIONAL -> getDimensionsFunctionalTablet(theme.dimensionsMap)
                DBDensity.EXPRESSIVE -> getDimensionsExpressiveTablet(theme.dimensionsMap)
                else -> getDimensionsRegularTablet(theme.dimensionsMap)
            }

        else -> when (density) {
            DBDensity.FUNCTIONAL -> getDimensionsFunctionalMobile(theme.dimensionsMap)
            DBDensity.EXPRESSIVE -> getDimensionsExpressiveMobile(theme.dimensionsMap)
            else -> getDimensionsRegularMobile(theme.dimensionsMap)
        }
    }

    CompositionLocalProvider(
        LocalActiveColor provides color,
        LocalDimensions provides dimensions,
        content = content,
    )
}
