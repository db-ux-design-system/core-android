package com.dbsystel.designsystem.components.switch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.dbsystel.designsystem.components.core.DBSize
import com.dbsystel.designsystem.components.core.DBValidation
import com.dbsystel.designsystem.components.core.PaparazziTest
import com.dbsystel.designsystem.components.core.preview.BasePreview
import com.dbsystel.designsystem.components.core.preview.BasePreviewExamples
import com.dbsystel.designsystem.components.core.preview.BasePreviewProperties
import com.dbsystel.designsystem.components.core.preview.previewName
import com.dbsystel.designsystem.components.switch.preview.previewName
import com.dbsystel.designsystem.foundation.theme.DBTheme
import org.junit.Test

class DBSwitchTest : PaparazziTest() {

    @Test
    fun test_component_dbswitch() {
        paparazzi.snapshot {
            val invalidState = DBValidation.Invalid("Invalid Message")
            val validState = DBValidation.Valid("Valid Message")
            var previewHeight by remember { mutableStateOf(0.dp) }
            val density = LocalDensity.current

            BasePreview(
                component = "DBSwitch",
                preview = {
                    listOf(
                        DBValidation.NoValidation,
                        invalidState,
                        validState,
                    ).forEach { validation ->
                        Column(
                            modifier = Modifier
                                .onGloballyPositioned { coordinates ->
                                    with(density) {
                                        previewHeight =
                                            max(previewHeight, coordinates.size.height.toDp())
                                    }
                                }
                                .heightIn(min = previewHeight),
                            verticalArrangement = Arrangement.spacedBy(
                                DBTheme.dimensions.spacing.fixedXs,
                                Alignment.CenterVertically
                            ),
                        ) {
                            DBSwitch(
                                label = "Label",
                                validation = validation,
                                visualAid = true,
                                onCheckedChange = { _ -> },
                            )
                            DBSwitch(
                                checked = true,
                                label = "Label",
                                visualAid = true,
                                validation = validation,
                                onCheckedChange = { _ -> },
                            )
                        }
                    }
                },
                properties = listOf(
                    BasePreviewProperties(
                        property = "Variant",
                        views = DBSwitchVariant.entries.map { variant ->
                            variant.previewName to {
                                DBSwitch(
                                    label = "Label",
                                    variant = variant,
                                    onCheckedChange = { _ -> },
                                )
                            }
                        }
                    ),
                    BasePreviewProperties(
                        property = "Disabled",
                        views = listOf(false, true).map { disabled ->
                            (if (!disabled) "(Def) False" else "True") to {
                                DBSwitch(
                                    label = "Label",
                                    disabled = disabled,
                                    onCheckedChange = { _ -> },
                                )
                            }
                        }
                    ),
                    BasePreviewProperties(
                        property = "Checked",
                        views = listOf(false, true).map { checked ->
                            (if (!checked) "(Def) False" else "True") to {
                                DBSwitch(
                                    label = "Label",
                                    checked = checked,
                                    onCheckedChange = { _ -> },
                                )
                            }
                        }
                    ),
                    BasePreviewProperties(
                        property = "Validation",
                        views = buildList {
                            add("(Def) No Validation" to { DBSwitch(onCheckedChange = { }) })
                            add("Invalid - Unchecked" to {
                                DBSwitch(
                                    validation = invalidState,
                                    onCheckedChange = { })
                            })
                            add("Invalid - Checked" to {
                                DBSwitch(
                                    validation = invalidState,
                                    checked = true,
                                    onCheckedChange = { },
                                )
                            })
                            add("Valid - Unchecked" to {
                                DBSwitch(
                                    validation = validState,
                                    onCheckedChange = { })
                            })
                            add("Valid - Checked" to {
                                DBSwitch(
                                    validation = validState,
                                    checked = true,
                                    onCheckedChange = { },
                                )
                            })
                        }
                    ),
                    BasePreviewProperties(
                        property = "Visual Aid",
                        views = listOf(false, true).map { visualAid ->
                            (if (!visualAid) "(Def) False" else "True") to {
                                Column(verticalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedSm)) {
                                    DBSwitch(
                                        label = "Label",
                                        checked = false,
                                        visualAid = visualAid,
                                        onCheckedChange = { _ -> },
                                    )
                                    DBSwitch(
                                        label = "Label",
                                        checked = true,
                                        visualAid = visualAid,
                                        onCheckedChange = { _ -> },
                                    )
                                }
                            }
                        }
                    ),
                    BasePreviewProperties(
                        property = "Size",
                        views = DBSize.entries.map { size ->
                            size.previewName to {
                                DBSwitch(
                                    label = "Label",
                                    size = size,
                                    onCheckedChange = { _ -> },
                                )
                            }
                        }
                    ),
                    BasePreviewProperties(
                        property = "Required",
                        views = listOf(false, true).map { required ->
                            (if (!required) "(Def) False" else "True") to {
                                DBSwitch(
                                    label = "Label",
                                    required = required,
                                    onCheckedChange = { _ -> },
                                )
                            }
                        }
                    ),
                    BasePreviewProperties(
                        property = "Show Label",
                        views = listOf(true, false).map { showLabel ->
                            (if (showLabel) "(Def) True" else "False") to {
                                DBSwitch(
                                    label = "Label",
                                    showLabel = showLabel,
                                    onCheckedChange = { _ -> },
                                )
                            }
                        }
                    ),
                    BasePreviewProperties(
                        property = "Show Message",
                        views = listOf(false, true).map { showMessage ->
                            (if (!showMessage) "(Def) False" else "True") to {
                                DBSwitch(
                                    label = "Label",
                                    showMessage = showMessage,
                                    message = "Message",
                                    onCheckedChange = { _ -> },
                                )
                            }
                        }
                    ),
                ),
                examples = listOf(
                    BasePreviewExamples(
                        property = "Custom Icons",
                        views = listOf(
                            {
                                DBSwitch(
                                    label = "Label",
                                    visualAid = true,
                                    iconLeading = ImageVector.vectorResource(com.dbsystel.designsystem.foundation.R.drawable.preview_dark),
                                    onCheckedChange = { _ -> },
                                )
                            },
                            {
                                DBSwitch(
                                    label = "Label",
                                    visualAid = true,
                                    checked = true,
                                    iconTrailing = ImageVector.vectorResource(com.dbsystel.designsystem.foundation.R.drawable.preview_light),
                                    onCheckedChange = { _ -> },
                                )
                            }
                        )
                    ),
                )
            )
        }
    }
}