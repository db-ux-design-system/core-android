package com.dbsystel.designsystem.components.button.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.dbsystel.designsystem.components.button.DBButton
import com.dbsystel.designsystem.components.button.DBButtonVariant
import com.dbsystel.designsystem.components.core.DBIcon
import com.dbsystel.designsystem.components.core.DBSize
import com.dbsystel.designsystem.foundation.R

@Preview
@Composable
private fun DBButtonSample() {
    DBButton(
        text = "Login",
        icon = DBIcon(
            imageVector = ImageVector.vectorResource(R.drawable.sample_vector),
            contentDescription = "Login Icon",
        ),
        variant = DBButtonVariant.BRAND,
        size = DBSize.MEDIUM,
        disabled = false,
        onClick = { /* Do something! */ },
    )
}
