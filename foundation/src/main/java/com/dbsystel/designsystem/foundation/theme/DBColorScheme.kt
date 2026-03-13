package com.dbsystel.designsystem.foundation.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.dbsystel.designsystem.foundation.theme.db.data.DBColorMap

interface IStateColor {
    val Default: Color
    val Hovered: Color
    val Pressed: Color
}

interface IBasic {
    interface IBackground {
        interface ITransparent {
            val Full: Color
            val Semi: Color
            val FullHovered: Color
            val FullPressed: Color
            val SemiHovered: Color
            val SemiPressed: Color
        }

        val Level1: IStateColor
        val Level2: IStateColor
        val Level3: IStateColor
        val Transparent: ITransparent
    }

    interface IText {
        val Default: IStateColor
        val Emphasis100: IStateColor
        val Emphasis90: IStateColor
        val Emphasis80: IStateColor
    }

    interface IIcon {
        val Default: IStateColor
        val Emphasis100: IStateColor
        val Emphasis90: IStateColor
        val Emphasis80: IStateColor
        val Emphasis70: IStateColor
    }

    interface IBorder {
        val Default: IStateColor
        val Emphasis100: IStateColor
        val Emphasis70: IStateColor
        val Emphasis60: IStateColor
        val Emphasis50: IStateColor
    }

    val Background: IBackground
    val Text: IText
    val Icon: IIcon
    val Border: IBorder
}

interface IInverted {
    interface IBackground {
        val ContrastMax: IStateColor
        val ContrastHigh: IStateColor
        val ContrastLow: IStateColor
    }

    val Background: IBackground
    val OnBackground: IStateColor
}

interface IVibrant {
    val Background: IStateColor
    val OnBackground: IStateColor
}

