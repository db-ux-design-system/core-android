package com.dbsystel.designsystem.components.link

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.dbsystel.designsystem.components.core.DBSize
import com.dbsystel.designsystem.components.core.preview.BasePreview
import com.dbsystel.designsystem.components.core.preview.BasePreviewProperties
import com.dbsystel.designsystem.components.core.preview.previewName
import com.dbsystel.designsystem.components.core.extensions.withAlphaForDisabledState
import com.dbsystel.designsystem.components.link.extensions.color
import com.dbsystel.designsystem.components.link.extensions.iconRes
import com.dbsystel.designsystem.components.link.extensions.iconSize
import com.dbsystel.designsystem.components.link.extensions.paddingH
import com.dbsystel.designsystem.components.link.extensions.pressedColor
import com.dbsystel.designsystem.components.link.extensions.textStyle
import com.dbsystel.designsystem.components.link.preview.previewName

/**
 * Links are used as navigation elements. They can stand alone, within a sentence or paragraph
 * or directly after the content to which they refer.
 *
 * @param modifier The Modifier to be applied to this link.
 * @param text The text to be displayed.
 * @param content Content type of the link (external or internal link).
 * @param variant Visual representation of the link.
 * @param size Size of the link.
 * @param disabled Controls the disabled state of this link. When true, this component will not
 * respond to user input, and it will appear visually disabled and disabled to accessibility services.
 * @param showIcon Control the visibility of the content type icon.
 * @param onClick Called when this link is clicked.
 *
 * @sample com.dbsystel.designsystem.components.link.preview.DBLinkSample
 */
@Composable
fun DBLink(
    modifier: Modifier = Modifier,
    text: String,
    content: DBLinkContent = DBLinkContent.INTERNAL,
    variant: DBLinkVariant = DBLinkVariant.ADAPTIVE,
    size: DBSize = DBSize.MEDIUM,
    disabled: Boolean = false,
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
        modifier = modifier
            .withAlphaForDisabledState(disabled)
            .clickable(
                enabled = !disabled,
                onClick = onClick,
                interactionSource = interactionSource,
                role = Role.Button,
                indication = null,
            )
            .heightIn(min = size.iconSize),
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

enum class DBLinkVariant {
    ADAPTIVE,
    BRAND;
}

enum class DBLinkContent {
    INTERNAL,
    EXTERNAL;
}

@Composable
@Preview
private fun DBLinkPreview() {
    BasePreview(
        component = "DBLink",
        preview = {
            DBLinkVariant.entries.forEach { variant ->
                DBLink(text = "Text", variant = variant) { }
                DBLink(
                    text = "Text",
                    variant = variant,
                    content = DBLinkContent.EXTERNAL,
                ) { }
            }
        },
        properties = listOf(
            BasePreviewProperties(
                property = "Content",
                views = DBLinkContent.entries.map { content ->
                    content.previewName to {
                        DBLink(text = "Text", content = content) { }
                    }
                }
            ),
            BasePreviewProperties(
                property = "Variant",
                views = DBLinkVariant.entries.map { variant ->
                    variant.previewName to {
                        DBLink(text = "Text", variant = variant) { }
                    }
                },
            ),
            BasePreviewProperties(
                property = "Disabled",
                views = listOf(false, true).map { disabled ->
                    (if (!disabled) "(Def) False" else "True") to {
                        DBLink(text = "Text", disabled = disabled) { }
                    }
                },
            ),
            BasePreviewProperties(
                property = "Size",
                views = DBSize.entries.map { size ->
                    size.previewName to {
                        DBLink(text = "Text", size = size) { }
                    }
                },
            ),
            BasePreviewProperties(
                property = "Show Icon",
                views = listOf(true, false).map { showIcon ->
                    (if (showIcon) "(Def) True" else "False") to {
                        DBLink(text = "Text", showIcon = showIcon) { }
                    }
                },
            ),
        ),
    )
}
