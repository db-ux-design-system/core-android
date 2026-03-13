package com.dbsystel.designsystem.components.badge.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dbsystel.designsystem.components.badge.DBBadge
import com.dbsystel.designsystem.components.badge.DBBadgeContent
import com.dbsystel.designsystem.components.core.DBEmphasis
import com.dbsystel.designsystem.components.core.DBSemantic
import com.dbsystel.designsystem.components.core.DBSize

@Preview
@Composable
private fun DBBadgeSample() {
    DBBadge(
        content = DBBadgeContent.Text("Text"),
        emphasis = DBEmphasis.STRONG,
        semantic = DBSemantic.INFORMATIONAL,
        size = DBSize.MEDIUM,
    )
}
