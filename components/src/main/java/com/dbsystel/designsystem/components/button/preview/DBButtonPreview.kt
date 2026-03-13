package com.dbsystel.designsystem.components.button.preview

import com.dbsystel.designsystem.components.button.DBButtonVariant

internal val DBButtonVariant.previewName: String
    get() = when (this) {
        DBButtonVariant.OUTLINED -> "(Def) Outlined - Adaptive"
        DBButtonVariant.GHOST -> "Ghost - Adaptive"
        DBButtonVariant.FILLED -> "Filled - Adaptive"
        DBButtonVariant.BRAND -> "Brand"
    }
