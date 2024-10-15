package com.dbsystel.designsystem.foundation.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.core.view.WindowCompat
import com.dbsystel.designsystem.foundation.theme.core.Density
import com.dbsystel.designsystem.foundation.theme.deutschebahn.DeutscheBahnTheme


object DesignSystemTheme {
    val colors: DesignSystemColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val activeColor: AdaptiveColors
        @Composable
        @ReadOnlyComposable
        get() = LocalActiveColor.current

    val dimensions: DesignSystemDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current

    val typography: DesignSystemTextStyles
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

interface Theme {
    val colorMap: Map<String, Color>
    val dimensionsMap: Map<String, Dp>
    val typographyMap: Map<String, TextUnit>
}

internal val LocalTheme = staticCompositionLocalOf<Theme> { DeutscheBahnTheme }

@Composable
fun DesignSystemTheme(
    theme: Theme = DeutscheBahnTheme,
    density: Density = Density.REGULAR,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val configuration = LocalConfiguration.current
    // typography
    val typography: DesignSystemTextStyles = when {
        configuration.screenWidthDp > 768 ->
            when (density) {
                Density.FUNCTIONAL -> getTextStyles(getTypographyFunctionalTablet(theme.typographyMap))
                Density.EXPRESSIVE -> getTextStyles(getTypographyExpressiveTablet(theme.typographyMap))
                else -> getTextStyles(getTypographyRegularTablet(theme.typographyMap))
            }

        else -> when (density) {
            Density.FUNCTIONAL -> getTextStyles(getTypographyFunctionalMobile(theme.typographyMap))
            Density.EXPRESSIVE -> getTextStyles(getTypographyExpressiveMobile(theme.typographyMap))
            else -> getTextStyles(getTypographyRegularMobile(theme.typographyMap))
        }
    }

    // screen
    val dimensions: DesignSystemDimensions = when {
        configuration.screenWidthDp > 768 ->
            when (density) {
                Density.FUNCTIONAL -> getDimensionsFunctionalTablet(theme.dimensionsMap)
                Density.EXPRESSIVE -> getDimensionsExpressiveTablet(theme.dimensionsMap)
                else -> getDimensionsRegularTablet(theme.dimensionsMap)
            }

        else -> when (density) {
            Density.FUNCTIONAL -> getDimensionsFunctionalMobile(theme.dimensionsMap)
            Density.EXPRESSIVE -> getDimensionsExpressiveMobile(theme.dimensionsMap)
            else -> getDimensionsRegularMobile(theme.dimensionsMap)
        }
    }

    // colors
    val colorScheme: DesignSystemColorScheme = when {
        darkTheme -> getColorSchemeDark(theme.colorMap)
        else -> getColorSchemeLight(theme.colorMap)
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.neutral.bgBasicLevel1Default.toArgb()
            window.navigationBarColor = colorScheme.neutral.bgBasicLevel1Default.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    CompositionLocalProvider(
        LocalTheme provides theme,
        LocalColors provides colorScheme,
        LocalDimensions provides dimensions,
        LocalTypography provides typography
    ) {
        content()
    }
}
