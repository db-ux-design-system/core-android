package com.dbsystel.designsystem.components.switch.preview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.dbsystel.designsystem.components.R
import com.dbsystel.designsystem.components.core.DBValidation
import com.dbsystel.designsystem.components.switch.DBSwitch
import com.dbsystel.designsystem.components.switch.DBSwitchVariant

@Preview
@Composable
private fun DBSwitchSample() {
    val checked by remember { mutableStateOf(false) }

    DBSwitch(
        checked = checked,
        label = "Label",
        validation = DBValidation.Valid("Success!"),
        variant = DBSwitchVariant.TRAILING,
        message = "This is a message.",
        showMessage = true,
        visualAid = true,
        iconTrailing = ImageVector.vectorResource(R.drawable.dbux_ic_cross),
        onCheckedChange = { /* Handle checked change */ },
    )
}
