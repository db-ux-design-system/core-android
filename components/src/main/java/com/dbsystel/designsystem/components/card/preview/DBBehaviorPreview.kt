package com.dbsystel.designsystem.components.card.preview

import com.dbsystel.designsystem.components.card.DBCardBehavior


internal val DBCardBehavior.previewName: String
    get() = when (this) {
        DBCardBehavior.STATIC -> "(Def) Static"
        DBCardBehavior.INTERACTIVE -> "Interactive"
    }
