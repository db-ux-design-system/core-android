package com.dbsystel.designsystem.components.infotext

import com.dbsystel.designsystem.components.core.DBSemantic
import com.dbsystel.designsystem.components.core.DBSize
import com.dbsystel.designsystem.components.core.PaparazziTest
import com.dbsystel.designsystem.components.core.preview.BasePreview
import com.dbsystel.designsystem.components.core.preview.BasePreviewProperties
import com.dbsystel.designsystem.components.core.preview.previewName
import org.junit.Test

class DBInfotextTest : PaparazziTest() {
    @Test
    fun test_component_dbinfotext() {
        paparazzi.snapshot {
            BasePreview(
                component = "DBInfotext",
                preview = {
                    DBSemantic.entries.forEach { semantic ->
                        DBInfotext(
                            text = "Text",
                            semantic = semantic,
                        )
                    }
                },
                properties = listOf(
                    BasePreviewProperties(
                        property = "Semantic",
                        views = DBSemantic.entries.map { semantic ->
                            semantic.previewName to {
                                DBInfotext(
                                    text = "Text",
                                    semantic = semantic,
                                )
                            }
                        },
                    ),
                    BasePreviewProperties(
                        property = "Size",
                        views = DBSize.entries.map { size ->
                            size.previewName to {
                                DBInfotext(
                                    text = "Text",
                                    size = size,
                                )
                            }
                        },
                    ),
                    BasePreviewProperties(
                        property = "Show Icon",
                        views = listOf(true, false).map { showIcon ->
                            (if (showIcon) "(Def) True" else "False") to {
                                DBInfotext(
                                    text = "Text",
                                    showIcon = showIcon,
                                )
                            }
                        },
                    ),
                    BasePreviewProperties(
                        property = "Width",
                        views = listOf("Text", "Text with\nMultiline").map { text ->
                            (if (text == "Text") "Single line" else "Multiline") to {
                                DBInfotext(
                                    text = text,
                                )
                            }
                        },
                    ),
                ),
            )
        }
    }
}
