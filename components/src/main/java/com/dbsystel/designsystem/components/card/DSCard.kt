package com.dbsystel.designsystem.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.foundation.theme.DesignSystemTheme

// The card components are shaded and rounded areas that give the interface depth.
// Whether static or interactive - the cards are the basis for further components and modules and can display content and actions.
@Composable
fun DSCard(
    modifier: Modifier = Modifier,
    elevation: DSCardElevation = DSCardElevation.LEVEL_1,
    spacing: DSSpacing = DSSpacing.SMALL,
    content: @Composable ColumnScope.() -> Unit,
) {
    val spacingValue = when (spacing) {
        DSSpacing.SMALL -> DesignSystemTheme.dimensions.spacing.fixedSm
        DSSpacing.MEDIUM -> DesignSystemTheme.dimensions.spacing.fixedMd
        DSSpacing.LARGE -> DesignSystemTheme.dimensions.spacing.fixedLg
    }

    val elevationColor = when (elevation) {
        DSCardElevation.LEVEL_1 -> DesignSystemTheme.activeColor.bgBasicLevel1Default
        DSCardElevation.LEVEL_2 -> DesignSystemTheme.activeColor.bgBasicLevel2Default
        DSCardElevation.LEVEL_3 -> DesignSystemTheme.activeColor.bgBasicLevel3Default
    }

    val shape = RoundedCornerShape(DesignSystemTheme.dimensions.border.radiusSm)

    Surface(
        color = elevationColor,
        tonalElevation = 0.dp,
        modifier = Modifier
            .background(color = elevationColor, shape = shape)
            .border(
                DesignSystemTheme.dimensions.border.height3xs,
                color = DesignSystemTheme.activeColor.Basic.Border.Default.Default,
                shape = shape
            )
            .padding(spacingValue)
            .then(modifier)
    ) {
        Column(modifier = Modifier, content = content)
    }
}

enum class DSSpacing { SMALL, MEDIUM, LARGE }
enum class DSCardElevation { LEVEL_1, LEVEL_2, LEVEL_3 }

@Composable
@Preview()
fun DSCardPreview() {
    DesignSystemTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            DSCardElevation.entries.forEach { el ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DSSpacing.entries.forEach {
                        DSCard(spacing = it, elevation = el) {
                            Text("Card content")
                        }
                    }
                }
            }
        }
    }
}
