package com.dbsystel.designsystem.foundation.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import com.dbsystel.designsystem.foundation.theme.db.data.DBDimensionsMap

class DBSpacingDimensions private constructor(
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
	internal constructor(dimensionsMap: Map<String, Dp>, density: String, device: String) : this(
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

class DBSizingDimensions private constructor(
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
	internal constructor(dimensionsMap: Map<String, Dp>, density: String) : this(
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

class DBBorderDimensions private constructor(
	val width3xs: Dp,
	val width2xs: Dp,
	val widthXs: Dp,
	val widthSm: Dp,
	val widthMd: Dp,
	val widthLg: Dp,
	val widthXl: Dp,
	val width2xl: Dp,
	val width3xl: Dp,
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
	internal constructor(dimensionsMap: Map<String, Dp>, ) : this(
		dimensionsMap.getValue("borderWidth3xs"),
		dimensionsMap.getValue("borderWidth2xs"),
		dimensionsMap.getValue("borderWidthXs"),
		dimensionsMap.getValue("borderWidthSm"),
		dimensionsMap.getValue("borderWidthMd"),
		dimensionsMap.getValue("borderWidthLg"),
		dimensionsMap.getValue("borderWidthXl"),
		dimensionsMap.getValue("borderWidth2xl"),
		dimensionsMap.getValue("borderWidth3xl"),
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

data class DBDimensions(
	val spacing: DBSpacingDimensions,
	val sizing: DBSizingDimensions,
	val border: DBBorderDimensions,
) {
	internal companion object {
		fun getDimensionsFunctionalMobile(
			dimensionsMap: Map<String, Dp>,
		): DBDimensions = DBDimensions(
			spacing = DBSpacingDimensions(dimensionsMap, "Functional", "Mobile"),
			sizing = DBSizingDimensions(dimensionsMap, "Functional"),
			border = DBBorderDimensions(dimensionsMap, ),
		)

		fun getDimensionsFunctionalTablet(
			dimensionsMap: Map<String, Dp>,
		): DBDimensions = DBDimensions(
			spacing = DBSpacingDimensions(dimensionsMap, "Functional", "Tablet"),
			sizing = DBSizingDimensions(dimensionsMap, "Functional"),
			border = DBBorderDimensions(dimensionsMap, ),
		)

		fun getDimensionsRegularMobile(
			dimensionsMap: Map<String, Dp>,
		): DBDimensions = DBDimensions(
			spacing = DBSpacingDimensions(dimensionsMap, "Regular", "Mobile"),
			sizing = DBSizingDimensions(dimensionsMap, "Regular"),
			border = DBBorderDimensions(dimensionsMap, ),
		)

		fun getDimensionsRegularTablet(
			dimensionsMap: Map<String, Dp>,
		): DBDimensions = DBDimensions(
			spacing = DBSpacingDimensions(dimensionsMap, "Regular", "Tablet"),
			sizing = DBSizingDimensions(dimensionsMap, "Regular"),
			border = DBBorderDimensions(dimensionsMap, ),
		)

		fun getDimensionsExpressiveMobile(
			dimensionsMap: Map<String, Dp>,
		): DBDimensions = DBDimensions(
			spacing = DBSpacingDimensions(dimensionsMap, "Expressive", "Mobile"),
			sizing = DBSizingDimensions(dimensionsMap, "Expressive"),
			border = DBBorderDimensions(dimensionsMap, ),
		)

		fun getDimensionsExpressiveTablet(
			dimensionsMap: Map<String, Dp>,
		): DBDimensions = DBDimensions(
			spacing = DBSpacingDimensions(dimensionsMap, "Expressive", "Tablet"),
			sizing = DBSizingDimensions(dimensionsMap, "Expressive"),
			border = DBBorderDimensions(dimensionsMap, ),
		)

	}
}

val LocalDimensions = staticCompositionLocalOf {
	DBDimensions.getDimensionsRegularMobile(
		DBDimensionsMap
	)
}
