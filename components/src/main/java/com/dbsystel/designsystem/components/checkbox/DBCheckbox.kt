package com.dbsystel.designsystem.components.checkbox

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.components.checkbox.extensions.checkboxColor
import com.dbsystel.designsystem.components.checkbox.extensions.checkboxColorPressed
import com.dbsystel.designsystem.components.checkbox.extensions.checkboxInvertedColor
import com.dbsystel.designsystem.components.checkbox.extensions.checkboxSize
import com.dbsystel.designsystem.components.checkbox.extensions.checkboxTransparentColor
import com.dbsystel.designsystem.components.checkbox.extensions.checkboxTransparentPressedColor
import com.dbsystel.designsystem.components.checkbox.extensions.spacing
import com.dbsystel.designsystem.components.checkbox.extensions.textColor
import com.dbsystel.designsystem.components.checkbox.extensions.textColorPressed
import com.dbsystel.designsystem.components.checkbox.extensions.textStyle
import com.dbsystel.designsystem.components.core.DBSemantic
import com.dbsystel.designsystem.components.core.DBSize
import com.dbsystel.designsystem.components.core.extensions.withAlphaForDisabledState
import com.dbsystel.designsystem.components.core.preview.BasePreview
import com.dbsystel.designsystem.components.core.preview.BasePreviewProperties
import com.dbsystel.designsystem.components.core.preview.previewName
import com.dbsystel.designsystem.components.infotext.DBInfotext
import com.dbsystel.designsystem.foundation.theme.DBTheme
import kotlin.math.max

/**
 * Checkboxes allow users to select multiple options from a list of independent choices or to mark
 * a single option as selected.
 *
 * @param modifier Modifier to be applied to the checkbox.
 * @param checked Controls the checked state of the checkbox.
 * @param indeterminate Controls the indeterminate state of the checkbox. If true, the checkbox will
 * be in an indeterminate state regardless of the value of [checked].
 * @param label The text to be displayed next to the checkbox. If null or blank, no label will be
 * shown.
 * @param showRequiredAsterisk If true, an asterisk will be appended to the label to indicate that
 * the checkbox is required. This has no semantic meaning and is purely visual.
 * @param showLabel Controls whether the label should be displayed.
 * @param size The size of the checkbox, which also determines the text style and spacing.
 * @param validation The validation state of the checkbox, which controls the display of validation
 * messages and colors.
 * @param disabled Controls whether the checkbox is enabled or disabled.
 * @param onClick Lambda to be invoked when the checkbox is clicked.
 *
 * @sample com.dbsystel.designsystem.components.checkbox.preview.DBCheckboxSample
 */
@Composable
fun DBCheckbox(
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    indeterminate: Boolean = false,
    label: String? = null,
    showRequiredAsterisk: Boolean = false,
    showLabel: Boolean = true,
    size: DBSize = DBSize.MEDIUM,
    validation: DBCheckboxValidation = DBCheckboxValidation.NoValidation,
    disabled: Boolean = false,
    onClick: () -> Unit,
) {
    val validationText = remember(validation) {
        when (validation) {
            is DBCheckboxValidation.Invalid -> validation.text
            is DBCheckboxValidation.Valid -> validation.text
            DBCheckboxValidation.NoValidation -> null
        }
    }

    val validationSemantic = remember(validation) {
        when (validation) {
            is DBCheckboxValidation.Invalid -> DBSemantic.CRITICAL
            else -> DBSemantic.SUCCESSFUL
        }
    }

    var lastValidationText by remember { mutableStateOf(validationText) }
    var lastValidationSemantic by remember { mutableStateOf(validationSemantic) }

    if (validationText != null) {
        lastValidationText = validationText
        lastValidationSemantic = validationSemantic
    }

    val triState = remember(indeterminate, checked) {
        when {
            indeterminate -> ToggleableState.Indeterminate
            checked -> ToggleableState.On
            else -> ToggleableState.Off
        }
    }
    var lineHeight by remember { mutableFloatStateOf(0f) }
    var calculatedPadding by remember { mutableStateOf(0.dp) }
    with(LocalDensity.current) {
        calculatedPadding = (size.checkboxSize.toPx() - lineHeight).toDp() / 2f
    }
    val textPadding by remember(calculatedPadding) { derivedStateOf { calculatedPadding } }
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val textColor by animateColorAsState(
        targetValue = if (pressed) validation.textColorPressed else validation.textColor,
        label = "Text color animation",
    )
    val checkboxBackgroundColor by animateColorAsState(
        targetValue = if (pressed) validation.checkboxTransparentPressedColor else validation.checkboxTransparentColor,
        label = "Text color animation",
    )

    val checkboxColor by animateColorAsState(
        targetValue = if (pressed) validation.checkboxColorPressed else validation.checkboxColor,
        label = "Text color animation",
    )

    Column(
        modifier = modifier
            .withAlphaForDisabledState(disabled)
            .triStateToggleable(
                state = triState,
                interactionSource = interactionSource,
                indication = ripple(color = validation.checkboxTransparentPressedColor),
                enabled = !disabled,
                role = Role.Checkbox,
                onClick = onClick,
            ),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(size.spacing),
        ) {
            TriStateCheckbox(
                modifier = Modifier.size(size.checkboxSize),
                state = triState,
                enabled = !disabled,
                interactionSource = interactionSource,
                colors = CheckboxColors(
                    checkedCheckmarkColor = if (indeterminate) checkboxColor else validation.checkboxInvertedColor,
                    uncheckedCheckmarkColor = checkboxColor,
                    checkedBoxColor = if (indeterminate) checkboxBackgroundColor else checkboxColor,
                    uncheckedBoxColor = checkboxBackgroundColor,
                    disabledCheckedBoxColor = checkboxColor,
                    disabledUncheckedBoxColor = checkboxBackgroundColor,
                    disabledIndeterminateBoxColor = checkboxColor,
                    checkedBorderColor = checkboxColor,
                    uncheckedBorderColor = checkboxColor,
                    disabledBorderColor = checkboxColor,
                    disabledUncheckedBorderColor = checkboxColor,
                    disabledIndeterminateBorderColor = checkboxColor,
                ),
                onClick = onClick,
            )

            if (showLabel && !label.isNullOrBlank()) {
                Text(
                    modifier = Modifier.padding(vertical = textPadding),
                    onTextLayout = { layout ->
                        lineHeight = max(0f, layout.getLineBottom(0) - layout.getLineTop(0))
                    },
                    text = "$label${if (showRequiredAsterisk) "*" else ""}",
                    style = size.textStyle,
                    color = textColor,
                )
            }
        }

        AnimatedVisibility(
            visible = validationText != null,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut(),
        ) {
            DBInfotext(
                modifier = Modifier.padding(top = DBTheme.dimensions.spacing.fixed2xs),
                text = lastValidationText ?: "",
                size = DBSize.SMALL,
                semantic = lastValidationSemantic,
            )
        }
    }
}

