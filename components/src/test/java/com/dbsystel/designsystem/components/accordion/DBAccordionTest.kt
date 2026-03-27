package com.dbsystel.designsystem.components.accordion

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.dbsystel.designsystem.components.accordion.preview.previewName
import com.dbsystel.designsystem.components.core.PaparazziTest
import com.dbsystel.designsystem.components.core.preview.BasePreview
import com.dbsystel.designsystem.components.core.preview.BasePreviewProperties
import com.dbsystel.designsystem.foundation.theme.DBTheme
import org.junit.Test


class DBAccordionTest : PaparazziTest() {
    @Test
    fun test_component_dbaccordion() {
        paparazzi.snapshot {
            val itemPreview = DBAccordionItem(
                title = "Accordion Item",
                content = {
                    Text(
                        text = "Lorem Ipsum",
                        modifier = Modifier.fillMaxWidth(),
                        style = DBTheme.typography.bodyMd,
                        color = DBTheme.activeColor.onBgBasicEmphasis100Default,
                    )
                },
            )
            val itemProperties = DBAccordionItem(
                title = "Headline",
                content = {
                    Text(
                        text = "Lorem Ipsum",
                        modifier = Modifier.fillMaxWidth(),
                        style = DBTheme.typography.bodyMd,
                        color = DBTheme.activeColor.onBgBasicEmphasis100Default,
                    )
                },
            )
            val itemsPreview = listOf(itemPreview, itemPreview, itemPreview)
            val itemsProperties = listOf(itemProperties, itemProperties, itemProperties)
            BasePreview(
                component = "DBAccordion",
                preview = {
                    DBAccordion(
                        items = itemsPreview,
                    )
                    DBAccordion(
                        variant = DBAccordionVariant.CARD,
                        items = itemsPreview,
                    )
                },
                properties = listOf(
                    BasePreviewProperties(
                        property = "Variant",
                        views = DBAccordionVariant.entries.map { variant ->
                            variant.previewName to {
                                DBAccordion(
                                    items = itemsProperties,
                                    variant = variant,
                                )
                            }
                        }
                    ),
                    BasePreviewProperties(
                        property = "Disabled",
                        views = listOf(false, true).map { disabled ->
                            (if (!disabled) "(Def) False" else "True") to {
                                DBAccordion(
                                    items = itemsProperties,
                                    disabled = disabled,
                                )
                            }
                        }
                    ),
                ),
            )
        }
    }
}
