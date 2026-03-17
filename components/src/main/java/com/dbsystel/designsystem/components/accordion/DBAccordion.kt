package com.dbsystel.designsystem.components.accordion

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.dbsystel.designsystem.components.R
import com.dbsystel.designsystem.components.accordion.preview.previewName
import com.dbsystel.designsystem.components.core.preview.BasePreview
import com.dbsystel.designsystem.components.core.preview.BasePreviewProperties
import com.dbsystel.designsystem.components.core.withAlphaForDisabledState
import com.dbsystel.designsystem.foundation.theme.DBTheme

/**
 * The accordion, consisting of the accordion item as well as a divider or a card, enables a
 * space-saving presentation of content through compact expanding and collapsing.
 *
 * @param modifier Modifier to be applied to the accordion.
 * @param items List of [DBAccordionItem] representing the individual items in the accordion.
 * @param variant The visual style of the accordion.
 * @param behavior The behavior of the accordion regarding how many items can be opened
 * simultaneously.
 * @param disabled Whether the accordion is disabled, preventing user interaction and applying a
 * visual indication of the disabled state.
 *
 * @sample com.dbsystel.designsystem.components.accordion.preview.DBAccordionSample
 */
@Composable
fun DBAccordion(
    modifier: Modifier = Modifier,
    items: List<DBAccordionItem>,
    variant: DBAccordionVariant = DBAccordionVariant.DIVIDER,
    behavior: DBAccordionBehavior = DBAccordionBehavior.MULTIPLE,
    disabled: Boolean = false,
) {
    var openedItems by rememberSaveable(items, behavior) {
        mutableStateOf(setOf<Int>())
    }

    val shape = RoundedCornerShape(DBTheme.dimensions.border.radiusSm)

    Column(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .withAlphaForDisabledState(disabled),
        verticalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedSm),
    ) {
        items.forEachIndexed { index, (title, content) ->
            val chevronAngle by animateFloatAsState(
                targetValue = if (openedItems.contains(index)) 180f else 0f,
                label = "Chevron rotation for index $index",
            )
            Column(
                modifier = if (variant == DBAccordionVariant.CARD)
                    Modifier
                        .border(
                            width = DBTheme.dimensions.border.width3xs,
                            color = DBTheme.activeColor.onBgBasicEmphasis60Default,
                            shape = shape,
                        )
                        .clip(shape)
                else Modifier
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(
                            if (disabled)
                                Modifier
                            else
                                Modifier
                                    .clickable(
                                        interactionSource = null,
                                        indication = ripple(color = DBTheme.activeColor.bgBasicTransparentFullPressed),
                                        onClick = {
                                            openedItems = when {
                                                openedItems.contains(index) -> {
                                                    openedItems.minus(index)
                                                }

                                                behavior == DBAccordionBehavior.SINGLE -> {
                                                    setOf(index)
                                                }

                                                else -> {
                                                    openedItems.plus(index)
                                                }
                                            }
                                        },
                                    )
                        )
                        .padding(DBTheme.dimensions.spacing.fixedMd),
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedMd),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = title,
                            style = DBTheme.typography.bodyMd,
                            color = DBTheme.activeColor.Basic.Text.Default.Default,
                        )
                        Icon(
                            modifier = Modifier.graphicsLayer(rotationZ = chevronAngle),
                            imageVector = ImageVector.vectorResource(R.drawable.dbux_ic_chevron_vertical),
                            contentDescription = null,
                            tint = DBTheme.activeColor.onBgBasicEmphasis100Default,
                        )
                    }
                }

                AnimatedVisibility(
                    visible = openedItems.contains(index),
                    enter = expandVertically() + fadeIn(),
                    exit = shrinkVertically() + fadeOut(),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = DBTheme.dimensions.spacing.fixedMd)
                            .padding(
                                top = DBTheme.dimensions.spacing.fixedMd,
                                bottom = DBTheme.dimensions.spacing.fixedLg,
                            ),
                    ) {
                        content()
                    }
                }
            }
            if (variant == DBAccordionVariant.DIVIDER && index < items.size - 1) {
                HorizontalDivider()
            }
        }
    }
}

data class DBAccordionItem(
    val title: String,
    val content: @Composable ColumnScope.() -> Unit,
)

enum class DBAccordionVariant {
    DIVIDER,
    CARD;
}

enum class DBAccordionBehavior {
    MULTIPLE,
    SINGLE;
}

@Preview(widthDp = 500, heightDp = 1400)
@Composable
private fun DBAccordionPreview() {
    val itemPreview = DBAccordionItem(
        title = "Accordion Item",
        content = {
            Text(
                text = "Lorem Ipsum",
                modifier = Modifier.fillMaxWidth(),
                style = DBTheme.typography.bodyMd,
                color = DBTheme.activeColor.onBgBasicEmphasis100Default,
            )
        },
    )
    val itemProperties = DBAccordionItem(
        title = "Headline",
        content = {
            Text(
                text = "Lorem Ipsum",
                modifier = Modifier.fillMaxWidth(),
                style = DBTheme.typography.bodyMd,
                color = DBTheme.activeColor.onBgBasicEmphasis100Default,
            )
        },
    )
    val itemsPreview = listOf(itemPreview, itemPreview, itemPreview)
    val itemsProperties = listOf(itemProperties, itemProperties, itemProperties)
    BasePreview(
        component = "DBAccordion",
        preview = {
            DBAccordion(
                items = itemsPreview,
            )
            DBAccordion(
                variant = DBAccordionVariant.CARD,
                items = itemsPreview,
            )
        },
        properties = listOf(
            BasePreviewProperties(
                property = "Variant",
                views = DBAccordionVariant.entries.map { variant ->
                    variant.previewName to {
                        DBAccordion(
                            items = itemsProperties,
                            variant = variant,
                        )
                    }
                }
            ),
            BasePreviewProperties(
                property = "Disabled",
                views = listOf(false, true).map { disabled ->
                    (if (!disabled) "(Def) False" else "True") to {
                        DBAccordion(
                            items = itemsProperties,
                            disabled = disabled,
                        )
                    }
                }
            ),
        ),
    )
}