sealed interface DBCheckboxValidation {
    data class Invalid(val text: String) : DBCheckboxValidation
    data class Valid(val text: String) : DBCheckboxValidation
    data object NoValidation : DBCheckboxValidation
}

@Preview(heightDp = 1400)
@Composable
private fun DBCheckboxPreview() {
    val invalidState = DBCheckboxValidation.Invalid("Invalid Message")
    val validState = DBCheckboxValidation.Valid("Valid Message")
    BasePreview(
        component = "DBCheckbox",
        preview = {
            DBCheckbox(
                label = "Checkbox",
            ) {}
            DBCheckbox(
                label = "Checkbox",
                checked = true,
            ) {}
            DBCheckbox(
                label = "Checkbox",
                validation = invalidState,
            ) {}
            DBCheckbox(
                label = "Checkbox",
                checked = true,
                validation = validState,
            ) {}
        },
        properties = listOf(
            BasePreviewProperties(
                property = "Disabled",
                views = listOf(false, true).map { disabled ->
                    (if (!disabled) "(Def) False" else "True") to {
                        DBCheckbox(
                            label = "Label",
                            checked = false,
                            disabled = disabled,
                        ) {}
                    }
                },
            ),
            BasePreviewProperties(
                property = "Checked",
                views = listOf(false, true).map { checked ->
                    (if (!checked) "(Def) False" else "True") to {
                        DBCheckbox(label = "Label", checked = checked) {}
                    }
                },
            ),
            BasePreviewProperties(
                property = "Indeterminate",
                views = listOf(false, true).map { indeterminate ->
                    (if (!indeterminate) "(Def) False" else "True") to {
                        DBCheckbox(label = "Label", indeterminate = indeterminate) {}
                    }
                },
            ),
            BasePreviewProperties(
                property = "Size",
                views = DBSize.entries.map { size ->
                    size.previewName to {
                        DBCheckbox(label = "Label", size = size) {}
                    }
                },
            ),
            BasePreviewProperties(
                property = "Required",
                views = listOf(false, true).map { required ->
                    (if (!required) "(Def) False" else "True") to {
                        DBCheckbox(label = "Label", showRequiredAsterisk = required) {}
                    }
                },
            ),
            BasePreviewProperties(
                property = "Validation",
                views = listOf(
                    "(Def) No Validation" to { DBCheckbox(label = "Label") {} },
                    "Invalid - Unchecked" to {
                        DBCheckbox(label = "Label", validation = invalidState) {}
                    },
                    "Invalid - Checked" to {
                        DBCheckbox(label = "Label", validation = invalidState, checked = true) {}
                    },
                    "Valid - Unchecked" to {
                        DBCheckbox(label = "Label", validation = validState) {}
                    },
                    "Valid - Checked" to {
                        DBCheckbox(label = "Label", validation = validState, checked = true) {}
                    },
                ),
            ),
            BasePreviewProperties(
                property = "Show Label",
                views = listOf(true, false).map { showLabel ->
                    (if (showLabel) "(Def) True" else "False") to {
                        DBCheckbox(showLabel = showLabel, label = "Label") {}
                    }
                },
            ),
        ),
    )
}
