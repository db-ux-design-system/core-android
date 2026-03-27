package com.dbsystel.designsystem.components.core


sealed interface DBValidation {
    data class Invalid(val text: String) : DBValidation
    data class Valid(val text: String) : DBValidation
    data object NoValidation : DBValidation
}
