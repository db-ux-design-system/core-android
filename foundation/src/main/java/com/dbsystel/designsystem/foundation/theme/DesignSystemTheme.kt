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
import com.dbsystel.designsystem.foundation.theme.DesignSystemColorScheme.Companion.getColorSchemeDark
import com.dbsystel.designsystem.foundation.theme.DesignSystemColorScheme.Companion.getColorSchemeLight
import com.dbsystel.designsystem.foundation.theme.DesignSystemDimensions.Companion.getDimensionsExpressiveMobile
import com.dbsystel.designsystem.foundation.theme.DesignSystemDimensions.Companion.getDimensionsExpressiveTablet
import com.dbsystel.designsystem.foundation.theme.DesignSystemDimensions.Companion.getDimensionsFunctionalMobile
import com.dbsystel.designsystem.foundation.theme.DesignSystemDimensions.Companion.getDimensionsFunctionalTablet
import com.dbsystel.designsystem.foundation.theme.DesignSystemDimensions.Companion.getDimensionsRegularMobile
import com.dbsystel.designsystem.foundation.theme.DesignSystemDimensions.Companion.getDimensionsRegularTablet
import com.dbsystel.designsystem.foundation.theme.DesignSystemTextStyles.Companion.getTextStyles
import com.dbsystel.designsystem.foundation.theme.DesignSystemTypography.Companion.getTypographyExpressiveMobile
import com.dbsystel.designsystem.foundation.theme.DesignSystemTypography.Companion.getTypographyExpressiveTablet
import com.dbsystel.designsystem.foundation.theme.DesignSystemTypography.Companion.getTypographyFunctionalMobile
import com.dbsystel.designsystem.foundation.theme.DesignSystemTypography.Companion.getTypographyFunctionalTablet
import com.dbsystel.designsystem.foundation.theme.DesignSystemTypography.Companion.getTypographyRegularMobile
import com.dbsystel.designsystem.foundation.theme.DesignSystemTypography.Companion.getTypographyRegularTablet
import com.dbsystel.designsystem.foundation.theme.core.DSDensity
import com.dbsystel.designsystem.foundation.theme.deutschebahn.DeutscheBahnTheme


object DesignSystemTheme {
    val colors: DesignSystemColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val activeColor: DSColorVariant
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

interface DSTheme {
    val colorMap: Map<String, Color>
    val dimensionsMap: Map<String, Dp>
    val typographyMap: Map<String, TextUnit>
}

internal val LocalTheme = staticCompositionLocalOf<DSTheme> { DeutscheBahnTheme }

@Composable
fun DesignSystemTheme(
    theme: DSTheme = DeutscheBahnTheme,
    density: DSDensity = DSDensity.REGULAR,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val isTablet = LocalConfiguration.current.screenWidthDp > 768
    // typography
    val typography: DesignSystemTextStyles = when (isTablet) {
        true ->
            when (density) {
                DSDensity.FUNCTIONAL -> getTextStyles(getTypographyFunctionalTablet(theme.typographyMap))
                DSDensity.EXPRESSIVE -> getTextStyles(getTypographyExpressiveTablet(theme.typographyMap))
                else -> getTextStyles(getTypographyRegularTablet(theme.typographyMap))
            }

        else -> when (density) {
            DSDensity.FUNCTIONAL -> getTextStyles(getTypographyFunctionalMobile(theme.typographyMap))
            DSDensity.EXPRESSIVE -> getTextStyles(getTypographyExpressiveMobile(theme.typographyMap))
            else -> getTextStyles(getTypographyRegularMobile(theme.typographyMap))
        }
    }

    // screen
    val dimensions: DesignSystemDimensions = when (isTablet) {
        true ->
            when (density) {
                DSDensity.FUNCTIONAL -> getDimensionsFunctionalTablet(theme.dimensionsMap)
                DSDensity.EXPRESSIVE -> getDimensionsExpressiveTablet(theme.dimensionsMap)
                else -> getDimensionsRegularTablet(theme.dimensionsMap)
            }

        else -> when (density) {
            DSDensity.FUNCTIONAL -> getDimensionsFunctionalMobile(theme.dimensionsMap)
            DSDensity.EXPRESSIVE -> getDimensionsExpressiveMobile(theme.dimensionsMap)
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
        LocalTypography provides typography,
    ) {
        content()
    }
}
