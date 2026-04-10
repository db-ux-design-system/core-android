package com.dbsystel.designsystem.components.link.extensions

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.components.core.DBSize
import com.dbsystel.designsystem.components.link.DBLinkContent
import com.dbsystel.designsystem.components.link.DBLinkContent.EXTERNAL
import com.dbsystel.designsystem.components.link.DBLinkContent.INTERNAL
import com.dbsystel.designsystem.components.link.DBLinkVariant
import com.dbsystel.designsystem.components.link.DBLinkVariant.ADAPTIVE
import com.dbsystel.designsystem.components.link.DBLinkVariant.BRAND
import com.dbsystel.designsystem.foundation.R
import com.dbsystel.designsystem.foundation.theme.DBTheme

// region Variant
internal val DBLinkVariant.color: Color
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        ADAPTIVE -> DBTheme.activeColor.Basic.Text.Emphasis100.Default
        BRAND -> DBTheme.colors.brand.Basic.Text.Emphasis80.Default
    }

internal val DBLinkVariant.pressedColor: Color
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        ADAPTIVE -> DBTheme.activeColor.Basic.Text.Emphasis100.Pressed
        BRAND -> DBTheme.colors.brand.Basic.Text.Emphasis80.Pressed
    }
// endregion

// region Size
internal val DBSize.paddingH: Dp
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        DBSize.MEDIUM -> DBTheme.dimensions.spacing.fixed2xs
        DBSize.SMALL -> DBTheme.dimensions.spacing.fixed3xs
    }
// endregion

// region Content
internal val DBLinkContent.iconRes: Int
    @DrawableRes
    get() = when (this) {
        INTERNAL -> R.drawable.ds_ic_arrow_forward
        EXTERNAL -> R.drawable.ds_ic_link_external
    }
//endregion
