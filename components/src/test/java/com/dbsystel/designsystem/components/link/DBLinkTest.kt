package com.dbsystel.designsystem.components.link

import com.dbsystel.designsystem.components.core.DBSize
import com.dbsystel.designsystem.components.core.PaparazziTest
import com.dbsystel.designsystem.components.core.preview.BasePreview
import com.dbsystel.designsystem.components.core.preview.BasePreviewProperties
import com.dbsystel.designsystem.components.core.preview.previewName
import com.dbsystel.designsystem.components.link.preview.previewName
import org.junit.Test


class DBLinkTest : PaparazziTest() {
    @Test
    fun test_component_dblink() {
        paparazzi.snapshot {
            BasePreview(
                component = "DBLink",
                preview = {
                    DBLinkVariant.entries.forEach { variant ->
                        DBLink(text = "Text", variant = variant) { }
                        DBLink(
                            text = "Text",
                            variant = variant,
                            content = DBLinkContent.EXTERNAL,
                        ) { }
                    }
                },
                properties = listOf(
                    BasePreviewProperties(
                        property = "Content",
                        views = DBLinkContent.entries.map { content ->
                            content.previewName to {
                                DBLink(text = "Text", content = content) { }
                            }
                        }
                    ),
                    BasePreviewProperties(
                        property = "Variant",
                        views = DBLinkVariant.entries.map { variant ->
                            variant.previewName to {
                                DBLink(text = "Text", variant = variant) { }
                            }
                        },
                    ),
                    BasePreviewProperties(
                        property = "Disabled",
                        views = listOf(false, true).map { disabled ->
                            (if (!disabled) "(Def) False" else "True") to {
                                DBLink(text = "Text", disabled = disabled) { }
                            }
                        },
                    ),
                    BasePreviewProperties(
                        property = "Size",
                        views = DBSize.entries.map { size ->
                            size.previewName to {
                                DBLink(text = "Text", size = size) { }
                            }
                        },
                    ),
                    BasePreviewProperties(
                        property = "Show Icon",
                        views = listOf(true, false).map { showIcon ->
                            (if (showIcon) "(Def) True" else "False") to {
                                DBLink(text = "Text", showIcon = showIcon) { }
                            }
                        },
                    ),
                ),
            )
        }
    }
}
