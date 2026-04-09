package com.dbsystel.designsystem.components.switch.preview

import com.dbsystel.designsystem.components.switch.DBSwitchVariant


internal val DBSwitchVariant.previewName: String
    get() = when (this) {
        DBSwitchVariant.TRAILING -> "(Def) Trailing"
        DBSwitchVariant.LEADING -> "Leading"
    }
