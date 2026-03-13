package com.dbsystel.designsystem.components.card.preview

import com.dbsystel.designsystem.components.card.DBCardElevation
import com.dbsystel.designsystem.components.card.DBCardSpacing

internal val DBCardElevation.previewName: String
    get() = when (this) {
        DBCardElevation.LEVEL_1 -> "(Def) 1"
        DBCardElevation.LEVEL_2 -> "2"
        DBCardElevation.LEVEL_3 -> "3"
    }

internal val DBCardSpacing.previewName: String
    get() = when (this) {
        DBCardSpacing.SMALL -> "(Def) Small"
        DBCardSpacing.MEDIUM -> "Medium"
        DBCardSpacing.LARGE -> "Large"
        DBCardSpacing.NONE -> "None"
    }

internal val DBCardSpacing.previewNameShort: String
    get() = when (this) {
        DBCardSpacing.SMALL -> "sm"
        DBCardSpacing.MEDIUM -> "md"
        DBCardSpacing.LARGE -> "lg"
        DBCardSpacing.NONE -> "None"
    }
