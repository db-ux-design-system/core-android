package com.dbsystel.designsystem.components.core.preview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.components.card.DBCard
import com.dbsystel.designsystem.components.card.DBCardSpacing
import com.dbsystel.designsystem.foundation.R
import com.dbsystel.designsystem.foundation.theme.DBTheme

internal data class BasePreviewProperties(
    val property: String,
    val views: List<Pair<String, @Composable () -> Unit>>,
)

internal data class BasePreviewExamples(
    val property: String,
    val views: List<@Composable () -> Unit>,
)

@Composable
internal fun BasePreview(
    component: String,
    preview: @Composable RowScope.() -> Unit,
    properties: List<BasePreviewProperties>,
    examples: List<BasePreviewExamples> = emptyList(),
) {
    DBTheme {
        Column(
            modifier = Modifier
                .background(DBTheme.activeColor.Basic.Background.Level1.Default)
                .padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedXl),
        ) {
            Text(
                text = component,
                style = DBTheme.typography.h1,
            )
            SectionPreview(preview)
            SectionProperties(properties)
            SectionExamples(examples)
        }
    }
}

@Composable
private fun SectionPreview(
    preview: @Composable RowScope.() -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedMd),
    ) {
        Text(
            text = "\uD83D\uDC41\uFE0F Preview",
            style = DBTheme.typography.h3,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    DBTheme.dimensions.border.width3xs,
                    DBTheme.activeColor.Basic.Border.Default.Default,
                    RoundedCornerShape(DBTheme.dimensions.border.radiusSm),
                )
                .clip(RoundedCornerShape(DBTheme.dimensions.border.radiusSm)),
        ) {
            listOf(false, true).forEach { darkTheme ->
                DBTheme(
                    darkTheme = darkTheme
                ) {
                    Row(
                        modifier = Modifier
                            .background(DBTheme.activeColor.Basic.Background.Level1.Default)
                            .padding(DBTheme.dimensions.spacing.fixedSm),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedMd)
                    ) {
                        Image(
                            modifier = Modifier.size(32.dp),
                            painter = painterResource(if (darkTheme) R.drawable.preview_dark else R.drawable.preview_light),
                            contentDescription = null,
                        )
                        Spacer(Modifier.weight(1f))

                        FlowRow(
                            verticalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedMd),
                            horizontalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedMd),
                        ) {
                            preview()
                        }
                        Spacer(Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionProperties(
    properties: List<BasePreviewProperties>,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedMd),
    ) {
        Text(
            text = "\uD83D\uDEE0\uFE0F Properties",
            style = DBTheme.typography.h3,
        )
        properties.forEach { (property, views) ->
            Column(
                verticalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixed2xs),
            ) {
                Text(
                    text = property,
                    style = DBTheme.typography.bodySm,
                )
                DBCard(
                    modifier = Modifier.fillMaxWidth(),
                    spacing = DBCardSpacing.SMALL,
                ) {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedXl),
                        verticalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedMd),
                    ) {
                        views.forEach { (title, view) ->
                            Column(
                                verticalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedXs),
                            ) {
                                Text(
                                    text = title,
                                    style = DBTheme.typography.bodyXs,
                                    color = DBTheme.colors.blue.Basic.Text.Emphasis100.Default,
                                )
                                view()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionExamples(
    examples: List<BasePreviewExamples>,
) {
    if (examples.isEmpty()) return
    Column(
        verticalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedMd),
    ) {
        Text(
            text = "✨ Examples",
            style = DBTheme.typography.h3,
        )
        examples.forEach { (property, views) ->
            Column(
                verticalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixed2xs),
            ) {
                Text(
                    text = property,
                    style = DBTheme.typography.bodySm,
                )
                DBCard(
                    modifier = Modifier.fillMaxWidth(),
                    spacing = DBCardSpacing.SMALL,
                ) {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedXl),
                        verticalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedMd),
                    ) {
                        views.forEach { view ->
                            view()
                        }
                    }
                }
            }
        }
    }
}
