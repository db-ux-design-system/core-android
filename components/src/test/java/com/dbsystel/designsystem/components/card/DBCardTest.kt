package com.dbsystel.designsystem.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.components.card.preview.previewName
import com.dbsystel.designsystem.components.card.preview.previewNameShort
import com.dbsystel.designsystem.components.core.PaparazziTest
import com.dbsystel.designsystem.components.core.preview.BasePreview
import com.dbsystel.designsystem.components.core.preview.BasePreviewProperties
import org.junit.Test


class DBCardTest : PaparazziTest() {
    @Test
    fun test_component_dbcard() {
        val cardSize = 90.dp
        paparazzi.snapshot {
            BasePreview(
                component = "DBCard",
                preview = {
                    DBCardElevation.entries.forEach { elevation ->
                        DBCard(modifier = Modifier.width(cardSize), elevation = elevation) { }
                    }
                },
                properties = listOf(
                    BasePreviewProperties(
                        property = "Elevation Level",
                        views = DBCardElevation.entries.map { elevation ->
                            elevation.previewName to {
                                DBCard(
                                    modifier = Modifier.size(cardSize),
                                    elevation = elevation
                                ) { }
                            }
                        }
                    ),
                    BasePreviewProperties(
                        property = "Spacing",
                        views = DBCardSpacing.entries.map { spacing ->
                            spacing.previewName to {
                                DBCard(modifier = Modifier.size(cardSize), spacing = spacing) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color(0x52e700eb))
                                    ) {
                                        Text(
                                            text = spacing.previewNameShort,
                                            color = Color(0xFFD600DB),
                                        )
                                    }
                                }
                            }
                        }
                    ),
                ),
            )
        }
    }
}
