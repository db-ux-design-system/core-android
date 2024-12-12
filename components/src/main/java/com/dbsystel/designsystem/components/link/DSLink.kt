package com.dbsystel.designsystem.components.link

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.foundation.R
import com.dbsystel.designsystem.foundation.theme.DesignSystemTheme

/**
 * Links are used as navigation elements. They can stand alone, within a sentence or paragraph
 * or directly after the content to which they refer.
 *
 * @param modifier - custom modifier of the link
 * @param enabled - enable state
 * @param onClick - callback when link is clicked
 * @param text - text of link
 * @param variant - variant of the link
 * @param size - size of the link (medium or small)
 * @param content - content type of the link (external or internal link)
 * @param showIcon - control the visibility of the icon
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DSLink(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    variant: DSLinkVariant = DSLinkVariant.ADAPTIVE,
    size: DSLinkSize = DSLinkSize.MEDIUM,
    content: DSLinkContent = DSLinkContent.INTERNAL,
    showIcon: Boolean = true,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    Row(
        horizontalArrangement = Arrangement.spacedBy(size.paddingH()),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .alpha(if (enabled) 1.0f else 0.4f)
            .clickable(
                enabled = enabled,
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null
            )
            .then(modifier)
    ) {
        Text(
            text = text,
            textDecoration = TextDecoration.Underline,
            style = size.textStyle(),
            color = if (pressed) variant.pressedColor() else variant.color()
        )
        if (showIcon) Icon(
            modifier = Modifier.size(size.iconSize()),
            painter = painterResource(content.iconRes),
            contentDescription = "Arrow",
            tint = if (pressed) variant.pressedColor() else variant.color(),
        )
    }
}

enum class DSLinkVariant(
    val color: @Composable () -> Color,
    val pressedColor: @Composable () -> Color
) {
    ADAPTIVE(
        color = { DesignSystemTheme.activeColor.Basic.Text.Emphasis100.Default },
        pressedColor = { DesignSystemTheme.activeColor.Basic.Text.Emphasis100.Pressed },
    ),
    BRAND(
        color = { DesignSystemTheme.colors.brand.onBgBasicEmphasis80Default },
        pressedColor = { DesignSystemTheme.colors.brand.onBgBasicEmphasis80Hovered },
    ),
}

enum class DSLinkSize(
    val paddingH: @Composable () -> Dp,
    val iconSize: @Composable () -> Dp,
    val textStyle: @Composable () -> TextStyle,
) {
    MEDIUM(
        paddingH = { DesignSystemTheme.dimensions.spacing.fixed2xs },
        iconSize = { 24.dp },
        textStyle = { TextStyle(fontSize = DesignSystemTheme.typography.bodyMd.fontSize) },
    ),
    SMALL(
        paddingH = { DesignSystemTheme.dimensions.spacing.fixed3xs },
        iconSize = { 20.dp },
        textStyle = { TextStyle(fontSize = DesignSystemTheme.typography.bodySm.fontSize) },
    )
}

enum class DSLinkContent(@DrawableRes val iconRes: Int) {
    INTERNAL(iconRes = R.drawable.ds_ic_arrow_forward),
    EXTERNAL(iconRes = R.drawable.ds_ic_link_external),
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
@Preview(showBackground = true)
private fun DSLinkPreview(
    @PreviewParameter(DSLinkPreviewProvider::class) previewType: DSLinkPreviewParameterType
) {
    DesignSystemTheme {
        Box(
            modifier = Modifier
                .padding(5.dp)
        ) {
            DSLink(
                onClick = {},
                text = previewType.variant.name,
                size = previewType.size,
                content = previewType.content,
                variant = previewType.variant,
                enabled = previewType.enabled
            )
        }
    }
}
