package com.dbsystel.designsystem.foundation.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.core.view.WindowCompat
import com.dbsystel.designsystem.foundation.theme.DBColorScheme.Companion.getColorSchemeDark
import com.dbsystel.designsystem.foundation.theme.DBColorScheme.Companion.getColorSchemeLight
import com.dbsystel.designsystem.foundation.theme.DBDimensions.Companion.getDimensionsExpressiveMobile
import com.dbsystel.designsystem.foundation.theme.DBDimensions.Companion.getDimensionsExpressiveTablet
import com.dbsystel.designsystem.foundation.theme.DBDimensions.Companion.getDimensionsFunctionalMobile
import com.dbsystel.designsystem.foundation.theme.DBDimensions.Companion.getDimensionsFunctionalTablet
import com.dbsystel.designsystem.foundation.theme.DBDimensions.Companion.getDimensionsRegularMobile
import com.dbsystel.designsystem.foundation.theme.DBDimensions.Companion.getDimensionsRegularTablet
import com.dbsystel.designsystem.foundation.theme.DBTextStyles.Companion.getTextStyles
import com.dbsystel.designsystem.foundation.theme.DBTypography.Companion.getTypographyExpressiveMobile
import com.dbsystel.designsystem.foundation.theme.DBTypography.Companion.getTypographyExpressiveTablet
import com.dbsystel.designsystem.foundation.theme.DBTypography.Companion.getTypographyFunctionalMobile
import com.dbsystel.designsystem.foundation.theme.DBTypography.Companion.getTypographyFunctionalTablet
import com.dbsystel.designsystem.foundation.theme.DBTypography.Companion.getTypographyRegularMobile
import com.dbsystel.designsystem.foundation.theme.DBTypography.Companion.getTypographyRegularTablet
import com.dbsystel.designsystem.foundation.theme.core.DBDensity
import com.dbsystel.designsystem.foundation.theme.db.DBBrandTheme


object DBTheme {
    val colors: DBColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val activeColor: DBColorVariant
        @Composable
        @ReadOnlyComposable
        get() = LocalActiveColor.current

    val dimensions: DBDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current

    val typography: DBTextStyles
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

interface DBThemeMaps {
    val colorMap: Map<String, Color>
    val dimensionsMap: Map<String, Dp>
    val typographyMap: Map<String, TextUnit>
}

internal val LocalTheme = staticCompositionLocalOf<DBThemeMaps> { DBBrandTheme }

@Composable
fun DBTheme(
    theme: DBThemeMaps = DBBrandTheme,
    density: DBDensity = DBDensity.REGULAR,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val isTablet = LocalConfiguration.current.smallestScreenWidthDp >= 600
    // typography
    val typography: DBTextStyles = remember(isTablet, theme) {
        when (isTablet) {
            true ->
                when (density) {
                    DBDensity.FUNCTIONAL -> getTextStyles(getTypographyFunctionalTablet(theme.typographyMap))
                    DBDensity.EXPRESSIVE -> getTextStyles(getTypographyExpressiveTablet(theme.typographyMap))
                    else -> getTextStyles(getTypographyRegularTablet(theme.typographyMap))
                }

            else -> when (density) {
                DBDensity.FUNCTIONAL -> getTextStyles(getTypographyFunctionalMobile(theme.typographyMap))
                DBDensity.EXPRESSIVE -> getTextStyles(getTypographyExpressiveMobile(theme.typographyMap))
                else -> getTextStyles(getTypographyRegularMobile(theme.typographyMap))
            }
        }
    }

    // screen
    val dimensions: DBDimensions = remember(isTablet, theme) {
        when (isTablet) {
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
    }

    // colors
    val colorScheme: DBColorScheme = remember(darkTheme, theme) {
        when {
            darkTheme -> getColorSchemeDark(theme.colorMap)
            else -> getColorSchemeLight(theme.colorMap)
        }
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    CompositionLocalProvider(
        LocalTheme provides theme,
        LocalColors provides colorScheme,
        LocalActiveColor provides colorScheme.neutral,
        LocalDimensions provides dimensions,
        LocalTypography provides typography,
    ) {
        content()
    }
}