class DBColorVariant private constructor(
	val bgBasicLevel1Default: Color,
	val bgBasicLevel1Hovered: Color,
	val bgBasicLevel1Pressed: Color,
	val bgBasicLevel2Default: Color,
	val bgBasicLevel2Hovered: Color,
	val bgBasicLevel2Pressed: Color,
	val bgBasicLevel3Default: Color,
	val bgBasicLevel3Hovered: Color,
	val bgBasicLevel3Pressed: Color,
	val bgBasicTransparentFullDefault: Color,
	val bgBasicTransparentFullHovered: Color,
	val bgBasicTransparentFullPressed: Color,
	val bgBasicTransparentSemiDefault: Color,
	val bgBasicTransparentSemiHovered: Color,
	val bgBasicTransparentSemiPressed: Color,
	val onBgBasicEmphasis100Default: Color,
	val onBgBasicEmphasis100Hovered: Color,
	val onBgBasicEmphasis100Pressed: Color,
	val onBgBasicEmphasis90Default: Color,
	val onBgBasicEmphasis90Hovered: Color,
	val onBgBasicEmphasis90Pressed: Color,
	val onBgBasicEmphasis80Default: Color,
	val onBgBasicEmphasis80Hovered: Color,
	val onBgBasicEmphasis80Pressed: Color,
	val onBgBasicEmphasis70Default: Color,
	val onBgBasicEmphasis70Hovered: Color,
	val onBgBasicEmphasis70Pressed: Color,
	val onBgBasicEmphasis60Default: Color,
	val onBgBasicEmphasis50Default: Color,
	val bgInvertedContrastMaxDefault: Color,
	val bgInvertedContrastMaxHovered: Color,
	val bgInvertedContrastMaxPressed: Color,
	val bgInvertedContrastHighDefault: Color,
	val bgInvertedContrastHighHovered: Color,
	val bgInvertedContrastHighPressed: Color,
	val bgInvertedContrastLowDefault: Color,
	val bgInvertedContrastLowHovered: Color,
	val bgInvertedContrastLowPressed: Color,
	val onBgInvertedDefault: Color,
	val onBgInvertedHovered: Color,
	val onBgInvertedPressed: Color,
	val bgVibrantDefault: Color,
	val bgVibrantHovered: Color,
	val bgVibrantPressed: Color,
	val onBgVibrantDefault: Color,
	val onBgVibrantHovered: Color,
	val onBgVibrantPressed: Color,
	val onOriginDefault: Color,
	val originDefault: Color,
	val originHovered: Color,
	val originPressed: Color,
) {
    val Basic = object : IBasic {
        override val Background = object : IBasic.IBackground {
            override val Level1 = object : IStateColor {
                override val Default = bgBasicLevel1Default
                override val Hovered = bgBasicLevel1Hovered
                override val Pressed = bgBasicLevel1Pressed
            }
            override val Level2 = object : IStateColor {
                override val Default = bgBasicLevel2Default
                override val Hovered = bgBasicLevel2Hovered
                override val Pressed = bgBasicLevel2Pressed
            }
            override val Level3 = object : IStateColor {
                override val Default = bgBasicLevel3Default
                override val Hovered = bgBasicLevel3Hovered
                override val Pressed = bgBasicLevel3Pressed
            }
            override val Transparent = object : IBasic.IBackground.ITransparent {
                override val Full = bgBasicTransparentFullDefault
                override val Semi = bgBasicTransparentSemiDefault
                override val FullHovered = bgBasicTransparentFullHovered
                override val FullPressed = bgBasicTransparentFullPressed
                override val SemiHovered = bgBasicTransparentSemiHovered
                override val SemiPressed = bgBasicTransparentSemiPressed
            }
        }

        override val Text = object : IBasic.IText {
            val e100 = object : IStateColor {
                override val Default = onBgBasicEmphasis100Default
                override val Hovered = onBgBasicEmphasis100Hovered
                override val Pressed = onBgBasicEmphasis100Pressed
            }

            override val Default = e100
            override val Emphasis100 = e100
            override val Emphasis90 = object : IStateColor {
                override val Default = onBgBasicEmphasis90Default
                override val Hovered = onBgBasicEmphasis90Hovered
                override val Pressed = onBgBasicEmphasis90Pressed
            }
            override val Emphasis80 = object : IStateColor {
                override val Default = onBgBasicEmphasis80Default
                override val Hovered = onBgBasicEmphasis80Hovered
                override val Pressed = onBgBasicEmphasis80Pressed
            }
        }

        override val Icon = object : IBasic.IIcon {
            val e70 = object : IStateColor {
                override val Default = onBgBasicEmphasis70Default
                override val Hovered = onBgBasicEmphasis70Hovered
                override val Pressed = onBgBasicEmphasis70Pressed
            }

            override val Default = e70
            override val Emphasis100 = object : IStateColor {
                override val Default = onBgBasicEmphasis100Default
                override val Hovered = onBgBasicEmphasis100Hovered
                override val Pressed = onBgBasicEmphasis100Pressed
            }
            override val Emphasis90 = object : IStateColor {
                override val Default = onBgBasicEmphasis90Default
                override val Hovered = onBgBasicEmphasis90Hovered
                override val Pressed = onBgBasicEmphasis90Pressed
            }
            override val Emphasis80 = object : IStateColor {
                override val Default = onBgBasicEmphasis80Default
                override val Hovered = onBgBasicEmphasis80Hovered
                override val Pressed = onBgBasicEmphasis80Pressed
            }
            override val Emphasis70 = e70
        }

        override val Border = object : IBasic.IBorder {
            val e60 = object : IStateColor {
                override val Default = onBgBasicEmphasis60Default
                override val Hovered = onBgBasicEmphasis60Default
                override val Pressed = onBgBasicEmphasis60Default
            }

            override val Default = e60
            override val Emphasis100 = object : IStateColor {
                override val Default = onBgBasicEmphasis100Default
                override val Hovered = onBgBasicEmphasis100Hovered
                override val Pressed = onBgBasicEmphasis100Pressed
            }
            override val Emphasis70 = object : IStateColor {
                override val Default = onBgBasicEmphasis70Default
                override val Hovered = onBgBasicEmphasis70Hovered
                override val Pressed = onBgBasicEmphasis70Pressed
            }
            override val Emphasis60 = e60
            override val Emphasis50 = object : IStateColor {
                override val Default = onBgBasicEmphasis50Default
                override val Hovered = onBgBasicEmphasis50Default
                override val Pressed = onBgBasicEmphasis50Default
            }
        }
    }

    val Inverted = object : IInverted {
        override val Background = object : IInverted.IBackground {
            override val ContrastMax = object : IStateColor {
                override val Default = bgInvertedContrastMaxDefault
                override val Hovered = bgInvertedContrastMaxHovered
                override val Pressed = bgInvertedContrastMaxPressed
            }
            override val ContrastHigh = object : IStateColor {
                override val Default = bgInvertedContrastHighDefault
                override val Hovered = bgInvertedContrastHighHovered
                override val Pressed = bgInvertedContrastHighPressed
            }
            override val ContrastLow = object : IStateColor {
                override val Default = bgInvertedContrastLowDefault
                override val Hovered = bgInvertedContrastLowHovered
                override val Pressed = bgInvertedContrastLowPressed
            }
        }
        override val OnBackground = object : IStateColor {
            override val Default = onBgInvertedDefault
            override val Hovered = onBgInvertedHovered
            override val Pressed = onBgInvertedPressed
        }
    }

    val Vibrant = object : IVibrant {
        override val Background = object : IStateColor {
            override val Default = bgVibrantDefault
            override val Hovered = bgVibrantHovered
            override val Pressed = bgVibrantPressed
        }
        override val OnBackground = object : IStateColor {
            override val Default = onBgVibrantDefault
            override val Hovered = onBgVibrantHovered
            override val Pressed = onBgVibrantPressed
        }
    }

    val Origin = object : IStateColor {
          override val Default = originDefault
          override val Hovered = originHovered
          override val Pressed = originPressed
    }

    val OnOrigin = onOriginDefault

	internal companion object {
		fun dark(colorMap: Map<String, Color>, colorName: String) = DBColorVariant(
			bgBasicLevel1Default = colorMap.getValue(colorName + 1),
			bgBasicLevel1Hovered = colorMap.getValue(colorName + 3),
			bgBasicLevel1Pressed = colorMap.getValue(colorName + 4),
			bgBasicLevel2Default = colorMap.getValue(colorName + 2),
			bgBasicLevel2Hovered = colorMap.getValue(colorName + 4),
			bgBasicLevel2Pressed = colorMap.getValue(colorName + 5),
			bgBasicLevel3Default = colorMap.getValue(colorName + 3),
			bgBasicLevel3Hovered = colorMap.getValue(colorName + 1),
			bgBasicLevel3Pressed = colorMap.getValue(colorName + 0),
			bgBasicTransparentFullDefault = colorMap.getValue(colorName + 9).copy(0.00f),
			bgBasicTransparentFullHovered = colorMap.getValue(colorName + 9).copy(0.24f),
			bgBasicTransparentFullPressed = colorMap.getValue(colorName + 9).copy(0.32f),
			bgBasicTransparentSemiDefault = colorMap.getValue(colorName + 9).copy(0.16f),
			bgBasicTransparentSemiHovered = colorMap.getValue(colorName + 9).copy(0.24f),
			bgBasicTransparentSemiPressed = colorMap.getValue(colorName + 9).copy(0.32f),
			onBgBasicEmphasis100Default = colorMap.getValue(colorName + 12),
			onBgBasicEmphasis100Hovered = colorMap.getValue(colorName + 9),
			onBgBasicEmphasis100Pressed = colorMap.getValue(colorName + 11),
			onBgBasicEmphasis90Default = colorMap.getValue(colorName + 10),
			onBgBasicEmphasis90Hovered = colorMap.getValue(colorName + 14),
			onBgBasicEmphasis90Pressed = colorMap.getValue(colorName + 11),
			onBgBasicEmphasis80Default = colorMap.getValue(colorName + 9),
			onBgBasicEmphasis80Hovered = colorMap.getValue(colorName + 12),
			onBgBasicEmphasis80Pressed = colorMap.getValue(colorName + 10),
			onBgBasicEmphasis70Default = colorMap.getValue(colorName + 8),
			onBgBasicEmphasis70Hovered = colorMap.getValue(colorName + 10),
			onBgBasicEmphasis70Pressed = colorMap.getValue(colorName + 9),
			onBgBasicEmphasis60Default = colorMap.getValue(colorName + 6),
			onBgBasicEmphasis50Default = colorMap.getValue(colorName + 3),
			bgInvertedContrastMaxDefault = colorMap.getValue(colorName + 12),
			bgInvertedContrastMaxHovered = colorMap.getValue(colorName + 9),
			bgInvertedContrastMaxPressed = colorMap.getValue(colorName + 11),
			bgInvertedContrastHighDefault = colorMap.getValue(colorName + 9),
			bgInvertedContrastHighHovered = colorMap.getValue(colorName + 12),
			bgInvertedContrastHighPressed = colorMap.getValue(colorName + 10),
			bgInvertedContrastLowDefault = colorMap.getValue(colorName + 8),
			bgInvertedContrastLowHovered = colorMap.getValue(colorName + 12),
			bgInvertedContrastLowPressed = colorMap.getValue(colorName + 9),
			onBgInvertedDefault = colorMap.getValue(colorName + 3),
			onBgInvertedHovered = colorMap.getValue(colorName + 0),
			onBgInvertedPressed = colorMap.getValue(colorName + 2),
			bgVibrantDefault = colorMap.getValue(colorName + 9),
			bgVibrantHovered = colorMap.getValue(colorName + 12),
			bgVibrantPressed = colorMap.getValue(colorName + 10),
			onBgVibrantDefault = colorMap.getValue(colorName + 1),
			onBgVibrantHovered = colorMap.getValue(colorName + 4),
			onBgVibrantPressed = colorMap.getValue(colorName + 2),
			onOriginDefault = colorMap.getValue(colorName + "OnOriginDefaultDark"),
			originDefault = colorMap.getValue(colorName + "OriginDefaultDark"),
			originHovered = colorMap.getValue(colorName + "OriginHoveredDark"),
			originPressed = colorMap.getValue(colorName + "OriginPressedDark"),
		)

		fun light(colorMap: Map<String, Color>, colorName: String) = DBColorVariant(
			bgBasicLevel1Default = colorMap.getValue(colorName + 14),
			bgBasicLevel1Hovered = colorMap.getValue(colorName + 13),
			bgBasicLevel1Pressed = colorMap.getValue(colorName + 12),
			bgBasicLevel2Default = colorMap.getValue(colorName + 13),
			bgBasicLevel2Hovered = colorMap.getValue(colorName + 12),
			bgBasicLevel2Pressed = colorMap.getValue(colorName + 11),
			bgBasicLevel3Default = colorMap.getValue(colorName + 12),
			bgBasicLevel3Hovered = colorMap.getValue(colorName + 11),
			bgBasicLevel3Pressed = colorMap.getValue(colorName + 10),
			bgBasicTransparentFullDefault = colorMap.getValue(colorName + 6).copy(0.00f),
			bgBasicTransparentFullHovered = colorMap.getValue(colorName + 6).copy(0.24f),
			bgBasicTransparentFullPressed = colorMap.getValue(colorName + 6).copy(0.32f),
			bgBasicTransparentSemiDefault = colorMap.getValue(colorName + 6).copy(0.08f),
			bgBasicTransparentSemiHovered = colorMap.getValue(colorName + 6).copy(0.24f),
			bgBasicTransparentSemiPressed = colorMap.getValue(colorName + 6).copy(0.32f),
			onBgBasicEmphasis100Default = colorMap.getValue(colorName + 1),
			onBgBasicEmphasis100Hovered = colorMap.getValue(colorName + 5),
			onBgBasicEmphasis100Pressed = colorMap.getValue(colorName + 2),
			onBgBasicEmphasis90Default = colorMap.getValue(colorName + 4),
			onBgBasicEmphasis90Hovered = colorMap.getValue(colorName + 0),
			onBgBasicEmphasis90Pressed = colorMap.getValue(colorName + 3),
			onBgBasicEmphasis80Default = colorMap.getValue(colorName + 6),
			onBgBasicEmphasis80Hovered = colorMap.getValue(colorName + 3),
			onBgBasicEmphasis80Pressed = colorMap.getValue(colorName + 5),
			onBgBasicEmphasis70Default = colorMap.getValue(colorName + 7),
			onBgBasicEmphasis70Hovered = colorMap.getValue(colorName + 4),
			onBgBasicEmphasis70Pressed = colorMap.getValue(colorName + 6),
			onBgBasicEmphasis60Default = colorMap.getValue(colorName + 10),
			onBgBasicEmphasis50Default = colorMap.getValue(colorName + 12),
			bgInvertedContrastMaxDefault = colorMap.getValue(colorName + 1),
			bgInvertedContrastMaxHovered = colorMap.getValue(colorName + 5),
			bgInvertedContrastMaxPressed = colorMap.getValue(colorName + 2),
			bgInvertedContrastHighDefault = colorMap.getValue(colorName + 6),
			bgInvertedContrastHighHovered = colorMap.getValue(colorName + 2),
			bgInvertedContrastHighPressed = colorMap.getValue(colorName + 5),
			bgInvertedContrastLowDefault = colorMap.getValue(colorName + 7),
			bgInvertedContrastLowHovered = colorMap.getValue(colorName + 3),
			bgInvertedContrastLowPressed = colorMap.getValue(colorName + 6),
			onBgInvertedDefault = colorMap.getValue(colorName + 14),
			onBgInvertedHovered = colorMap.getValue(colorName + 11),
			onBgInvertedPressed = colorMap.getValue(colorName + 13),
			bgVibrantDefault = colorMap.getValue(colorName + 9),
			bgVibrantHovered = colorMap.getValue(colorName + 12),
			bgVibrantPressed = colorMap.getValue(colorName + 10),
			onBgVibrantDefault = colorMap.getValue(colorName + 1),
			onBgVibrantHovered = colorMap.getValue(colorName + 4),
			onBgVibrantPressed = colorMap.getValue(colorName + 2),
			onOriginDefault = colorMap.getValue(colorName + "OnOriginDefaultLight"),
			originDefault = colorMap.getValue(colorName + "OriginDefaultLight"),
			originHovered = colorMap.getValue(colorName + "OriginHoveredLight"),
			originPressed = colorMap.getValue(colorName + "OriginPressedLight"),
		)

	}
}

