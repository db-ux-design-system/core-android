package com.dbsystel.designsystem.components.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.components.button.extensions.background
import com.dbsystel.designsystem.components.button.extensions.color
import com.dbsystel.designsystem.components.button.extensions.hasBorder
import com.dbsystel.designsystem.components.button.extensions.iconSize
import com.dbsystel.designsystem.components.button.extensions.paddingFull
import com.dbsystel.designsystem.components.button.extensions.paddingH
import com.dbsystel.designsystem.components.button.extensions.size
import com.dbsystel.designsystem.components.button.extensions.spacing
import com.dbsystel.designsystem.components.button.extensions.textStyle
import com.dbsystel.designsystem.components.button.preview.previewName
import com.dbsystel.designsystem.components.core.DBIcon
import com.dbsystel.designsystem.components.core.DBSize
import com.dbsystel.designsystem.components.core.preview.BasePreview
import com.dbsystel.designsystem.components.core.preview.BasePreviewProperties
import com.dbsystel.designsystem.components.core.preview.previewName
import com.dbsystel.designsystem.components.core.extensions.withAlphaForDisabledState
import com.dbsystel.designsystem.foundation.R
import com.dbsystel.designsystem.foundation.theme.DBTheme

/**
 * Buttons are a fundamental element in UI design and are used to prompt users to interact
 * with the user interface and trigger an action.
 *
 * @param modifier The Modifier to be applied to this button.
 * @param text The text to be displayed.
 * @param noText When true, the button will not display any text, even if the [text] parameter is
 * provided.
 * @param icon Wrapper class to define the icon to be displayed.
 * @param showIcon Controls whether the icon should be displayed. When true, the icon will be shown
 * if provided, otherwise it will be hidden.
 * @param iconPosition Defines the position of the icon in relation to the text. It can be either
 * leading (before the text) or trailing (after the text).
 * @param variant Visual representation of the button.
 * @param size Size of the button.
 * @param disabled Controls the disabled state of this button.
 * @param onClick Called when this button is clicked.
 *
 * @sample com.dbsystel.designsystem.components.button.preview.DBButtonSample
 */
@Composable
fun DBButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    noText: Boolean = false,
    icon: DBIcon? = null,
    showIcon: Boolean = false,
    iconPosition: DBButtonIconPosition = DBButtonIconPosition.LEADING,
    variant: DBButtonVariant = DBButtonVariant.OUTLINED,
    size: DBSize = DBSize.MEDIUM,
    disabled: Boolean = false,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(size = DBTheme.dimensions.border.radiusXs)
    val iconOnly = icon != null && showIcon && (noText || text.isNullOrBlank())
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    val buttonBackground by animateColorAsState(
        targetValue =
            when {
                pressed && variant == DBButtonVariant.BRAND -> DBTheme.colors.brand.Origin.Pressed
                pressed -> DBTheme.activeColor.Basic.Background.Transparent.FullPressed
                else -> variant.background
            },
        label = "ButtonColorAnimation"
    )

    Button(
        border = if (variant.hasBorder) BorderStroke(
            width = DBTheme.dimensions.border.width3xs,
            color = DBTheme.activeColor.onBgBasicEmphasis100Default
        ) else null,
        shape = shape,
        modifier = modifier
            .then(if (iconOnly) Modifier.size(size.size) else Modifier.height(size.size))
            .withAlphaForDisabledState(disabled),
        onClick = onClick,
        enabled = !disabled,
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
            if (showIcon && iconPosition == DBButtonIconPosition.LEADING && icon != null) {
                Icon(
                    modifier = Modifier.size(size.iconSize),
                    imageVector = icon.imageVector,
                    contentDescription = icon.contentDescription,
                )
            }
            if (!noText && !text.isNullOrBlank()) {
                Text(
                    text = text,
                    style = size.textStyle.copy(fontWeight = FontWeight.Bold),
                )
            }
            if (showIcon && iconPosition == DBButtonIconPosition.TRAILING && icon != null) {
                Icon(
                    modifier = Modifier.size(size.iconSize),
                    imageVector = icon.imageVector,
                    contentDescription = icon.contentDescription,
                )
            }
        }
    }
}

enum class DBButtonVariant {
    OUTLINED,
    FILLED,
    GHOST,
    BRAND;
}

enum class DBButtonIconPosition { LEADING, TRAILING; }

@Preview(heightDp = 1400)
@Composable
private fun DBButtonPreview() {
    BasePreview(
        component = "DBButton",
        preview = {
            DBButtonVariant.entries.forEach { variant ->
                DBButton(
                    text = "Button",
                    variant = variant,
                ) { }
            }
        },
        properties = listOf(
            BasePreviewProperties(
                property = "Variant",
                views = DBButtonVariant.entries.map { variant ->
                    variant.previewName to {
                        DBButton(
                            text = "Text",
                            variant = variant,
                        ) { }
                    }
                }
            ),
            BasePreviewProperties(
                property = "Disabled",
                views = listOf(false, true).map { disabled ->
                    (if (!disabled) "(Def) False" else "True") to {
                        DBButton(
                            text = "Text",
                            disabled = disabled,
                        ) { }
                    }
                }
            ),
            BasePreviewProperties(
                property = "Size",
                views = DBSize.entries.map { size ->
                    size.previewName to {
                        DBButton(
                            text = "Text",
                            size = size,
                        ) { }
                    }
                }
            ),
            BasePreviewProperties(
                property = "Show Icon Leading",
                views = listOf(false, true).map { showIcon ->
                    (if (!showIcon) "(Def) False" else "True") to {
                        DBButton(
                            text = "Text",
                            icon = DBIcon(ImageVector.vectorResource(R.drawable.sample_vector)),
                            showIcon = showIcon,
                        ) { }
                    }
                }
            ),
            BasePreviewProperties(
                property = "Show Icon Trailing",
                views = listOf(false, true).map { showIcon ->
                    (if (!showIcon) "(Def) False" else "True") to {
                        DBButton(
                            text = "Text",
                            icon = DBIcon(ImageVector.vectorResource(R.drawable.sample_vector)),
                            showIcon = showIcon,
                            iconPosition = DBButtonIconPosition.TRAILING,
                        ) { }
                    }
                }
            ),
            BasePreviewProperties(
                property = "No Text",
                views = listOf(false, true).map { noText ->
                    (if (!noText) "(Def) False" else "True") to {
                        DBButton(
                            text = "Text",
                            noText = noText,
                            showIcon = noText,
                            icon = DBIcon(ImageVector.vectorResource(R.drawable.sample_vector)),
                        ) { }
                    }
                }
            ),
            BasePreviewProperties(
                property = "Width",
                views = listOf(false, true).map { width ->
                    (if (!width) "(Def) Auto" else "Full") to {
                        DBButton(
                            modifier = if (width) Modifier.fillMaxWidth(0.7f) else Modifier,
                            text = "Text",
                        ) { }
                    }
                }
            ),
        ),
    )
}
