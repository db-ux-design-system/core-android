package com.dbsystel.designsystem.foundation.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.dbsystel.designsystem.foundation.theme.core.DBFont
import com.dbsystel.designsystem.foundation.theme.db.data.DBTypographyMap

class DBTypographyUnits private constructor(
	val lineHeight3xs: TextUnit,
	val fontSize3xs: TextUnit,
	val lineHeight2xs: TextUnit,
	val fontSize2xs: TextUnit,
	val lineHeightXs: TextUnit,
	val fontSizeXs: TextUnit,
	val lineHeightSm: TextUnit,
	val fontSizeSm: TextUnit,
	val lineHeightMd: TextUnit,
	val fontSizeMd: TextUnit,
	val lineHeightLg: TextUnit,
	val fontSizeLg: TextUnit,
	val lineHeightXl: TextUnit,
	val fontSizeXl: TextUnit,
	val lineHeight2xl: TextUnit,
	val fontSize2xl: TextUnit,
	val lineHeight3xl: TextUnit,
	val fontSize3xl: TextUnit,
) {
    internal companion object {
        fun create(
            typographyMap: Map<String, TextUnit>,
            typoVariant: String,
            density: String,
            device: String,
        ): DBTypographyUnits {
            return DBTypographyUnits(
                typographyMap.getValue("${typoVariant}LineHeight$density${device}3xs"),
                typographyMap.getValue("${typoVariant}FontSize$density${device}3xs"),
                typographyMap.getValue("${typoVariant}LineHeight$density${device}2xs"),
                typographyMap.getValue("${typoVariant}FontSize$density${device}2xs"),
                typographyMap.getValue("${typoVariant}LineHeight$density${device}Xs"),
                typographyMap.getValue("${typoVariant}FontSize$density${device}Xs"),
                typographyMap.getValue("${typoVariant}LineHeight$density${device}Sm"),
                typographyMap.getValue("${typoVariant}FontSize$density${device}Sm"),
                typographyMap.getValue("${typoVariant}LineHeight$density${device}Md"),
                typographyMap.getValue("${typoVariant}FontSize$density${device}Md"),
                typographyMap.getValue("${typoVariant}LineHeight$density${device}Lg"),
                typographyMap.getValue("${typoVariant}FontSize$density${device}Lg"),
                typographyMap.getValue("${typoVariant}LineHeight$density${device}Xl"),
                typographyMap.getValue("${typoVariant}FontSize$density${device}Xl"),
                typographyMap.getValue("${typoVariant}LineHeight$density${device}2xl"),
                typographyMap.getValue("${typoVariant}FontSize$density${device}2xl"),
                typographyMap.getValue("${typoVariant}LineHeight$density${device}3xl"),
                typographyMap.getValue("${typoVariant}FontSize$density${device}3xl"),
            )
        }
    }
}

data class DBTypography(
	val body: DBTypographyUnits,
	val headline: DBTypographyUnits,
) {
	internal companion object {

		fun getTypographyFunctionalMobile(
			typographyMap: Map<String, TextUnit>,
		): DBTypography = DBTypography(
			body = DBTypographyUnits.create(typographyMap, "body", "Functional", "Mobile"),
			headline = DBTypographyUnits.create(typographyMap, "headline", "Functional", "Mobile"),
		)

		fun getTypographyFunctionalTablet(
			typographyMap: Map<String, TextUnit>,
		): DBTypography = DBTypography(
			body = DBTypographyUnits.create(typographyMap, "body", "Functional", "Tablet"),
			headline = DBTypographyUnits.create(typographyMap, "headline", "Functional", "Tablet"),
		)

		fun getTypographyRegularMobile(
			typographyMap: Map<String, TextUnit>,
		): DBTypography = DBTypography(
			body = DBTypographyUnits.create(typographyMap, "body", "Regular", "Mobile"),
			headline = DBTypographyUnits.create(typographyMap, "headline", "Regular", "Mobile"),
		)

		fun getTypographyRegularTablet(
			typographyMap: Map<String, TextUnit>,
		): DBTypography = DBTypography(
			body = DBTypographyUnits.create(typographyMap, "body", "Regular", "Tablet"),
			headline = DBTypographyUnits.create(typographyMap, "headline", "Regular", "Tablet"),
		)

		fun getTypographyExpressiveMobile(
			typographyMap: Map<String, TextUnit>,
		): DBTypography = DBTypography(
			body = DBTypographyUnits.create(typographyMap, "body", "Expressive", "Mobile"),
			headline = DBTypographyUnits.create(typographyMap, "headline", "Expressive", "Mobile"),
		)

		fun getTypographyExpressiveTablet(
			typographyMap: Map<String, TextUnit>,
		): DBTypography = DBTypography(
			body = DBTypographyUnits.create(typographyMap, "body", "Expressive", "Tablet"),
			headline = DBTypographyUnits.create(typographyMap, "headline", "Expressive", "Tablet"),
		)
	}
}

