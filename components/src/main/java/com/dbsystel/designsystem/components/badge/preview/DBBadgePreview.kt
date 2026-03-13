package com.dbsystel.designsystem.components.badge.preview

import com.dbsystel.designsystem.components.badge.DBBadgeContent
import com.dbsystel.designsystem.components.core.DBSize

internal val DBBadgeContent.previewName: String
    get() = when (this) {
        is DBBadgeContent.Text -> "(Def) Text"
        is DBBadgeContent.Icon -> "Icon"
        DBBadgeContent.Dot -> "Dot"
    }

internal val DBSize.previewName: String
    get() = when (this) {
        DBSize.MEDIUM -> "Medium"
        DBSize.SMALL -> "(Def) Small"
    }
