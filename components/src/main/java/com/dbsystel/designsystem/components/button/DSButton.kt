package com.dbsystel.designsystem.components.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.components.button.preview.previewName
import com.dbsystel.designsystem.foundation.R
import com.dbsystel.designsystem.foundation.theme.DesignSystemTheme

/**
 * Buttons are a fundamental element in UI design and are used to prompt users to interact
 * with the user interface and trigger an action.
 *
 * @param modifier the Modifier to be applied to this button
 * @param text the text to be displayed
 * @param icon wrapper class to define the icon to be displayed
 * @param variant visual representation of the button
 * @param size size of the button
 * @param enabled controls the enabled state of this button. When false, this component will not respond to user input, and it will appear visually disabled and disabled to accessibility services.
 * @param width width of the button
 * @param onClick called when this button is clicked
 *
 * @sample com.dbsystel.designsystem.components.samples.DSButtonSample
 */
@Composable
fun DSButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    icon: DSButtonIcon? = null,
    variant: DSButtonVariant = DSButtonVariant.OUTLINED,
    size: DSButtonSize = DSButtonSize.MEDIUM,
    enabled: Boolean = true,
    width: DSButtonWidth = DSButtonWidth.AUTO,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(size = DesignSystemTheme.dimensions.border.radiusXs)
    val iconOnly = icon != null && text.isNullOrBlank()
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    val buttonBackground by animateColorAsState(
        targetValue =
        if (pressed) DesignSystemTheme.activeColor.Basic.Background.Transparent.Pressed
        else variant.background,
        label = "ButtonColorAnimation"
    )

    Button(
        border = if (variant.hasBorder) BorderStroke(
            width = DesignSystemTheme.dimensions.border.height3xs,
            color = DesignSystemTheme.activeColor.onBgBasicEmphasis100Default
        ) else null,
        shape = shape,
        modifier = Modifier
            .then(if (iconOnly) Modifier.size(size.size) else Modifier.height(size.size))
            .then(if (width == DSButtonWidth.FULL_WIDTH) Modifier.fillMaxWidth() else Modifier)
            .alpha(if (enabled) 1.0f else 0.4f)
            .then(modifier),
        onClick = onClick,
        enabled = enabled,
        colors = ButtonColors(
            contentColor = variant.color,
            containerColor = buttonBackground,
            disabledContentColor = variant.color,
            disabledContainerColor = variant.background,
        ),
        contentPadding = if (!iconOnly) {
            PaddingValues(horizontal = size.paddingH, vertical = 0.dp)
        } else {
            PaddingValues(all = size.paddingFull)
        },
        interactionSource = interactionSource
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(size.spacing),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let {
                Icon(
                    modifier = Modifier.size(size.iconSize),
                    imageVector = icon.imageVector,
                    contentDescription = icon.contentDescription,
                    tint = variant.color
                )
            }
            text?.let {
                if (text.isBlank()) return@let
                Text(
                    text = text,
                    style = size.textStyle.copy(fontWeight = FontWeight.Bold),
                    color = variant.color
                )
            }
        }
    }
}

/**
 * @param imageVector [ImageVector] to draw inside this icon
 * @param contentDescription text used by accessibility services to describe what this icon
 * represents. This should always be provided unless this icon is used for decorative purposes, and
 * does not represent a meaningful action that a user can take. This text should be localized.
 */
data class DSButtonIcon(
    val imageVector: ImageVector,
    val contentDescription: String? = null,
)

enum class DSButtonVariant {
    OUTLINED,
    GHOST,
    FILLED,
    BRAND;

    internal val background: Color
        @Composable
        @ReadOnlyComposable
        get() = when (this) {
            OUTLINED,
            GHOST,
                -> DesignSystemTheme.activeColor.Basic.Background.Transparent.Full

            FILLED -> DesignSystemTheme.activeColor.Basic.Background.Transparent.Semi
            BRAND -> DesignSystemTheme.colors.brand.Origin.Default
        }

