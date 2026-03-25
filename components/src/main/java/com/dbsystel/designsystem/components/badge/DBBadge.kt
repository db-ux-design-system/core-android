package com.dbsystel.designsystem.components.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.components.badge.extensions.dotSize
import com.dbsystel.designsystem.components.badge.extensions.horizontalPadding
import com.dbsystel.designsystem.components.badge.extensions.iconSize
import com.dbsystel.designsystem.components.badge.extensions.paddingFull
import com.dbsystel.designsystem.components.badge.extensions.textStyle
import com.dbsystel.designsystem.components.core.DBEmphasis
import com.dbsystel.designsystem.components.core.DBIcon
import com.dbsystel.designsystem.components.core.DBSemantic
import com.dbsystel.designsystem.components.core.DBSize
import com.dbsystel.designsystem.components.core.extensions.backgroundColor
import com.dbsystel.designsystem.components.core.extensions.borderColor
import com.dbsystel.designsystem.components.core.extensions.iconColor
import com.dbsystel.designsystem.components.core.extensions.textColor
import com.dbsystel.designsystem.components.core.preview.BasePreview
import com.dbsystel.designsystem.components.core.preview.BasePreviewProperties
import com.dbsystel.designsystem.components.core.preview.previewName
import com.dbsystel.designsystem.foundation.R
import com.dbsystel.designsystem.foundation.theme.DBTheme
import com.dbsystel.designsystem.components.badge.preview.previewName as badgePreviewName


/**
 * Badges are static, visual indicators used to highlight important information and to display the
 * status of items, components, or content. They can be used in a minimal form as a dot or with
 * short texts.
 *
 * @param modifier The Modifier to be applied to this badge
 * @param content The content of the badge, which can be either text, an icon, or a dot
 * @param emphasis The emphasis attribute divides in between a weak or strong importance
 * @param semantic The semantic defines the default variants for most components
 * @param size The size of the badge, which can be either small or medium
 *
 * @sample com.dbsystel.designsystem.components.badge.preview.DBBadgeSample
 */
@Composable
fun DBBadge(
    modifier: Modifier = Modifier,
    content: DBBadgeContent = DBBadgeContent.Text(""),
    emphasis: DBEmphasis = DBEmphasis.WEAK,
    semantic: DBSemantic = DBSemantic.ADAPTIVE,
    size: DBSize = DBSize.SMALL,
) {
    val shape = RoundedCornerShape(CornerSize(percent = 50))

    Column(
        modifier = Modifier
            .background(color = semantic.backgroundColor(emphasis), shape = shape)
            .border(
                width = DBTheme.dimensions.border.width3xs,
                color = semantic.borderColor,
                shape = shape,
            )
            .clip(shape)
            .sizeIn(
                minHeight = if (content is DBBadgeContent.Dot) size.dotSize else 14.dp,
                minWidth = if (content is DBBadgeContent.Dot) size.dotSize else 0.dp,
            )
            .then(modifier),
        verticalArrangement = Arrangement.Center,
    ) {
        when (content) {
            is DBBadgeContent.Text -> {
                Text(
                    modifier = Modifier.padding(horizontal = size.horizontalPadding),
                    text = content.text,
                    color = semantic.textColor(emphasis),
                    style = size.textStyle.copy(fontWeight = FontWeight.W700),
                )
            }

            is DBBadgeContent.Icon -> {
                Icon(
                    modifier = Modifier
                        .size(size.iconSize)
                        .padding(all = size.paddingFull + 1.dp),
                    imageVector = content.icon.imageVector,
                    contentDescription = content.icon.contentDescription,
                    tint = semantic.iconColor(emphasis),
                )
            }

            DBBadgeContent.Dot -> Unit
        }
    }
}

sealed interface DBBadgeContent {
    data class Text(val text: String) : DBBadgeContent
    data object Dot : DBBadgeContent
    data class Icon(val icon: DBIcon) : DBBadgeContent
}

@Preview
@Composable
private fun DBBadgePreview() {
    BasePreview(
        component = "DBBadge",
        preview = {
            DBSemantic.entries.forEach { semantic ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedLg),
                ) {
                    DBBadge(
                        content = DBBadgeContent.Text("Badge"),
                        semantic = semantic,
                    )
                    DBBadge(
                        content = DBBadgeContent.Text("Badge"),
                        semantic = semantic,
                        emphasis = DBEmphasis.STRONG,
                    )
                }
            }
        },
        properties = listOf(
            BasePreviewProperties(
                property = "Size",
                views = DBSize.entries.reversed().map { size ->
                    size.badgePreviewName to {
                        DBBadge(
                            content = DBBadgeContent.Text("Text"),
                            size = size,
                        )
                    }
                }
            ),
            BasePreviewProperties(
                property = "Content",
                views = listOf(
                    DBBadgeContent.Text("Text"),
                    DBBadgeContent.Dot,
                    DBBadgeContent.Icon(
                        DBIcon(ImageVector.vectorResource(R.drawable.sample_vector))
                    )
                ).map { content ->
                    content.badgePreviewName to {
                        DBBadge(
                            content = content,
                        )
                    }
                }
            ),
            BasePreviewProperties(
                property = "Emphasis",
                views = DBEmphasis.entries.map { emphasis ->
                    emphasis.previewName to {
                        DBBadge(
                            content = DBBadgeContent.Text("Text"),
                            emphasis = emphasis,
                        )
                    }
                }
            ),
            BasePreviewProperties(
                property = "Semantic",
                views = DBSemantic.entries.map { semantic ->
                    semantic.previewName to {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedSm),
                        ) {
                            DBBadge(
                                content = DBBadgeContent.Text("Text"),
                                semantic = semantic,
                            )
                            DBBadge(
                                content = DBBadgeContent.Text("Text"),
                                semantic = semantic,
                                emphasis = DBEmphasis.STRONG,
                            )
                        }
                    }
                }
            ),
        )
    )
}
