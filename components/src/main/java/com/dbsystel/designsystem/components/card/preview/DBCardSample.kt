package com.dbsystel.designsystem.components.card.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dbsystel.designsystem.components.card.DBCard
import com.dbsystel.designsystem.components.card.DBCardElevation
import com.dbsystel.designsystem.components.card.DBCardSpacing

@Preview
@Composable
private fun DBCardSample() {
    DBCard(
        elevation = DBCardElevation.LEVEL_2,
        spacing = DBCardSpacing.LARGE,
        content = { /* Content of the card goes here */ },
    )
}
