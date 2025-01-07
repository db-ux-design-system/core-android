package com.dbsystel.designsystem.components.button.preview

import com.dbsystel.designsystem.components.button.DSButtonSize
import com.dbsystel.designsystem.components.button.DSButtonVariant
import com.dbsystel.designsystem.components.button.DSButtonWidth

internal val DSButtonVariant.previewName: String
    get() = when (this) {
        DSButtonVariant.OUTLINED -> "(Def) Outlined - Adaptive"
        DSButtonVariant.GHOST -> "Ghost - Adaptive"
        DSButtonVariant.FILLED -> "Filled - Adaptive"
        DSButtonVariant.BRAND -> "Brand"
    }

internal val DSButtonSize.previewName: String
    get() = when (this) {
        DSButtonSize.MEDIUM -> "(Def) Medium"
        DSButtonSize.SMALL -> "Small"
    }

internal val DSButtonWidth.previewName: String
    get() = when (this) {
        DSButtonWidth.AUTO -> "(Def) Auto"
        DSButtonWidth.FULL_WIDTH -> "Full"
    }
