package com.dbsystel.designsystem.components.accordion.preview

import com.dbsystel.designsystem.components.accordion.DBAccordionBehavior
import com.dbsystel.designsystem.components.accordion.DBAccordionVariant


internal val DBAccordionVariant.previewName: String
    get() = when (this) {
        DBAccordionVariant.DIVIDER -> "(Def) Divider"
        DBAccordionVariant.CARD -> "Card"
    }

internal val DBAccordionBehavior.previewName: String
    get() = when (this) {
        DBAccordionBehavior.MULTIPLE -> "(Def) Multiple"
        DBAccordionBehavior.SINGLE -> "Single"
    }
