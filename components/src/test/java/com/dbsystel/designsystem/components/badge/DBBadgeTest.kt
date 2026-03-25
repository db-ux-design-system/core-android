package com.dbsystel.designsystem.components.badge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.dbsystel.designsystem.components.core.DBEmphasis
import com.dbsystel.designsystem.components.core.DBIcon
import com.dbsystel.designsystem.components.core.DBSemantic
import com.dbsystel.designsystem.components.core.DBSize
import com.dbsystel.designsystem.components.core.PaparazziTest
import com.dbsystel.designsystem.components.core.preview.BasePreview
import com.dbsystel.designsystem.components.core.preview.BasePreviewProperties
import com.dbsystel.designsystem.components.core.preview.previewName
import com.dbsystel.designsystem.foundation.R
import com.dbsystel.designsystem.foundation.theme.DBTheme
import org.junit.Test
import com.dbsystel.designsystem.components.badge.preview.previewName as badgePreviewName


class DBBadgeTest : PaparazziTest() {
    @Test
    fun db_badge_test() {
        paparazzi.snapshot {
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
    }
}
