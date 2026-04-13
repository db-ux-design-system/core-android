package com.dbsystel.designsystem.components.card

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.components.card.extensions.color
import com.dbsystel.designsystem.components.card.extensions.colorPressed
import com.dbsystel.designsystem.components.card.extensions.padding
import com.dbsystel.designsystem.components.card.preview.previewName
import com.dbsystel.designsystem.components.card.preview.previewNameShort
import com.dbsystel.designsystem.components.core.preview.BasePreview
import com.dbsystel.designsystem.components.core.preview.BasePreviewProperties
import com.dbsystel.designsystem.foundation.theme.DBTheme

/**
 * The card components are shaded and rounded areas that give the interface depth.
 * Whether static or interactive - the cards are the basis for further components and modules and
 * can display content and actions.
 *
 * @param modifier Modifier to be applied to the card.
 * @param elevation The elevation level of the card, which determines its background color.
 * @param spacing The spacing level of the card, which determines the padding inside the card.
 * @param behavior The behavior of the card, which determines whether it is static or interactive.
 * @param onClick The callback to be invoked when the card is clicked. The card is only clickable if
 * the behavior is set to interactive and onClick is not null.
 * @param content The content of the card in a ColumnScope as a receiver.
 *
 * @sample com.dbsystel.designsystem.components.card.preview.DBCardSample
 */
@Composable
fun DBCard(
    modifier: Modifier = Modifier,
    elevation: DBCardElevation = DBCardElevation.LEVEL_1,
    spacing: DBCardSpacing = DBCardSpacing.SMALL,
    behavior: DBCardBehavior = DBCardBehavior.STATIC,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val shape = RoundedCornerShape(DBTheme.dimensions.border.radiusSm)
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val backgroundColor by animateColorAsState(
        targetValue = if (pressed) elevation.colorPressed else elevation.color,
        label = "CardBackgroundColoAnimation"
    )
    Surface(
        color = backgroundColor,
        shape = shape,
        contentColor = DBTheme.activeColor.Basic.Text.Default.Default,
        tonalElevation = 0.dp,
        modifier = modifier
            .border(
                width = DBTheme.dimensions.border.width3xs,
                color = DBTheme.activeColor.Basic.Border.Default.Default,
                shape = shape,
            )
            .then(
                if (behavior == DBCardBehavior.INTERACTIVE && onClick != null) {
                    Modifier.clickable(
                        role = Role.Button,
                        interactionSource = interactionSource,
                        indication = ripple(color = elevation.colorPressed),
                        onClick = { onClick() },
                    )
                } else Modifier
            ),
    ) {
        Column(modifier = Modifier.padding(spacing.padding)) {
            content()
        }
    }
}

enum class DBCardSpacing {
    SMALL,
    MEDIUM,
    LARGE,
    NONE;
}

enum class DBCardElevation {
    LEVEL_1,
    LEVEL_2,
    LEVEL_3;
}

enum class DBCardBehavior {
    STATIC,
    INTERACTIVE;
}

@Preview(heightDp = 1000)
@Composable
private fun DBCardPreview() {
    val cardSize = 90.dp

    BasePreview(
        component = "DBCard",
        preview = {
            DBCardElevation.entries.forEach { elevation ->
                DBCard(modifier = Modifier.width(cardSize), elevation = elevation) { }
            }
        },
        properties = listOf(
            BasePreviewProperties(
                property = "Elevation Level",
                views = DBCardElevation.entries.map { elevation ->
                    elevation.previewName to {
                        DBCard(modifier = Modifier.size(cardSize), elevation = elevation) { }
                    }
                }
            ),
            BasePreviewProperties(
                property = "Spacing",
                views = DBCardSpacing.entries.map { spacing ->
                    spacing.previewName to {
                        DBCard(modifier = Modifier.size(cardSize), spacing = spacing) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color(0x52e700eb))
                            ) {
                                Text(
                                    text = spacing.previewNameShort,
                                    color = Color(0xFFD600DB),
                                )
                            }
                        }
                    }
                }
            ),
            BasePreviewProperties(
                property = "Behavior",
                views = DBCardBehavior.entries.map { behavior ->
                    behavior.previewName to {
                        DBCard(
                            modifier = Modifier.size(cardSize),
                            behavior = behavior,
                            onClick = {},
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize(),
                            ) { }
                        }
                    }
                }
            ),
        ),
    )
}
