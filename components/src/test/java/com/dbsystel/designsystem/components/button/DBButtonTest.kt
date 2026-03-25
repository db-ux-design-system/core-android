package com.dbsystel.designsystem.components.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.dbsystel.designsystem.components.button.preview.previewName
import com.dbsystel.designsystem.components.core.DBIcon
import com.dbsystel.designsystem.components.core.DBSize
import com.dbsystel.designsystem.components.core.PaparazziTest
import com.dbsystel.designsystem.components.core.preview.BasePreview
import com.dbsystel.designsystem.components.core.preview.BasePreviewProperties
import com.dbsystel.designsystem.components.core.preview.previewName
import com.dbsystel.designsystem.foundation.R
import org.junit.Test


class DBButtonTest: PaparazziTest() {
    @Test
    fun db_button_test() {
        paparazzi.snapshot {
            BasePreview(
                component = "DBButton",
                preview = {
                    DBButtonVariant.entries.forEach { variant ->
                        DBButton(
                            text = "Button",
                            variant = variant,
                        ) { }
                    }
                },
                properties = listOf(
                    BasePreviewProperties(
                        property = "Variant",
                        views = DBButtonVariant.entries.map { variant ->
                            variant.previewName to {
                                DBButton(
                                    text = "Text",
                                    variant = variant,
                                ) { }
                            }
                        }
                    ),
                    BasePreviewProperties(
                        property = "Disabled",
                        views = listOf(false, true).map { disabled ->
                            (if (!disabled) "(Def) False" else "True") to {
                                DBButton(
                                    text = "Text",
                                    disabled = disabled,
                                ) { }
                            }
                        }
                    ),
                    BasePreviewProperties(
                        property = "Size",
                        views = DBSize.entries.map { size ->
                            size.previewName to {
                                DBButton(
                                    text = "Text",
                                    size = size,
                                ) { }
                            }
                        }
                    ),
                    BasePreviewProperties(
                        property = "Show Icon Leading",
                        views = listOf(false, true).map { showIcon ->
                            (if (!showIcon) "(Def) False" else "True") to {
                                DBButton(
                                    text = "Text",
                                    icon = DBIcon(ImageVector.vectorResource(R.drawable.sample_vector)),
                                    showIcon = showIcon,
                                ) { }
                            }
                        }
                    ),
                    BasePreviewProperties(
                        property = "Show Icon Trailing",
                        views = listOf(false, true).map { showIcon ->
                            (if (!showIcon) "(Def) False" else "True") to {
                                DBButton(
                                    text = "Text",
                                    icon = DBIcon(ImageVector.vectorResource(R.drawable.sample_vector)),
                                    showIcon = showIcon,
                                    iconPosition = DBButtonIconPosition.TRAILING,
                                ) { }
                            }
                        }
                    ),
                    BasePreviewProperties(
                        property = "No Text",
                        views = listOf(false, true).map { noText ->
                            (if (!noText) "(Def) False" else "True") to {
                                DBButton(
                                    text = "Text",
                                    noText = noText,
                                    showIcon = noText,
                                    icon = DBIcon(ImageVector.vectorResource(R.drawable.sample_vector)),
                                ) { }
                            }
                        }
                    ),
                    BasePreviewProperties(
                        property = "Width",
                        views = listOf(false, true).map { width ->
                            (if (!width) "(Def) Auto" else "Full") to {
                                DBButton(
                                    modifier = if (width) Modifier.fillMaxWidth(0.7f) else Modifier,
                                    text = "Text",
                                ) { }
                            }
                        }
                    ),
                ),
            )
        }
    }
}