class DBColorScheme(
	val neutral: DBColorVariant,
	val brand: DBColorVariant,
	val informational: DBColorVariant,
	val warning: DBColorVariant,
	val successful: DBColorVariant,
	val critical: DBColorVariant,
	val yellow: DBColorVariant,
	val orange: DBColorVariant,
	val red: DBColorVariant,
	val pink: DBColorVariant,
	val violet: DBColorVariant,
	val blue: DBColorVariant,
	val cyan: DBColorVariant,
	val turquoise: DBColorVariant,
	val green: DBColorVariant,
	val lightGreen: DBColorVariant,
	val burgundy: DBColorVariant,
) {
	internal companion object {
		fun getColorSchemeDark(colorMap: Map<String, Color>): DBColorScheme =
			DBColorScheme(
				neutral = DBColorVariant.dark(colorMap, "neutral"),
				brand = DBColorVariant.dark(colorMap, "brand"),
				informational = DBColorVariant.dark(colorMap, "informational"),
				warning = DBColorVariant.dark(colorMap, "warning"),
				successful = DBColorVariant.dark(colorMap, "successful"),
				critical = DBColorVariant.dark(colorMap, "critical"),
				yellow = DBColorVariant.dark(colorMap, "yellow"),
				orange = DBColorVariant.dark(colorMap, "orange"),
				red = DBColorVariant.dark(colorMap, "red"),
				pink = DBColorVariant.dark(colorMap, "pink"),
				violet = DBColorVariant.dark(colorMap, "violet"),
				blue = DBColorVariant.dark(colorMap, "blue"),
				cyan = DBColorVariant.dark(colorMap, "cyan"),
				turquoise = DBColorVariant.dark(colorMap, "turquoise"),
				green = DBColorVariant.dark(colorMap, "green"),
				lightGreen = DBColorVariant.dark(colorMap, "light-green"),
				burgundy = DBColorVariant.dark(colorMap, "burgundy"),
			)

		fun getColorSchemeLight(colorMap: Map<String, Color>): DBColorScheme =
			DBColorScheme(
				neutral = DBColorVariant.light(colorMap, "neutral"),
				brand = DBColorVariant.light(colorMap, "brand"),
				informational = DBColorVariant.light(colorMap, "informational"),
				warning = DBColorVariant.light(colorMap, "warning"),
				successful = DBColorVariant.light(colorMap, "successful"),
				critical = DBColorVariant.light(colorMap, "critical"),
				yellow = DBColorVariant.light(colorMap, "yellow"),
				orange = DBColorVariant.light(colorMap, "orange"),
				red = DBColorVariant.light(colorMap, "red"),
				pink = DBColorVariant.light(colorMap, "pink"),
				violet = DBColorVariant.light(colorMap, "violet"),
				blue = DBColorVariant.light(colorMap, "blue"),
				cyan = DBColorVariant.light(colorMap, "cyan"),
				turquoise = DBColorVariant.light(colorMap, "turquoise"),
				green = DBColorVariant.light(colorMap, "green"),
				lightGreen = DBColorVariant.light(colorMap, "light-green"),
				burgundy = DBColorVariant.light(colorMap, "burgundy"),
			)

	}
}

val LocalColors =
	staticCompositionLocalOf { DBColorScheme.getColorSchemeLight(DBColorMap) }
val LocalActiveColor =
	staticCompositionLocalOf { DBColorScheme.getColorSchemeLight(DBColorMap).neutral }