data class DBTextStyles(
	val h1: TextStyle,
	val h2: TextStyle,
	val h3: TextStyle,
	val h4: TextStyle,
	val h5: TextStyle,
	val h6: TextStyle,
	val body: TextStyle,
	val body3xl: TextStyle,
	val body2xl: TextStyle,
	val bodyXl: TextStyle,
	val bodyLg: TextStyle,
	val bodyMd: TextStyle,
	val bodySm: TextStyle,
	val bodyXs: TextStyle,
	val body2xs: TextStyle,
	val body3xs: TextStyle,
) {
	internal companion object {
		fun getTextStyles(typo: DBTypography): DBTextStyles =
			DBTextStyles(
			TextStyle(
				fontFamily = DBFont.dbNeoScreenFlex,
				fontWeight = FontWeight.Black,
				fontSize = typo.headline.fontSizeXl,
				lineHeight = typo.headline.lineHeightXl
			),
			TextStyle(
				fontFamily = DBFont.dbNeoScreenFlex,
				fontWeight = FontWeight.Black,
				fontSize = typo.headline.fontSizeLg,
				lineHeight = typo.headline.lineHeightLg
			),
			TextStyle(
				fontFamily = DBFont.dbNeoScreenFlex,
				fontWeight = FontWeight.Black,
				fontSize = typo.headline.fontSizeMd,
				lineHeight = typo.headline.lineHeightMd
			),
			TextStyle(
				fontFamily = DBFont.dbNeoScreenFlex,
				fontWeight = FontWeight.Black,
				fontSize = typo.headline.fontSizeSm,
				lineHeight = typo.headline.lineHeightSm
			),
			TextStyle(
				fontFamily = DBFont.dbNeoScreenFlex,
				fontWeight = FontWeight.Black,
				fontSize = typo.headline.fontSizeXs,
				lineHeight = typo.headline.lineHeightXs
			),
			TextStyle(
				fontFamily = DBFont.dbNeoScreenFlex,
				fontWeight = FontWeight.Black,
				fontSize = typo.headline.fontSize2xs,
				lineHeight = typo.headline.lineHeight2xs
			),
			TextStyle(
				fontFamily = DBFont.dbNeoScreenFlex,
				fontWeight = FontWeight.Normal,
				fontSize = typo.body.fontSizeMd,
				lineHeight = typo.body.lineHeightMd
			),
			TextStyle(
				fontFamily = DBFont.dbNeoScreenFlex,
				fontWeight = FontWeight.Normal,
				fontSize = typo.body.fontSize3xl,
				lineHeight = typo.body.lineHeight3xl
			),
			TextStyle(
				fontFamily = DBFont.dbNeoScreenFlex,
				fontWeight = FontWeight.Normal,
				fontSize = typo.body.fontSize2xl,
				lineHeight = typo.body.lineHeight2xl
			),
			TextStyle(
				fontFamily = DBFont.dbNeoScreenFlex,
				fontWeight = FontWeight.Normal,
				fontSize = typo.body.fontSizeXl,
				lineHeight = typo.body.lineHeightXl
			),
			TextStyle(
				fontFamily = DBFont.dbNeoScreenFlex,
				fontWeight = FontWeight.Normal,
				fontSize = typo.body.fontSizeLg,
				lineHeight = typo.body.lineHeightLg
			),
			TextStyle(
				fontFamily = DBFont.dbNeoScreenFlex,
				fontWeight = FontWeight.Normal,
				fontSize = typo.body.fontSizeMd,
				lineHeight = typo.body.lineHeightMd
			),
			TextStyle(
				fontFamily = DBFont.dbNeoScreenFlex,
				fontWeight = FontWeight.Normal,
				fontSize = typo.body.fontSizeSm,
				lineHeight = typo.body.lineHeightSm
			),
			TextStyle(
				fontFamily = DBFont.dbNeoScreenFlex,
				fontWeight = FontWeight.Normal,
				fontSize = typo.body.fontSizeXs,
				lineHeight = typo.body.lineHeightXs
			),
			TextStyle(
				fontFamily = DBFont.dbNeoScreenFlex,
				fontWeight = FontWeight.Normal,
				fontSize = typo.body.fontSize2xs,
				lineHeight = typo.body.lineHeight2xs
			),
			TextStyle(
				fontFamily = DBFont.dbNeoScreenFlex,
				fontWeight = FontWeight.Normal,
				fontSize = typo.body.fontSize3xs,
				lineHeight = typo.body.lineHeight3xs
			),
			)
	}
}


val LocalTypography = staticCompositionLocalOf {
    DBTextStyles.getTextStyles(
        DBTypography.getTypographyRegularMobile(
            DBTypographyMap
        )
    )
}
