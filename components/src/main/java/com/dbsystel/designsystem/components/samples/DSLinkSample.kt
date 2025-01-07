package com.dbsystel.designsystem.components.samples

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dbsystel.designsystem.components.link.DSLink
import com.dbsystel.designsystem.components.link.DSLinkContent
import com.dbsystel.designsystem.components.link.DSLinkSize
import com.dbsystel.designsystem.components.link.DSLinkVariant

@Preview
@Composable
private fun DSLinkSample() {
    DSLink(
        text = "More",
        variant = DSLinkVariant.BRAND,
        size = DSLinkSize.MEDIUM,
        content = DSLinkContent.INTERNAL,
        enabled = true,
        showIcon = true,
        onClick = { /* Do something! */ },
    )
}
