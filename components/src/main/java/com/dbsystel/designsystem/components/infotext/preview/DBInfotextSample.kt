package com.dbsystel.designsystem.components.infotext.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dbsystel.designsystem.components.core.DBSemantic
import com.dbsystel.designsystem.components.core.DBSize
import com.dbsystel.designsystem.components.infotext.DBInfotext

@Preview
@Composable
private fun DBInfotextSample() {
    DBInfotext(
        text = "Invalid data",
        semantic = DBSemantic.CRITICAL,
        size = DBSize.SMALL,
        showIcon = true,
    )
}
