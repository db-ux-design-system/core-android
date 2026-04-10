package com.dbsystel.designsystem.components.switch

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.dbsystel.designsystem.components.R
import com.dbsystel.designsystem.components.core.DBSize
import com.dbsystel.designsystem.components.core.DBValidation
import com.dbsystel.designsystem.components.core.extensions.textColor
import com.dbsystel.designsystem.components.core.extensions.textStyle
import com.dbsystel.designsystem.components.core.extensions.withAlphaForDisabledState
import com.dbsystel.designsystem.components.core.preview.BasePreview
import com.dbsystel.designsystem.components.core.preview.BasePreviewExamples
import com.dbsystel.designsystem.components.core.preview.BasePreviewProperties
import com.dbsystel.designsystem.components.core.preview.previewName
import com.dbsystel.designsystem.components.core.rememberValidationState
import com.dbsystel.designsystem.components.infotext.DBInfotext
import com.dbsystel.designsystem.components.switch.extensions.animatedBackgroundColor
import com.dbsystel.designsystem.components.switch.extensions.animatedBorderColor
import com.dbsystel.designsystem.components.switch.extensions.animatedThumbColor
import com.dbsystel.designsystem.components.switch.extensions.animatedThumbSize
import com.dbsystel.designsystem.components.switch.preview.previewName
import com.dbsystel.designsystem.foundation.theme.DBTheme

/**
 * Switches allow users to toggle between two states, such as turning something on or off. They are
 * often used to enable or disable settings and provide an easy and quick way to interact.
 *
 * @param modifier Modifier to be applied to the switch.
 * @param checked Whether the switch is currently checked (on) or not (off).
 * @param label Optional text label to describe the purpose of the switch.
 * @param showLabel Whether to display the label or not.
 * @param validation Validation state of the switch, which can affect its appearance and behavior.
 * @param disabled Whether the switch is disabled and non-interactive.
 * @param required Whether the switch is required, which can be indicated in the label.
 * @param variant The position of the label relative to the switch (leading or trailing).
 * @param message Optional message to display below the switch. Will be replaced by validation messages.
 * @param showMessage Whether to display the message or not.
 * @param size The size of the switch, which can affect its dimensions and thumb size.
 * @param visualAid Whether to show visual aid icons inside the switch when checked/unchecked.
 * @param iconTrailing Optional custom icon to display when the switch is checked (overrides default checkmark).
 * @param iconLeading Optional custom icon to display when the switch is unchecked (overrides default cross).
 * @param onCheckedChange Callback function that is invoked when the checked state changes.
 *
 * @sample com.dbsystel.designsystem.components.switch.preview.DBSwitchSample
 */
@Composable
fun DBSwitch(
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    label: String? = null,
    showLabel: Boolean = true,
    validation: DBValidation = DBValidation.NoValidation,
    disabled: Boolean = false,
    required: Boolean = false,
    variant: DBSwitchVariant = DBSwitchVariant.TRAILING,
    message: String? = null,
    showMessage: Boolean = false,
    size: DBSize = DBSize.MEDIUM,
    visualAid: Boolean = false,
    iconTrailing: ImageVector? = null,
    iconLeading: ImageVector? = null,
    onCheckedChange: ((Boolean) -> Unit),
) {
    val validationState = validation.rememberValidationState(
        message = message,
        showMessage = showMessage,
    )

    Column(
        modifier = modifier.withAlphaForDisabledState(disabled),
    ) {
        DBSwitchWithLabel(
            checked = checked,
            label = label,
            disabled = disabled,
            required = required,
            validation = validation,
            size = size,
            visualAid = visualAid,
            iconTrailing = iconTrailing,
            iconLeading = iconLeading,
            showLabelLeading = showLabel && variant == DBSwitchVariant.LEADING,
            showLabelTrailing = showLabel && variant == DBSwitchVariant.TRAILING,
            onCheckedChange = onCheckedChange,
        )

        AnimatedVisibility(
            visible = validationState.show,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut(),
        ) {
            DBInfotext(
                modifier = Modifier.padding(top = DBTheme.dimensions.spacing.fixed2xs),
                text = validationState.text ?: "",
                size = DBSize.SMALL,
                semantic = validationState.semantic,
            )
        }
    }
}

enum class DBSwitchVariant {
    TRAILING,
    LEADING,
}

