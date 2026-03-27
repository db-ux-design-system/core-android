package com.dbsystel.designsystem.components.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

enum class DBSemantic {
    ADAPTIVE,
    CRITICAL,
    INFORMATIONAL,
    NEUTRAL,
    SUCCESSFUL,
    WARNING;
}

data class ValidationState(
    val show: Boolean,
    val text: String?,
    val semantic: DBSemantic,
)

@Composable
fun DBValidation.rememberValidationState(
    message: String? = null,
    showMessage: Boolean = false,
): ValidationState {
    val validationText: String? = remember(this, message, showMessage) {
        when (this) {
            is DBValidation.Invalid -> text
            is DBValidation.Valid -> text
            DBValidation.NoValidation -> message.takeIf { showMessage && !it.isNullOrBlank() }
        }
    }

    val validationSemantic = remember(this) {
        when (this) {
            is DBValidation.Invalid -> DBSemantic.CRITICAL
            is DBValidation.Valid -> DBSemantic.SUCCESSFUL
            else -> DBSemantic.ADAPTIVE
        }
    }

    var lastValidationText by remember { mutableStateOf(validationText) }
    var lastValidationSemantic by remember { mutableStateOf(validationSemantic) }

    if (validationText != null) {
        lastValidationText = validationText
        lastValidationSemantic = validationSemantic
    }

    return ValidationState(
        show = validationText != null,
        text = lastValidationText,
        semantic = lastValidationSemantic,
    )
}

