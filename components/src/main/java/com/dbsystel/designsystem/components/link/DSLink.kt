package com.dbsystel.designsystem.components.link

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.components.link.preview.previewName
import com.dbsystel.designsystem.foundation.R
import com.dbsystel.designsystem.foundation.theme.DesignSystemTheme

/**
 * Links are used as navigation elements. They can stand alone, within a sentence or paragraph
 * or directly after the content to which they refer.
 *
 * @param modifier the Modifier to be applied to this link
 * @param text the text to be displayed
 * @param content content type of the link (external or internal link)
 * @param variant visual representation of the link
 * @param size size of the link
 * @param enabled controls the enabled state of this link. When false, this component will not
 * respond to user input, and it will appear visually disabled and disabled to accessibility services.
 * @param showIcon control the visibility of the content type icon
 * @param onClick called when this link is clicked
 *
 * @sample com.dbsystel.designsystem.components.samples.DSLinkSample
 */
@Composable
fun DSLink(
    modifier: Modifier = Modifier,
    text: String,
    content: DSLinkContent = DSLinkContent.INTERNAL,
    variant: DSLinkVariant = DSLinkVariant.ADAPTIVE,
    size: DSLinkSize = DSLinkSize.MEDIUM,
    enabled: Boolean = true,
    showIcon: Boolean = true,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    val linkColor by animateColorAsState(
        targetValue = if (pressed) variant.pressedColor else variant.color,
        label = "LinkColorAnimation",
    )

    Row(
        modifier = Modifier
            .alpha(if (enabled) 1.0f else 0.4f)
            .clickable(
                enabled = enabled,
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null
            )
            .then(modifier),
        horizontalArrangement = Arrangement.spacedBy(size.paddingH),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = text,
            textDecoration = TextDecoration.Underline,
            style = size.textStyle,
            color = linkColor,
        )
        if (showIcon) Icon(
            modifier = Modifier.size(size.iconSize),
            painter = painterResource(content.iconRes),
            contentDescription = content.name,
            tint = linkColor,
        )
    }
}

enum class DSLinkVariant {
    ADAPTIVE,
    BRAND;

    internal val color: Color
        @Composable
        @ReadOnlyComposable
        get() = when (this) {
            ADAPTIVE -> DesignSystemTheme.activeColor.Basic.Text.Emphasis100.Default
            BRAND -> DesignSystemTheme.colors.brand.Basic.Text.Emphasis80.Default
        }

    internal val pressedColor: Color
        @Composable
        @ReadOnlyComposable
        get() = when (this) {
            ADAPTIVE -> DesignSystemTheme.activeColor.Basic.Text.Emphasis100.Pressed
            BRAND -> DesignSystemTheme.colors.brand.Basic.Text.Emphasis80.Pressed
        }
}

enum class DSLinkSize {
    MEDIUM,
    SMALL;

    internal val paddingH: Dp
        @Composable
        @ReadOnlyComposable
        get() = when (this) {
            MEDIUM -> DesignSystemTheme.dimensions.spacing.fixed2xs
            SMALL -> DesignSystemTheme.dimensions.spacing.fixed3xs
        }

    internal val iconSize: Dp
        @Composable
        @ReadOnlyComposable
        get() = when (this) {
            MEDIUM -> 24.dp
            SMALL -> 20.dp
        }

    internal val textStyle: TextStyle
        @Composable
        @ReadOnlyComposable
        get() = when (this) {
            MEDIUM -> TextStyle(fontSize = DesignSystemTheme.typography.bodyMd.fontSize)
            SMALL -> TextStyle(fontSize = DesignSystemTheme.typography.bodySm.fontSize)
        }
}

enum class DSLinkContent {
    INTERNAL,
    EXTERNAL;

    internal val iconRes: Int
        @DrawableRes
        get() = when (this) {
            INTERNAL -> R.drawable.ds_ic_arrow_forward
            EXTERNAL -> R.drawable.ds_ic_link_external
        }
}

@Composable
@Preview(
    showBackground = true,
    group = "Content",
)
private fun DSLinkPreview_Content() {
    DesignSystemTheme {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            DSLinkContent.entries.forEach { content ->
                DSLink(
                    text = content.previewName,
                    content = content,
                    onClick = {},
                )
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    group = "Variant",
)
private fun DSLinkPreview_Variant() {
    DesignSystemTheme {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            DSLinkVariant.entries.forEach { variant ->
                DSLink(
                    text = variant.previewName,
                    variant = variant,
                    onClick = {},
                )
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    group = "Disabled",
)
private fun DSLinkPreview_Disabled() {
    DesignSystemTheme {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            listOf(false, true).forEach { disabled ->
                DSLink(
                    text = if (!disabled) "(Def) False" else "True",
                    enabled = !disabled,
                    onClick = {},
                )
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    group = "Size",
)
private fun DSLinkPreview_Size() {
    DesignSystemTheme {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            DSLinkSize.entries.forEach { size ->
                DSLink(
                    text = size.previewName,
                    size = size,
                    onClick = {},
                )
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    group = "ShowIcon",
)
private fun DSLinkPreview_ShowIcon() {
    DesignSystemTheme {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            listOf(true, false).forEach { showIcon ->
                DSLink(
                    text = if (showIcon) "(Def) True" else "False",
                    showIcon = showIcon,
                    onClick = {},
                )
            }
        }
    }
}
