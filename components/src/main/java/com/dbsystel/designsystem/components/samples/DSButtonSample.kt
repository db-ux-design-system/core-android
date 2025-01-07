package com.dbsystel.designsystem.components.samples

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.dbsystel.designsystem.components.button.DSButton
import com.dbsystel.designsystem.components.button.DSButtonIcon
import com.dbsystel.designsystem.components.button.DSButtonSize
import com.dbsystel.designsystem.components.button.DSButtonVariant
import com.dbsystel.designsystem.components.button.DSButtonWidth
import com.dbsystel.designsystem.foundation.R

@Preview
@Composable
private fun DSButtonSample() {
    DSButton(
        text = "Login",
        icon = DSButtonIcon(
            imageVector = ImageVector.vectorResource(R.drawable.sample_vector),
            contentDescription = "Login Icon",
        ),
        variant = DSButtonVariant.BRAND,
        size = DSButtonSize.MEDIUM,
        enabled = true,
        width = DSButtonWidth.AUTO,
        onClick = { /* Do something! */ },
    )
}