    internal val color: Color
        @Composable
        @ReadOnlyComposable
        get() = when (this) {
            OUTLINED,
            GHOST,
            FILLED,
                -> DesignSystemTheme.activeColor.Basic.Text.Emphasis100.Default

            BRAND -> DesignSystemTheme.colors.brand.OnOrigin.Default
        }
    internal val hasBorder: Boolean
        @Composable
        @ReadOnlyComposable
        get() = when (this) {
            OUTLINED -> true
            GHOST,
            FILLED,
            BRAND,
                -> false
        }
}

enum class DSButtonSize {
    MEDIUM,
    SMALL;

    internal val size: Dp
        @Composable
        @ReadOnlyComposable
        get() = when (this) {
            MEDIUM -> DesignSystemTheme.dimensions.sizing.baseMd
            SMALL -> DesignSystemTheme.dimensions.sizing.baseSm
        }

    internal val paddingFull: Dp
        @Composable
        @ReadOnlyComposable
        get() = when (this) {
            MEDIUM -> DesignSystemTheme.dimensions.spacing.fixedXs
            SMALL -> DesignSystemTheme.dimensions.spacing.fixed3xs
        }

    internal val paddingH: Dp
        @Composable
        @ReadOnlyComposable
        get() = when (this) {
            MEDIUM -> DesignSystemTheme.dimensions.spacing.fixedMd
            SMALL -> DesignSystemTheme.dimensions.spacing.fixedSm
        }

    internal val spacing: Dp
        @Composable
        @ReadOnlyComposable
        get() = when (this) {
            MEDIUM -> DesignSystemTheme.dimensions.spacing.fixedXs
            SMALL -> DesignSystemTheme.dimensions.spacing.fixed2xs
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
            MEDIUM -> DesignSystemTheme.typography.bodyMd
            SMALL -> DesignSystemTheme.typography.bodySm
        }
}

enum class DSButtonWidth { AUTO, FULL_WIDTH }

@OptIn(ExperimentalLayoutApi::class)
@Composable
@Preview(
    showBackground = true,
    group = "Variants",
)
private fun DSButtonPreview_Variants(
) {
    DesignSystemTheme {
        FlowRow(
            modifier = Modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            DSButtonVariant.entries.forEach { variant ->
                DSButton(
                    text = variant.previewName,
                    variant = variant,
                    enabled = true,
                    icon = if (variant == DSButtonVariant.OUTLINED) DSButtonIcon(
                        ImageVector.vectorResource(
                            R.drawable.sample_vector
                        )
                    ) else null,
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
private fun DSButtonPreview_Disabled(
) {
    DesignSystemTheme {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            listOf(true, false).forEach { enabled ->
                DSButton(
                    text = if (!enabled) "True" else "(Def) False",
                    enabled = enabled,
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
private fun DSButtonPreview_Size(
) {
    DesignSystemTheme {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            DSButtonSize.entries.forEach { size ->
                DSButton(
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
private fun DSButtonPreview_ShowIcon(
) {
    DesignSystemTheme {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            listOf(false, true).forEach { showIcon ->
                DSButton(
                    text = if (showIcon) "True" else "(Def) False",
                    icon = if (showIcon) DSButtonIcon(
                        ImageVector.vectorResource(
                            R.drawable.sample_vector
                        )
                    ) else null,
                    onClick = {},
                )
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    group = "NoText",
)
private fun DSButtonPreview_NoText(
) {
    DesignSystemTheme {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            listOf(false, true).forEach { showIcon ->
                DSButton(
                    text = if (showIcon) null else "(Def) False",
                    icon = if (showIcon) DSButtonIcon(
                        ImageVector.vectorResource(
                            R.drawable.sample_vector
                        )
                    ) else null,
                    onClick = {},
                )
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    group = "Width",
)
private fun DSButtonPreview_Width(
) {
    DesignSystemTheme {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            DSButtonWidth.entries.forEach { width ->
                DSButton(
                    text = width.previewName,
                    width = width,
                    onClick = {},
                )
            }
        }
    }
}