@Composable
private fun DBSwitchWithLabel(
    checked: Boolean,
    label: String?,
    disabled: Boolean,
    required: Boolean,
    validation: DBValidation,
    size: DBSize,
    visualAid: Boolean,
    iconTrailing: ImageVector?,
    iconLeading: ImageVector?,
    showLabelLeading: Boolean,
    showLabelTrailing: Boolean,
    onCheckedChange: ((Boolean) -> Unit),
) {

    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    val switchShape = RoundedCornerShape(percent = 100)

    val borderColor by validation.animatedBorderColor(checked, pressed)
    val backgroundColor by validation.animatedBackgroundColor(checked, pressed)
    val thumbColor by validation.animatedThumbColor(checked)

    val thumbSize by size.animatedThumbSize(checked)

    val thumbOffset by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        label = "Switch Thumb Offset",
    )

    val switchSize = remember(size) {
        when (size) {
            DBSize.MEDIUM -> DpSize(46.dp, 24.dp)
            DBSize.SMALL -> DpSize(38.dp, 20.dp)
        }
    }

    Row(
        modifier = Modifier
            .height(switchSize.height)
            .minimumInteractiveComponentSize()
            .toggleable(
                value = checked,
                onValueChange = onCheckedChange,
                enabled = !disabled,
                role = Role.Switch,
                interactionSource = interactionSource,
                indication = null,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedXs),
    ) {
        if (showLabelLeading && !label.isNullOrBlank()) {
            Text(
                text = "$label${if (required) "*" else ""}",
                style = size.textStyle,
                color = validation.textColor(pressed = pressed),
            )
        }
        Box(
            modifier = Modifier
                .size(switchSize)
                .border(
                    width = DBTheme.dimensions.border.width2xs,
                    color = borderColor,
                    shape = switchShape,
                )
                .background(
                    color = backgroundColor,
                    shape = switchShape,
                ),
        ) {
            VisualAidIcons(
                checked = checked,
                visualAid = visualAid,
                thumbColor = thumbColor,
                iconTrailing = iconTrailing,
                iconLeading = iconLeading,
            )
            Box(
                modifier = Modifier
                    .padding(start = 4.dp, end = 2.dp)
                    .align(Alignment.CenterStart)
                    .graphicsLayer {
                        val trackWidthPx = switchSize.width.toPx()
                        val thumbSizePx = thumbSize.toPx()
                        val paddingPx = 6.dp.toPx()
                        val maxOffset = trackWidthPx - thumbSizePx - paddingPx
                        translationX = thumbOffset * maxOffset
                    }
                    .size(thumbSize)
                    .background(thumbColor, CircleShape)
                    .indication(
                        interactionSource = interactionSource,
                        indication = ripple(
                            bounded = false,
                            radius = (switchSize.height + 8.dp) / 2,
                            color = DBTheme.activeColor.onBgBasicEmphasis100Default,
                        ),
                    ),
            )
        }
        if (showLabelTrailing && !label.isNullOrBlank()) {
            Text(
                text = "$label${if (required) "*" else ""}",
                style = size.textStyle,
                color = validation.textColor(pressed = pressed),
            )
        }
    }
}

