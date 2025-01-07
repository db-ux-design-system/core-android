package com.dbsystel.designsystem.foundation.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.dbsystel.designsystem.foundation.theme.deutschebahn.data.DeutscheBahnColorMap

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
            val Hovered: Color
            val Pressed: Color
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

class DSColorVariant private constructor(
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
    val bgBasicTransparentSemiDefault: Color,
    val bgBasicTransparentHovered: Color,
    val bgBasicTransparentPressed: Color,
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
    val onBgBasicEmphasis60Hovered: Color,
    val onBgBasicEmphasis60Pressed: Color,
    val onBgBasicEmphasis50Default: Color,
    val onBgBasicEmphasis50Hovered: Color,
    val onBgBasicEmphasis50Pressed: Color,
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
    val onOriginDefault: Color,
    val onOriginHovered: Color,
    val onOriginPressed: Color,
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
                override val Hovered = bgBasicTransparentHovered
                override val Pressed = bgBasicTransparentPressed
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
                override val Hovered = onBgBasicEmphasis60Hovered
                override val Pressed = onBgBasicEmphasis60Pressed
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
                override val Hovered = onBgBasicEmphasis50Hovered
                override val Pressed = onBgBasicEmphasis50Pressed
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

    val Origin = object : IStateColor {
        override val Default = originDefault
        override val Hovered = originHovered
        override val Pressed = originPressed
    }

    val OnOrigin = object : IStateColor {
        override val Default = onOriginDefault
        override val Hovered = onOriginHovered
        override val Pressed = onOriginPressed
    }

    internal companion object {
        fun dark(colorMap: Map<String, Color>, colorName: String) = DSColorVariant(
            colorMap.getValue(colorName + 3),
            colorMap.getValue(colorName + 4),
            colorMap.getValue(colorName + 5),
            colorMap.getValue(colorName + 2),
            colorMap.getValue(colorName + 3),
            colorMap.getValue(colorName + 4),
            colorMap.getValue(colorName + 1),
            colorMap.getValue(colorName + 2),
            colorMap.getValue(colorName + 3),
            colorMap.getValue(colorName + 9).copy(1f),
            colorMap.getValue(colorName + 9).copy(0.84f),
            colorMap.getValue(colorName + 9).copy(0.76f),
            colorMap.getValue(colorName + 9).copy(0.68f),
            colorMap.getValue(colorName + 12),
            colorMap.getValue(colorName + 11),
            colorMap.getValue(colorName + 10),
            colorMap.getValue(colorName + 10),
            colorMap.getValue(colorName + 9),
            colorMap.getValue(colorName + 8),
            colorMap.getValue(colorName + 9),
            colorMap.getValue(colorName + 8),
            colorMap.getValue(colorName + 7),
            colorMap.getValue(colorName + 8),
            colorMap.getValue(colorName + 7),
            colorMap.getValue(colorName + 6),
            colorMap.getValue(colorName + 6),
            colorMap.getValue(colorName + 5),
            colorMap.getValue(colorName + 4),
            colorMap.getValue(colorName + 5),
            colorMap.getValue(colorName + 4),
            colorMap.getValue(colorName + 3),
            colorMap.getValue(colorName + 12),
            colorMap.getValue(colorName + 11),
            colorMap.getValue(colorName + 10),
            colorMap.getValue(colorName + 9),
            colorMap.getValue(colorName + 8),
            colorMap.getValue(colorName + 7),
            colorMap.getValue(colorName + 8),
            colorMap.getValue(colorName + 7),
            colorMap.getValue(colorName + 6),
            colorMap.getValue(colorName + 3),
            colorMap.getValue(colorName + 4),
            colorMap.getValue(colorName + 5),
            colorMap.getValue(colorName + "OnOriginDefaultDark"),
            colorMap.getValue(colorName + "OnOriginHoveredDark"),
            colorMap.getValue(colorName + "OnOriginPressedDark"),
            colorMap.getValue(colorName + "OriginDefaultDark"),
            colorMap.getValue(colorName + "OriginHoveredDark"),
            colorMap.getValue(colorName + "OriginPressedDark"),
        )

        fun light(colorMap: Map<String, Color>, colorName: String) = DSColorVariant(
            colorMap.getValue(colorName + 14),
            colorMap.getValue(colorName + 13),
            colorMap.getValue(colorName + 12),
            colorMap.getValue(colorName + 13),
            colorMap.getValue(colorName + 12),
            colorMap.getValue(colorName + 11),
            colorMap.getValue(colorName + 12),
            colorMap.getValue(colorName + 11),
            colorMap.getValue(colorName + 10),
            colorMap.getValue(colorName + 6).copy(0f),
            colorMap.getValue(colorName + 6).copy(0.08f),
            colorMap.getValue(colorName + 6).copy(0.16f),
            colorMap.getValue(colorName + 6).copy(0.24f),
            colorMap.getValue(colorName + 3),
            colorMap.getValue(colorName + 4),
            colorMap.getValue(colorName + 5),
            colorMap.getValue(colorName + 5),
            colorMap.getValue(colorName + 6),
            colorMap.getValue(colorName + 7),
            colorMap.getValue(colorName + 6),
            colorMap.getValue(colorName + 5),
            colorMap.getValue(colorName + 4),
            colorMap.getValue(colorName + 7),
            colorMap.getValue(colorName + 6),
            colorMap.getValue(colorName + 5),
            colorMap.getValue(colorName + 10),
            colorMap.getValue(colorName + 9),
            colorMap.getValue(colorName + 8),
            colorMap.getValue(colorName + 9),
            colorMap.getValue(colorName + 8),
            colorMap.getValue(colorName + 7),
            colorMap.getValue(colorName + 3),
            colorMap.getValue(colorName + 4),
            colorMap.getValue(colorName + 5),
            colorMap.getValue(colorName + 6),
            colorMap.getValue(colorName + 5),
            colorMap.getValue(colorName + 4),
            colorMap.getValue(colorName + 7),
            colorMap.getValue(colorName + 6),
            colorMap.getValue(colorName + 5),
            colorMap.getValue(colorName + 14),
            colorMap.getValue(colorName + 13),
            colorMap.getValue(colorName + 12),
            colorMap.getValue(colorName + "OnOriginDefaultLight"),
            colorMap.getValue(colorName + "OnOriginHoveredLight"),
            colorMap.getValue(colorName + "OnOriginPressedLight"),
            colorMap.getValue(colorName + "OriginDefaultLight"),
            colorMap.getValue(colorName + "OriginHoveredLight"),
            colorMap.getValue(colorName + "OriginPressedLight"),
        )

    }
}

