package com.dbsystel.designsystem.components.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.foundation.R
import com.dbsystel.designsystem.foundation.theme.DesignSystemTheme

/**
 * Buttons are a fundamental element in UI design and are used to prompt users to interact
 * with the user interface and trigger an action.
 *
 * @param modifier - custom modifier of the button
 * @param enabled - enable state
 * @param onClick - callback when button clicked
 * @param text - text to display
 * @param icon - icon to display
 * @param iconContentDescription - content description of the icon
 * @param size - size of the button
 * @param variant - variant of the button
 * @param width - width-setting of the button
 * @param showIcon - control the visibility of the icon
 * @param noText - control the visibility of the text
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DSButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String? = null,
    icon: DSButtonIcon? = null,
    iconContentDescription: String = "Icon",
    size: DSButtonSize = DSButtonSize.MEDIUM,
    variant: DSButtonVariant = DSButtonVariant.OUTLINED,
    width: DSButtonWidth = DSButtonWidth.AUTO,
    showIcon: Boolean = false,
    noText: Boolean = false,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(size = DesignSystemTheme.dimensions.border.radiusXs)
    val iconOnly = icon != null && text.isNullOrBlank()
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    Button(
        border = if (variant.hasBorder) BorderStroke(
            width = DesignSystemTheme.dimensions.border.height3xs,
            color = DesignSystemTheme.activeColor.onBgBasicEmphasis100Default
        ) else null,
        shape = shape,
        modifier = Modifier
            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
            .padding(0.dp)
            .height(size.size())
            .then(if (width == DSButtonWidth.FULL_WIDTH) Modifier.fillMaxWidth() else Modifier)
            .then(if (enabled) Modifier.alpha(1.0f) else Modifier.alpha(0.4f))
            .then(modifier),
        onClick = onClick,
        enabled = enabled,
        colors = ButtonColors(
            contentColor = variant.color(),
            containerColor = if (pressed) DesignSystemTheme.activeColor.Basic.Background.Transparent.Pressed else variant.background(),
            disabledContentColor = variant.color(),
            disabledContainerColor = variant.background(),
        ),
        contentPadding = if (!iconOnly) {
            PaddingValues(horizontal = size.paddingH(), vertical = 0.dp)
        } else {
            PaddingValues(all = size.paddingFull())
        },
        interactionSource = interactionSource
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(if (!iconOnly) size.spacing() else 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null && showIcon) {
                if (icon.iconRes != null) {
                    Icon(
                        modifier = Modifier.size(size.iconSize()),
                        painter = painterResource(id = icon.iconRes),
                        contentDescription = icon.contentDescription,
                        tint = variant.color()
                    )
                } else if (icon.imageVector != null) {
                    Icon(
                        modifier = Modifier.size(size.iconSize()),
                        imageVector = icon.imageVector,
                        contentDescription = icon.contentDescription,
                        tint = variant.color()
                    )
                }
            }
            if (text != null && !noText) Text(
                text = text,
                style = TextStyle(
                    fontSize = size.textSize(),
                    fontWeight = FontWeight.Bold,
                    lineHeight = size.lineHeight(),
                ),
                color = variant.color()
            )
        }
    }
}

data class DSButtonIcon(
    @DrawableRes val iconRes: Int? = null,
    val imageVector: ImageVector? = null,
    val contentDescription: String? = null
)

enum class DSButtonVariant(
    val background: @Composable () -> Color,
    val color: @Composable () -> Color,
    val hasBorder: Boolean,
) {
    OUTLINED(
        background = { DesignSystemTheme.activeColor.Basic.Background.Transparent.Full },
        color = { DesignSystemTheme.activeColor.Basic.Text.Emphasis100.Default },
        hasBorder = true,
    ),
    FILLED(
        background = { DesignSystemTheme.activeColor.Basic.Background.Transparent.Semi },
        color = { DesignSystemTheme.activeColor.Basic.Text.Emphasis100.Default },
        hasBorder = false,
    ),
    GHOST(
        background = { DesignSystemTheme.activeColor.Basic.Background.Transparent.Full },
        color = { DesignSystemTheme.activeColor.Basic.Text.Emphasis100.Default },
        hasBorder = false,
    ),
    BRAND(
        background = { DesignSystemTheme.colors.brand.Origin.Default },
        color = { DesignSystemTheme.colors.brand.OnOrigin.Default },
        hasBorder = false,
    ),
}

enum class DSButtonSize(
    val size: @Composable () -> Dp,
    val paddingFull: @Composable () -> Dp,
    val paddingH: @Composable () -> Dp,
    val spacing: @Composable () -> Dp,
    val iconSize: @Composable () -> Dp,
    val textSize: @Composable () -> TextUnit,
    val lineHeight: @Composable () -> TextUnit,
) {
    MEDIUM(
        textSize = { DesignSystemTheme.typography.bodyMd.fontSize },
        lineHeight = { DesignSystemTheme.typography.bodyMd.lineHeight },
        size = { DesignSystemTheme.dimensions.sizing.baseMd },
        paddingFull = { DesignSystemTheme.dimensions.spacing.fixedXs },
        paddingH = { DesignSystemTheme.dimensions.spacing.fixedMd },
        spacing = { DesignSystemTheme.dimensions.spacing.fixedXs },
        iconSize = { 24.dp },
    ),
    SMALL(
        size = { DesignSystemTheme.dimensions.sizing.baseSm },
        textSize = { DesignSystemTheme.typography.bodySm.fontSize },
        lineHeight = { DesignSystemTheme.typography.bodyMd.lineHeight },
        paddingFull = { DesignSystemTheme.dimensions.spacing.fixed3xs },
        paddingH = { DesignSystemTheme.dimensions.spacing.fixedSm },
        spacing = { DesignSystemTheme.dimensions.spacing.fixed2xs },
        iconSize = { 20.dp },
    )
}

enum class DSButtonWidth { AUTO, FULL_WIDTH }

private class DSButtonPreviewProvider : PreviewParameterProvider<DSButtonPreviewParameterType> {
    override val values = DSButtonVariant.entries.flatMap { variant ->
        DSButtonSize.entries.flatMap { size ->
            DSButtonWidth.entries.flatMap { width ->
                listOf(true, false).map { enabled ->
                    DSButtonPreviewParameterType(
                        variant = variant,
                        size = size,
                        width = width,
                        enabled = enabled
                    )
                }
            }
        }
    }.asSequence()
}

private class DSButtonPreviewParameterType(
    val size: DSButtonSize,
    val variant: DSButtonVariant,
    val width: DSButtonWidth,
    val enabled: Boolean
)

@Composable
@Preview
@PreviewLightDark
private fun DSButtonPreview(
    @PreviewParameter(DSButtonPreviewProvider::class) previewType: DSButtonPreviewParameterType
) {
    DesignSystemTheme {
        Box(
            modifier = Modifier
                .width(150.dp)
                .background(Color.White)
                .padding(5.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                DSButton(
                    onClick = {},
                    text = previewType.width.name,
                    size = previewType.size,
                    width = previewType.width,
                    variant = previewType.variant,
                    enabled = previewType.enabled,
                    icon = DSButtonIcon(iconRes = R.drawable.sample_vector),
                    showIcon = true,
                    noText = false,
                )
                DSButton(
                    onClick = {},
                    icon = DSButtonIcon(iconRes = R.drawable.sample_vector),
                    size = previewType.size,
                    width = previewType.width,
                    variant = previewType.variant,
                    enabled = previewType.enabled,
                    showIcon = true,
                )
            }
        }
    }
}
