package com.dbsystel.designsystem.components.link.preview

import com.dbsystel.designsystem.components.link.DBLinkContent
import com.dbsystel.designsystem.components.link.DBLinkVariant

internal val DBLinkContent.previewName: String
    get() = when (this) {
        DBLinkContent.INTERNAL -> "(Def) Internal"
        DBLinkContent.EXTERNAL -> "External"
    }

internal val DBLinkVariant.previewName: String
    get() = when (this) {
        DBLinkVariant.ADAPTIVE -> "(Def) Adaptive"
        DBLinkVariant.BRAND -> "Brand"
    }
