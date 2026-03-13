package com.dbsystel.designsystem.components.core

import androidx.compose.ui.graphics.vector.ImageVector


/**
 * @param imageVector [ImageVector] to draw inside this icon
 * @param contentDescription text used by accessibility services to describe what this icon
 * represents. This should always be provided unless this icon is used for decorative purposes, and
 * does not represent a meaningful action that a user can take. This text should be localized.
 */
data class DBIcon(
    val imageVector: ImageVector,
    val contentDescription: String? = null,
)
