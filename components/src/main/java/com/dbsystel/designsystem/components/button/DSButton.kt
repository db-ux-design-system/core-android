package com.dbsystel.designsystem.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.foundation.theme.DesignSystemTheme

/**
 * Basic DesignSystem-Button
 *
 * @param modifier - custom modifier of the button
 * @param enabled - enable state
 * @param callback - callback when button clicked
 * @param text - text to display
 * @param icon - icon to display
 * @param iconContentDescription - content description of the icon
 * @param size - size of the button
 * @param variant - variant of the button
 * @param width - width-setting of the button
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DSButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    callback: () -> Unit,
    text: String? = null,
    icon: Int? = null,
    iconContentDescription: String = "Icon",
    size: DSButtonSize = DSButtonSize.MEDIUM,
    variant: DSButtonVariant = DSButtonVariant.OUTLINE,
    width: DSButtonWidth = DSButtonWidth.AUTO,
) {
    val shape = RoundedCornerShape(size = size.borderRadius())
    val iconOnly = icon != null && text.isNullOrEmpty()
    val textOnly = icon == null && !text.isNullOrEmpty()
    val textAndIcon = icon != null && !text.isNullOrEmpty()
    val backgroundRippleTheme = RippleConfiguration(
        color = DesignSystemTheme.activeColor.Basic.Background.Transparent.Pressed,
        rippleAlpha = RippleAlpha(1.0f, 1.0f, 1.0f, 1.0f)
    )
    CompositionLocalProvider(LocalRippleConfiguration provides backgroundRippleTheme) {
        Button(
            border = if (variant.hasBorder) BorderStroke(
                width = size.borderHeight(),
                color = DesignSystemTheme.activeColor.onBgBasicEmphasis100Default
            ) else null,
            shape = shape,
            modifier = Modifier
                .height(size.size())
                .run { if (width == DSButtonWidth.FULL_WIDTH) fillMaxWidth() else this }
                .run { if (enabled) alpha(1.0f) else alpha(0.4f) }
                .then(modifier),
            onClick = callback,
            enabled = enabled,
            colors = ButtonColors(
                contentColor = variant.color(),
                containerColor = variant.background(),
                disabledContentColor = variant.color(),
                disabledContainerColor = variant.background(),
            ),
            contentPadding = if (textAndIcon || textOnly) {
                PaddingValues(horizontal = size.paddingH(), vertical = 0.dp)
            } else if (iconOnly) {
                PaddingValues(all = size.paddingFull())
            } else {
                PaddingValues(horizontal = size.paddingH())
            },
        ) {
            Row {
                if (icon != null) Image(
                    modifier = Modifier
                        .size(size.iconSize())
                        .padding(end = size.spacing()),
                    painter = painterResource(id = icon),
                    contentDescription = iconContentDescription
                )
                if (text != null) Text(
                    text = text,
                    fontSize = size.textSize(),
                    fontWeight = FontWeight(size.fontWeight()),
                    lineHeight = size.lineHeight(),
                    color = variant.color()
                )
            }
        }
    }
}

enum class DSButtonVariant(
    val background: @Composable () -> Color,
    val color: @Composable () -> Color,
    val hasBorder: Boolean,
) {
    OUTLINE(
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
    val borderRadius: @Composable () -> Dp,
    val borderHeight: @Composable () -> Dp,
    val iconSize: @Composable () -> Dp,
    val textSize: @Composable () -> TextUnit,
    val fontWeight: @Composable () -> Int,
    val lineHeight: @Composable () -> TextUnit,
) {
    MEDIUM(
        textSize = { DesignSystemTheme.typography.bodyMd.fontSize },
        lineHeight = { DesignSystemTheme.typography.bodyMd.lineHeight },
        size = { DesignSystemTheme.dimensions.sizing.baseMd },
        paddingFull = { DesignSystemTheme.dimensions.spacing.fixedXs },
        paddingH = { DesignSystemTheme.dimensions.spacing.fixedMd },
        spacing = { DesignSystemTheme.dimensions.spacing.fixedXs },
        borderRadius = { DesignSystemTheme.dimensions.border.radiusXs },
        borderHeight = { DesignSystemTheme.dimensions.border.height3xs },
        iconSize = { 20.dp },
        fontWeight = { 700 }
    ),
    SMALL(
        size = { DesignSystemTheme.dimensions.sizing.baseSm },
        textSize = { DesignSystemTheme.typography.bodySm.fontSize },
        lineHeight = { DesignSystemTheme.typography.bodyMd.lineHeight },
        paddingFull = { DesignSystemTheme.dimensions.spacing.fixed3xs },
        paddingH = { DesignSystemTheme.dimensions.spacing.fixedSm },
        spacing = { DesignSystemTheme.dimensions.spacing.fixed2xs },
        borderRadius = { DesignSystemTheme.dimensions.border.radiusXs },
        borderHeight = { DesignSystemTheme.dimensions.border.height3xs },
        iconSize = { 16.dp },
        fontWeight = { 700 }
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
            DSButton(
                callback = {},
                text = previewType.width.name,
                size = previewType.size,
                width = previewType.width,
                variant = previewType.variant,
                enabled = previewType.enabled
            )
        }
    }
}
