package com.dbsystel.designsystem.components.core.preview

import com.dbsystel.designsystem.components.core.DBSemantic


internal val DBSemantic.previewName: String
    get() = when (this) {
        DBSemantic.ADAPTIVE -> "(Def) Adaptive"
        DBSemantic.CRITICAL -> "Critical"
        DBSemantic.INFORMATIONAL -> "Informational"
        DBSemantic.NEUTRAL -> "Neutral"
        DBSemantic.SUCCESSFUL -> "Successful"
        DBSemantic.WARNING -> "Warning"
    }
