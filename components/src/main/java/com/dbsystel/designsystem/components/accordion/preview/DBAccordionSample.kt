package com.dbsystel.designsystem.components.accordion.preview

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dbsystel.designsystem.components.accordion.DBAccordion
import com.dbsystel.designsystem.components.accordion.DBAccordionBehavior
import com.dbsystel.designsystem.components.accordion.DBAccordionItem
import com.dbsystel.designsystem.components.accordion.DBAccordionVariant

@Preview
@Composable
private fun DBAccordionSample() {
    DBAccordion(
        items = listOf(
            DBAccordionItem(
                title = "Item 1",
                content = { Text("Content for Item 1") },
            ),
            DBAccordionItem(
                title = "Item 2",
                content = { Text("Content for Item 2") },
            ),
            DBAccordionItem(
                title = "Item 3",
                content = { Text("Content for Item 3") },
            ),
        ),
        variant = DBAccordionVariant.CARD,
        behavior = DBAccordionBehavior.SINGLE,
    )
}
