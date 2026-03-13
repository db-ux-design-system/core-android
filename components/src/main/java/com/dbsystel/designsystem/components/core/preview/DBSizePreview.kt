package com.dbsystel.designsystem.components.core.preview

import com.dbsystel.designsystem.components.core.DBSize


internal val DBSize.previewName: String
    get() = when (this) {
        DBSize.MEDIUM -> "(Def) Medium"
        DBSize.SMALL -> "Small"
    }
