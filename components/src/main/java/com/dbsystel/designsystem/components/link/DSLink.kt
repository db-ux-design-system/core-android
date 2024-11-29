package com.dbsystel.designsystem.components.link

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.foundation.R
import com.dbsystel.designsystem.foundation.theme.DesignSystemTheme

/**
 * Basic DesignSystem-Link
 *
 * @param modifier - custom modifier of the link
 * @param enabled - enable state
 * @param callback - callback when link is clicked
 * @param text - text of link
 * @param variant - variant of the link
 * @param size - size of the link (medium or small)
 * @param content - content type of the link (external or internal link)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DSLink(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    callback: () -> Unit,
    text: String,
    variant: DSLinkVariant = DSLinkVariant.ADAPTIVE,
    size: DSLinkSize = DSLinkSize.MEDIUM,
    content: DSLinkContent = DSLinkContent.INTERNAL,
) {
    val backgroundRippleTheme = RippleConfiguration(
        color = variant.pressedColor(),
        rippleAlpha = RippleAlpha(1.0f, 1.0f, 1.0f, 1.0f)
    )
    CompositionLocalProvider(LocalRippleConfiguration provides backgroundRippleTheme) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .run { if (enabled) alpha(1.0f) else alpha(0.4f) }
                .clickable(enabled = enabled) { callback() }
                .then(modifier)
        ) {
            Text(
                text = text,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.padding(end = size.paddingH()),
                fontSize = size.textSize(),
                color = variant.color()
            )
            Box(modifier = Modifier.size(size.iconSize())) {
                Image(
                    painter = painterResource(content.iconRes),
                    contentDescription = "Arrow",
                    modifier = Modifier.align(Alignment.Center),
                    colorFilter = ColorFilter.tint(variant.color())
                )
            }
        }
    }
}

enum class DSLinkVariant(
    val color: @Composable () -> Color,
    val pressedColor: @Composable () -> Color
) {
    ADAPTIVE(
        color = { DesignSystemTheme.activeColor.Basic.Text.Emphasis100.Default },
        pressedColor = { DesignSystemTheme.activeColor.onBgBasicEmphasis100Pressed },
    ),
    BRAND(
        color = { DesignSystemTheme.colors.brand.Origin.Default },
        pressedColor = { DesignSystemTheme.colors.brand.onBgBasicEmphasis100Pressed },
    ),
}

enum class DSLinkSize(
    val paddingH: @Composable () -> Dp,
    val iconSize: @Composable () -> Dp,
    val textSize: @Composable () -> TextUnit,
) {
    MEDIUM(
        paddingH = { DesignSystemTheme.dimensions.spacing.fixed2xs },
        iconSize = { 24.dp },
        textSize = { DesignSystemTheme.typography.bodyMd.fontSize },
    ),
    SMALL(
        paddingH = { DesignSystemTheme.dimensions.spacing.fixed3xs },
        iconSize = { 20.dp },
        textSize = { DesignSystemTheme.typography.bodySm.fontSize },
    )
}

enum class DSLinkContent(@DrawableRes val iconRes: Int) {
    INTERNAL(iconRes = R.drawable.arrow_right),
    EXTERNAL(iconRes = R.drawable.arrow_right_up),
}

private class DSLinkPreviewProvider : PreviewParameterProvider<DSLinkPreviewParameterType> {
    override val values = DSLinkVariant.entries.flatMap { variant ->
        DSLinkSize.entries.flatMap { size ->
            DSLinkContent.entries.flatMap { content ->
                listOf(true, false).map { enabled ->
                    DSLinkPreviewParameterType(
                        variant = variant,
                        size = size,
                        content = content,
                        enabled = enabled
                    )
                }
            }
        }
    }.asSequence()
}

private class DSLinkPreviewParameterType(
    val size: DSLinkSize,
    val variant: DSLinkVariant,
    val content: DSLinkContent,
    val enabled: Boolean
)

@Composable
@Preview
private fun DSButtonPreview(
    @PreviewParameter(DSLinkPreviewProvider::class) previewType: DSLinkPreviewParameterType
) {
    DesignSystemTheme {
        Box(
            modifier = Modifier
                .width(150.dp)
                .background(Color.White)
                .padding(5.dp)
        ) {
            DSLink(
                callback = {},
                text = previewType.variant.name,
                size = previewType.size,
                content = previewType.content,
                variant = previewType.variant,
                enabled = previewType.enabled
            )
        }
    }
}
