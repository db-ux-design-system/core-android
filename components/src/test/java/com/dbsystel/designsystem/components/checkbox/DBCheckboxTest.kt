package com.dbsystel.designsystem.components.checkbox

import com.dbsystel.designsystem.components.core.DBSize
import com.dbsystel.designsystem.components.core.PaparazziTest
import com.dbsystel.designsystem.components.core.preview.BasePreview
import com.dbsystel.designsystem.components.core.preview.BasePreviewProperties
import com.dbsystel.designsystem.components.core.preview.previewName
import org.junit.Test


class DBCheckboxTest : PaparazziTest() {
    @Test
    fun test_component_dbcheckbox() {
        paparazzi.snapshot {
            val invalidState = DBCheckboxValidation.Invalid("Invalid Message")
            val validState = DBCheckboxValidation.Valid("Valid Message")
            BasePreview(
                component = "DBCheckbox",
                preview = {
                    DBCheckbox(
                        label = "Checkbox",
                    ) {}
                    DBCheckbox(
                        label = "Checkbox",
                        checked = true,
                    ) {}
                    DBCheckbox(
                        label = "Checkbox",
                        validation = invalidState,
                    ) {}
                    DBCheckbox(
                        label = "Checkbox",
                        checked = true,
                        validation = validState,
                    ) {}
                },
                properties = listOf(
                    BasePreviewProperties(
                        property = "Disabled",
                        views = listOf(false, true).map { disabled ->
                            (if (!disabled) "(Def) False" else "True") to {
                                DBCheckbox(
                                    label = "Label",
                                    checked = false,
                                    disabled = disabled,
                                ) {}
                            }
                        },
                    ),
                    BasePreviewProperties(
                        property = "Checked",
                        views = listOf(false, true).map { checked ->
                            (if (!checked) "(Def) False" else "True") to {
                                DBCheckbox(label = "Label", checked = checked) {}
                            }
                        },
                    ),
                    BasePreviewProperties(
                        property = "Indeterminate",
                        views = listOf(false, true).map { indeterminate ->
                            (if (!indeterminate) "(Def) False" else "True") to {
                                DBCheckbox(label = "Label", indeterminate = indeterminate) {}
                            }
                        },
                    ),
                    BasePreviewProperties(
                        property = "Size",
                        views = DBSize.entries.map { size ->
                            size.previewName to {
                                DBCheckbox(label = "Label", size = size) {}
                            }
                        },
                    ),
                    BasePreviewProperties(
                        property = "Required",
                        views = listOf(false, true).map { required ->
                            (if (!required) "(Def) False" else "True") to {
                                DBCheckbox(label = "Label", showRequiredAsterisk = required) {}
                            }
                        },
                    ),
                    BasePreviewProperties(
                        property = "Validation",
                        views = listOf(
                            "(Def) No Validation" to { DBCheckbox(label = "Label") {} },
                            "Invalid - Unchecked" to {
                                DBCheckbox(label = "Label", validation = invalidState) {}
                            },
                            "Invalid - Checked" to {
                                DBCheckbox(
                                    label = "Label",
                                    validation = invalidState,
                                    checked = true
                                ) {}
                            },
                            "Valid - Unchecked" to {
                                DBCheckbox(label = "Label", validation = validState) {}
                            },
                            "Valid - Checked" to {
                                DBCheckbox(
                                    label = "Label",
                                    validation = validState,
                                    checked = true
                                ) {}
                            },
                        ),
                    ),
                    BasePreviewProperties(
                        property = "Show Label",
                        views = listOf(true, false).map { showLabel ->
                            (if (showLabel) "(Def) True" else "False") to {
                                DBCheckbox(showLabel = showLabel, label = "Label") {}
                            }
                        },
                    ),
                    BasePreviewProperties(
                        property = "Show Label",
                        views = listOf(true, false).map { showLabel ->
                            (if (showLabel) "(Def) True" else "False") to {
                                DBCheckbox(showLabel = showLabel, label = "Label") {}
                            }
                        },
                    ),
                ),
            )
        }
    }
}