@Composable
private fun VisualAidIcons(
    checked: Boolean,
    visualAid: Boolean,
    thumbColor: Color,
    iconTrailing: ImageVector?,
    iconLeading: ImageVector?,
) {
    if (visualAid) {
        Row(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixed3xs),
        ) {
            if (checked) {
                Icon(
                    imageVector = iconTrailing
                        ?: ImageVector.vectorResource(R.drawable.dbux_ic_checkmark),
                    contentDescription = null,
                    tint = thumbColor,
                )
            } else Spacer(modifier = Modifier.weight(1f))
            if (!checked) {
                Icon(
                    imageVector = iconLeading
                        ?: ImageVector.vectorResource(R.drawable.dbux_ic_cross),
                    contentDescription = null,
                    tint = thumbColor,
                )
            } else Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(widthDp = 800, heightDp = 1800)
@Composable
private fun DBSwitchPreview() {
    val invalidState = DBValidation.Invalid("Invalid Message")
    val validState = DBValidation.Valid("Valid Message")
    var previewHeight by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    BasePreview(
        component = "DBSwitch",
        preview = {
            listOf(
                DBValidation.NoValidation,
                invalidState,
                validState,
            ).forEach { validation ->
                Column(
                    modifier = Modifier
                        .onGloballyPositioned { coordinates ->
                            with(density) {
                                previewHeight = max(previewHeight, coordinates.size.height.toDp())
                            }
                        }
                        .heightIn(min = previewHeight),
                    verticalArrangement = Arrangement.spacedBy(
                        DBTheme.dimensions.spacing.fixedXs,
                        Alignment.CenterVertically
                    ),
                ) {
                    DBSwitch(
                        label = "Label",
                        validation = validation,
                        visualAid = true,
                        onCheckedChange = { _ -> },
                    )
                    DBSwitch(
                        checked = true,
                        label = "Label",
                        visualAid = true,
                        validation = validation,
                        onCheckedChange = { _ -> },
                    )
                }
            }
        },
        properties = listOf(
            BasePreviewProperties(
                property = "Variant",
                views = DBSwitchVariant.entries.map { variant ->
                    variant.previewName to {
                        DBSwitch(
                            label = "Label",
                            variant = variant,
                            onCheckedChange = { _ -> },
                        )
                    }
                }
            ),
            BasePreviewProperties(
                property = "Disabled",
                views = listOf(false, true).map { disabled ->
                    (if (!disabled) "(Def) False" else "True") to {
                        DBSwitch(
                            label = "Label",
                            disabled = disabled,
                            onCheckedChange = { _ -> },
                        )
                    }
                }
            ),
            BasePreviewProperties(
                property = "Checked",
                views = listOf(false, true).map { checked ->
                    (if (!checked) "(Def) False" else "True") to {
                        DBSwitch(
                            label = "Label",
                            checked = checked,
                            onCheckedChange = { _ -> },
                        )
                    }
                }
            ),
            BasePreviewProperties(
                property = "Validation",
                views = buildList {
                    add("(Def) No Validation" to { DBSwitch(onCheckedChange = { }) })
                    add("Invalid - Unchecked" to {
                        DBSwitch(
                            validation = invalidState,
                            onCheckedChange = { })
                    })
                    add("Invalid - Checked" to {
                        DBSwitch(
                            validation = invalidState,
                            checked = true,
                            onCheckedChange = { },
                        )
                    })
                    add("Valid - Unchecked" to {
                        DBSwitch(
                            validation = validState,
                            onCheckedChange = { })
                    })
                    add("Valid - Checked" to {
                        DBSwitch(
                            validation = validState,
                            checked = true,
                            onCheckedChange = { },
                        )
                    })
                }
            ),
            BasePreviewProperties(
                property = "Visual Aid",
                views = listOf(false, true).map { visualAid ->
                    (if (!visualAid) "(Def) False" else "True") to {
                        Column(verticalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedSm)) {
                            DBSwitch(
                                label = "Label",
                                checked = false,
                                visualAid = visualAid,
                                onCheckedChange = { _ -> },
                            )
                            DBSwitch(
                                label = "Label",
                                checked = true,
                                visualAid = visualAid,
                                onCheckedChange = { _ -> },
                            )
                        }
                    }
                }
            ),
            BasePreviewProperties(
                property = "Size",
                views = DBSize.entries.map { size ->
                    size.previewName to {
                        DBSwitch(
                            label = "Label",
                            size = size,
                            onCheckedChange = { _ -> },
                        )
                    }
                }
            ),
            BasePreviewProperties(
                property = "Required",
                views = listOf(false, true).map { required ->
                    (if (!required) "(Def) False" else "True") to {
                        DBSwitch(
                            label = "Label",
                            required = required,
                            onCheckedChange = { _ -> },
                        )
                    }
                }
            ),
            BasePreviewProperties(
                property = "Show Label",
                views = listOf(true, false).map { showLabel ->
                    (if (showLabel) "(Def) True" else "False") to {
                        DBSwitch(
                            label = "Label",
                            showLabel = showLabel,
                            onCheckedChange = { _ -> },
                        )
                    }
                }
            ),
            BasePreviewProperties(
                property = "Show Message",
                views = listOf(false, true).map { showMessage ->
                    (if (!showMessage) "(Def) False" else "True") to {
                        DBSwitch(
                            label = "Label",
                            showMessage = showMessage,
                            message = "Message",
                            onCheckedChange = { _ -> },
                        )
                    }
                }
            ),
        ),
        examples = listOf(
            BasePreviewExamples(
                property = "Custom Icons",
                views = listOf(
                    {
                        DBSwitch(
                            label = "Label",
                            visualAid = true,
                            iconLeading = ImageVector.vectorResource(com.dbsystel.designsystem.foundation.R.drawable.preview_dark),
                            onCheckedChange = { _ -> },
                        )
                    },
                    {
                        DBSwitch(
                            label = "Label",
                            visualAid = true,
                            checked = true,
                            iconTrailing = ImageVector.vectorResource(com.dbsystel.designsystem.foundation.R.drawable.preview_light),
                            onCheckedChange = { _ -> },
                        )
                    }
                )
            ),
        )
    )
}
