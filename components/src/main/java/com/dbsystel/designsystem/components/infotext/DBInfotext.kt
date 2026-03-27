package com.dbsystel.designsystem.components.infotext

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.components.core.DBSemantic
import com.dbsystel.designsystem.components.core.DBSize
import com.dbsystel.designsystem.components.core.extensions.icon
import com.dbsystel.designsystem.components.core.extensions.iconColor70
import com.dbsystel.designsystem.components.core.extensions.textColor
import com.dbsystel.designsystem.components.core.preview.BasePreview
import com.dbsystel.designsystem.components.core.preview.BasePreviewProperties
import com.dbsystel.designsystem.components.core.preview.previewName
import com.dbsystel.designsystem.components.infotext.extensions.iconSize
import com.dbsystel.designsystem.components.infotext.extensions.textStyle
import com.dbsystel.designsystem.foundation.theme.DBTheme

/**
 * The Infotext component provides a clear and concise presentation of textual content to give users
 * additional information, context, and instructions.
 *
 * @param modifier Modifier to be applied to the Infotext.
 * @param text The text to be displayed in the Infotext.
 * @param semantic The semantic of the Infotext, which determines the icon and colors used.
 * @param size The size of the Infotext, which can be either small or medium.
 * @param showIcon Whether to show the icon or not.
 *
 * @sample com.dbsystel.designsystem.components.infotext.preview.DBInfotextSample
 */
@Composable
fun DBInfotext(
    modifier: Modifier = Modifier,
    text: String,
    semantic: DBSemantic = DBSemantic.ADAPTIVE,
    size: DBSize = DBSize.MEDIUM,
    showIcon: Boolean = true,
) {
    var lineHeight by remember { mutableFloatStateOf(0f) }
    var calculatedPadding by remember { mutableStateOf(0.dp) }
    with(LocalDensity.current) {
        calculatedPadding = (size.iconSize.toPx() - lineHeight).toDp() / 2f
    }
    val textPadding by remember(calculatedPadding) { derivedStateOf { calculatedPadding } }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixed2xs),
    ) {
        if (showIcon) {
            Icon(
                imageVector = semantic.icon,
                contentDescription = null,
                tint = semantic.iconColor70,
                modifier = Modifier.size(size.iconSize),
            )
        }
        Text(
            modifier = Modifier.padding(vertical = textPadding),
            onTextLayout = { layout ->
                lineHeight = layout.getLineBottom(0) - layout.getLineTop(0)
            },
            text = text,
            style = size.textStyle,
            color = semantic.textColor(),
        )
    }
}


@Preview
@Composable
private fun DBInfotextPreview() {
    BasePreview(
        component = "DBInfotext",
        preview = {
            DBSemantic.entries.forEach { semantic ->
                DBInfotext(
                    text = "Infotext",
                    semantic = semantic,
                )
            }
        },
        properties = listOf(
            BasePreviewProperties(
                property = "Semantic",
                views = DBSemantic.entries.map { semantic ->
                    semantic.previewName to {
                        DBInfotext(
                            text = "Text",
                            semantic = semantic,
                        )
                    }
                },
            ),
            BasePreviewProperties(
                property = "Size",
                views = DBSize.entries.map { size ->
                    size.previewName to {
                        DBInfotext(
                            text = "Text",
                            size = size,
                        )
                    }
                },
            ),
            BasePreviewProperties(
                property = "Show Icon",
                views = listOf(true, false).map { showIcon ->
                    (if (showIcon) "(Def) True" else "False") to {
                        DBInfotext(
                            text = "Text",
                            showIcon = showIcon,
                        )
                    }
                },
            ),
            BasePreviewProperties(
                property = "Width",
                views = listOf("Text", "Text with\nMultiline").map { text ->
                    (if (text == "Text") "Single line" else "Multiline") to {
                        DBInfotext(
                            text = text,
                        )
                    }
                },
            ),
        ),
    )
}