class DesignSystemColorScheme(
    val neutral: DSColorVariant,
    val brand: DSColorVariant,
    val informational: DSColorVariant,
    val warning: DSColorVariant,
    val successful: DSColorVariant,
    val critical: DSColorVariant,
    val yellow: DSColorVariant,
    val orange: DSColorVariant,
    val red: DSColorVariant,
    val pink: DSColorVariant,
    val violet: DSColorVariant,
    val blue: DSColorVariant,
    val cyan: DSColorVariant,
    val turquoise: DSColorVariant,
    val green: DSColorVariant,
) {
    internal companion object {
        fun getColorSchemeDark(colorMap: Map<String, Color>): DesignSystemColorScheme =
            DesignSystemColorScheme(
                neutral = DSColorVariant.dark(colorMap, "neutral"),
                brand = DSColorVariant.dark(colorMap, "brand"),
                informational = DSColorVariant.dark(colorMap, "informational"),
                warning = DSColorVariant.dark(colorMap, "warning"),
                successful = DSColorVariant.dark(colorMap, "successful"),
                critical = DSColorVariant.dark(colorMap, "critical"),
                yellow = DSColorVariant.dark(colorMap, "yellow"),
                orange = DSColorVariant.dark(colorMap, "orange"),
                red = DSColorVariant.dark(colorMap, "red"),
                pink = DSColorVariant.dark(colorMap, "pink"),
                violet = DSColorVariant.dark(colorMap, "violet"),
                blue = DSColorVariant.dark(colorMap, "blue"),
                cyan = DSColorVariant.dark(colorMap, "cyan"),
                turquoise = DSColorVariant.dark(colorMap, "turquoise"),
                green = DSColorVariant.dark(colorMap, "green"),
            )

        fun getColorSchemeLight(colorMap: Map<String, Color>): DesignSystemColorScheme =
            DesignSystemColorScheme(
                neutral = DSColorVariant.light(colorMap, "neutral"),
                brand = DSColorVariant.light(colorMap, "brand"),
                informational = DSColorVariant.light(colorMap, "informational"),
                warning = DSColorVariant.light(colorMap, "warning"),
                successful = DSColorVariant.light(colorMap, "successful"),
                critical = DSColorVariant.light(colorMap, "critical"),
                yellow = DSColorVariant.light(colorMap, "yellow"),
                orange = DSColorVariant.light(colorMap, "orange"),
                red = DSColorVariant.light(colorMap, "red"),
                pink = DSColorVariant.light(colorMap, "pink"),
                violet = DSColorVariant.light(colorMap, "violet"),
                blue = DSColorVariant.light(colorMap, "blue"),
                cyan = DSColorVariant.light(colorMap, "cyan"),
                turquoise = DSColorVariant.light(colorMap, "turquoise"),
                green = DSColorVariant.light(colorMap, "green"),
            )

    }
}

val LocalColors =
    staticCompositionLocalOf { DesignSystemColorScheme.getColorSchemeLight(DeutscheBahnColorMap) }
val LocalActiveColor =
    staticCompositionLocalOf { DesignSystemColorScheme.getColorSchemeLight(DeutscheBahnColorMap).neutral }
