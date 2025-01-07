package com.dbsystel.designsystem.components.link.preview

import com.dbsystel.designsystem.components.link.DSLinkContent
import com.dbsystel.designsystem.components.link.DSLinkSize
import com.dbsystel.designsystem.components.link.DSLinkVariant

internal val DSLinkContent.previewName: String
    get() = when (this) {
        DSLinkContent.INTERNAL -> "(Def) Internal"
        DSLinkContent.EXTERNAL -> "External"
    }

internal val DSLinkVariant.previewName: String
    get() = when (this) {
        DSLinkVariant.ADAPTIVE -> "(Def) Adaptive"
        DSLinkVariant.BRAND -> "Brand"
    }

internal val DSLinkSize.previewName: String
    get() = when (this) {
        DSLinkSize.MEDIUM -> "(Def) Medium"
        DSLinkSize.SMALL -> "Small"
    }
