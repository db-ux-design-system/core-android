package com.dbsystel.designsystem.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.foundation.theme.DesignSystemTheme

/**
 * The card components are shaded and rounded areas that give the interface depth.
 * Whether static or interactive - the cards are the basis for further components and modules and can display content and actions.
 *
 * @param modifier - custom modifier of the card
 * @param elevation - set the background color elevation
 * @param spacing - padding of the card
 * @param content - content composable
 */
@Composable
fun DSCard(
    modifier: Modifier = Modifier,
    elevation: DSCardElevation = DSCardElevation.LEVEL_1,
    spacing: DSCardSpacing = DSCardSpacing.SMALL,
    content: @Composable () -> Unit,
) {
    val shape = RoundedCornerShape(DesignSystemTheme.dimensions.border.radiusSm)

    Surface(
        color = elevation.value(),
        tonalElevation = 0.dp,
        modifier = Modifier
            .background(color = elevation.value(), shape = shape)
            .border(
                DesignSystemTheme.dimensions.border.height3xs,
                color = DesignSystemTheme.activeColor.Basic.Border.Default.Default,
                shape = shape
            )
            .padding(spacing.value())
            .then(modifier),
        content = content
    )
}

enum class DSCardSpacing(val value: @Composable () -> Dp) {
    SMALL({ DesignSystemTheme.dimensions.spacing.fixedSm }),
    MEDIUM({ DesignSystemTheme.dimensions.spacing.fixedMd }),
    LARGE({ DesignSystemTheme.dimensions.spacing.fixedLg })
}

enum class DSCardElevation(val value: @Composable () -> Color) {
    LEVEL_1({ DesignSystemTheme.activeColor.bgBasicLevel1Default }),
    LEVEL_2({ DesignSystemTheme.activeColor.bgBasicLevel2Default }),
    LEVEL_3({ DesignSystemTheme.activeColor.bgBasicLevel3Default })
}

private class DSCardPreviewProvider : PreviewParameterProvider<DSCardPreviewParameterType> {
    override val values = DSCardElevation.entries.flatMap { elevation ->
        DSCardSpacing.entries.map { spacing ->
            DSCardPreviewParameterType(spacing = spacing, elevation = elevation)
        }
    }.asSequence()
}

private class DSCardPreviewParameterType(val spacing: DSCardSpacing, val elevation: DSCardElevation)

@Composable
@PreviewLightDark
private fun DSCardPreview(
    @PreviewParameter(DSCardPreviewProvider::class) previewType: DSCardPreviewParameterType,
) {
    DesignSystemTheme {
        DSCard(spacing = previewType.spacing, elevation = previewType.elevation) {
            Text("Card content")
        }
    }
}
