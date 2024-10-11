package com.dbsystel.designsystem.foundation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import com.dbsystel.designsystem.foundation.deutschebahn.data.DeutscheBahnDimensionsMap

class SpacingDimensions private constructor(
	val responsive3xs: Dp,
	val responsive2xs: Dp,
	val responsiveXs: Dp,
	val responsiveSm: Dp,
	val responsiveMd: Dp,
	val responsiveLg: Dp,
	val responsiveXl: Dp,
	val responsive2xl: Dp,
	val responsive3xl: Dp,
	val fixed3xs: Dp,
	val fixed2xs: Dp,
	val fixedXs: Dp,
	val fixedSm: Dp,
	val fixedMd: Dp,
	val fixedLg: Dp,
	val fixedXl: Dp,
	val fixed2xl: Dp,
	val fixed3xl: Dp,
) {
	constructor(dimensionsMap: Map<String, Dp>, density: String, device: String) : this(
		dimensionsMap.getValue("spacingResponsive${density}${device}3xs"),
		dimensionsMap.getValue("spacingResponsive${density}${device}2xs"),
		dimensionsMap.getValue("spacingResponsive${density}${device}Xs"),
		dimensionsMap.getValue("spacingResponsive${density}${device}Sm"),
		dimensionsMap.getValue("spacingResponsive${density}${device}Md"),
		dimensionsMap.getValue("spacingResponsive${density}${device}Lg"),
		dimensionsMap.getValue("spacingResponsive${density}${device}Xl"),
		dimensionsMap.getValue("spacingResponsive${density}${device}2xl"),
		dimensionsMap.getValue("spacingResponsive${density}${device}3xl"),
		dimensionsMap.getValue("spacingFixed${density}3xs"),
		dimensionsMap.getValue("spacingFixed${density}2xs"),
		dimensionsMap.getValue("spacingFixed${density}Xs"),
		dimensionsMap.getValue("spacingFixed${density}Sm"),
		dimensionsMap.getValue("spacingFixed${density}Md"),
		dimensionsMap.getValue("spacingFixed${density}Lg"),
		dimensionsMap.getValue("spacingFixed${density}Xl"),
		dimensionsMap.getValue("spacingFixed${density}2xl"),
		dimensionsMap.getValue("spacingFixed${density}3xl"),
	)
}

class SizingDimensions private constructor(
	val base3xs: Dp,
	val base2xs: Dp,
	val baseXs: Dp,
	val baseSm: Dp,
	val baseMd: Dp,
	val baseLg: Dp,
	val baseXl: Dp,
	val base2xl: Dp,
	val base3xl: Dp,
) {
	constructor(dimensionsMap: Map<String, Dp>, density: String) : this(
		dimensionsMap.getValue("sizing${density}3xs"),
		dimensionsMap.getValue("sizing${density}2xs"),
		dimensionsMap.getValue("sizing${density}Xs"),
		dimensionsMap.getValue("sizing${density}Sm"),
		dimensionsMap.getValue("sizing${density}Md"),
		dimensionsMap.getValue("sizing${density}Lg"),
		dimensionsMap.getValue("sizing${density}Xl"),
		dimensionsMap.getValue("sizing${density}2xl"),
		dimensionsMap.getValue("sizing${density}3xl"),
	)
}

class BorderDimensions private constructor(
	val height3xs: Dp,
	val height2xs: Dp,
	val heightXs: Dp,
	val heightSm: Dp,
	val heightMd: Dp,
	val heightLg: Dp,
	val heightXl: Dp,
	val height2xl: Dp,
	val height3xl: Dp,
	val radius3xs: Dp,
	val radius2xs: Dp,
	val radiusXs: Dp,
	val radiusSm: Dp,
	val radiusMd: Dp,
	val radiusLg: Dp,
	val radiusXl: Dp,
	val radius2xl: Dp,
	val radius3xl: Dp,
) {
	constructor(dimensionsMap: Map<String, Dp>, ) : this(
		dimensionsMap.getValue("borderHeight3xs"),
		dimensionsMap.getValue("borderHeight2xs"),
		dimensionsMap.getValue("borderHeightXs"),
		dimensionsMap.getValue("borderHeightSm"),
		dimensionsMap.getValue("borderHeightMd"),
		dimensionsMap.getValue("borderHeightLg"),
		dimensionsMap.getValue("borderHeightXl"),
		dimensionsMap.getValue("borderHeight2xl"),
		dimensionsMap.getValue("borderHeight3xl"),
		dimensionsMap.getValue("borderRadius3xs"),
		dimensionsMap.getValue("borderRadius2xs"),
		dimensionsMap.getValue("borderRadiusXs"),
		dimensionsMap.getValue("borderRadiusSm"),
		dimensionsMap.getValue("borderRadiusMd"),
		dimensionsMap.getValue("borderRadiusLg"),
		dimensionsMap.getValue("borderRadiusXl"),
		dimensionsMap.getValue("borderRadius2xl"),
		dimensionsMap.getValue("borderRadius3xl"),
	)
}

data class DesignSystemDimensions(
	val spacing: SpacingDimensions,
	val sizing: SizingDimensions,
	val border: BorderDimensions,
)

fun getDimensionsFunctionalMobile(
    dimensionsMap: Map<String, Dp>,
): DesignSystemDimensions = DesignSystemDimensions(
	spacing = SpacingDimensions(dimensionsMap, "Functional", "Mobile"),
	sizing = SizingDimensions(dimensionsMap, "Functional"),
	border = BorderDimensions(dimensionsMap, ),
)

fun getDimensionsFunctionalTablet(
    dimensionsMap: Map<String, Dp>,
): DesignSystemDimensions = DesignSystemDimensions(
	spacing = SpacingDimensions(dimensionsMap, "Functional", "Tablet"),
	sizing = SizingDimensions(dimensionsMap, "Functional"),
	border = BorderDimensions(dimensionsMap, ),
)

fun getDimensionsRegularMobile(
    dimensionsMap: Map<String, Dp>,
): DesignSystemDimensions = DesignSystemDimensions(
	spacing = SpacingDimensions(dimensionsMap, "Regular", "Mobile"),
	sizing = SizingDimensions(dimensionsMap, "Regular"),
	border = BorderDimensions(dimensionsMap, ),
)

fun getDimensionsRegularTablet(
    dimensionsMap: Map<String, Dp>,
): DesignSystemDimensions = DesignSystemDimensions(
	spacing = SpacingDimensions(dimensionsMap, "Regular", "Tablet"),
	sizing = SizingDimensions(dimensionsMap, "Regular"),
	border = BorderDimensions(dimensionsMap, ),
)

fun getDimensionsExpressiveMobile(
    dimensionsMap: Map<String, Dp>,
): DesignSystemDimensions = DesignSystemDimensions(
	spacing = SpacingDimensions(dimensionsMap, "Expressive", "Mobile"),
	sizing = SizingDimensions(dimensionsMap, "Expressive"),
	border = BorderDimensions(dimensionsMap, ),
)

fun getDimensionsExpressiveTablet(
    dimensionsMap: Map<String, Dp>,
): DesignSystemDimensions = DesignSystemDimensions(
	spacing = SpacingDimensions(dimensionsMap, "Expressive", "Tablet"),
	sizing = SizingDimensions(dimensionsMap, "Expressive"),
	border = BorderDimensions(dimensionsMap, ),
)

val LocalDimensions = staticCompositionLocalOf {
    getDimensionsRegularMobile(
        DeutscheBahnDimensionsMap
    )
}
