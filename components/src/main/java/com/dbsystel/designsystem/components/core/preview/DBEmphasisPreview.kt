package com.dbsystel.designsystem.components.core.preview

import com.dbsystel.designsystem.components.core.DBEmphasis


internal val DBEmphasis.previewName: String
    get() = when (this) {
        DBEmphasis.WEAK -> "(Def) Weak"
        DBEmphasis.STRONG -> "Strong"
    }
