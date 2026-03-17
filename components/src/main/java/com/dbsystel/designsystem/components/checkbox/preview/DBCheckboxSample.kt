package com.dbsystel.designsystem.components.checkbox.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dbsystel.designsystem.components.checkbox.DBCheckbox
import com.dbsystel.designsystem.components.checkbox.DBCheckboxValidation
import com.dbsystel.designsystem.components.core.DBSize

@Preview
@Composable
private fun DBCheckboxSample() {
    DBCheckbox(
        checked = true,
        indeterminate = false,
        label = "Happy",
        showRequiredAsterisk = true,
        showLabel = true,
        size = DBSize.MEDIUM,
        validation = DBCheckboxValidation.Valid("Looks good!"),
        disabled = false,
        onClick = { /* Do something! */ },
    )
}
