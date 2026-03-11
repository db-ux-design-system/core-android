package com.dbsystel.designsystem.components.link.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dbsystel.designsystem.components.core.DBSize
import com.dbsystel.designsystem.components.link.DBLink
import com.dbsystel.designsystem.components.link.DBLinkContent
import com.dbsystel.designsystem.components.link.DBLinkVariant

@Preview
@Composable
private fun DBLinkSample() {
    DBLink(
        text = "More",
        variant = DBLinkVariant.BRAND,
        size = DBSize.MEDIUM,
        content = DBLinkContent.INTERNAL,
        disabled = false,
        showIcon = true,
        onClick = { /* Do something! */ },
    )
}
