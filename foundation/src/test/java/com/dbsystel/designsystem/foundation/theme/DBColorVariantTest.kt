package com.dbsystel.designsystem.foundation.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.foundation.core.PaparazziTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(Parameterized::class)
class DBColorVariantTest(
    private val name: String,
    private val colorVariant: @Composable () -> DBColorVariant,
) : PaparazziTest() {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun colorVariants(): List<Array<Any>> = listOf(
            // Semantic colors
            arrayOf("Neutral", colorVariantOf { DBTheme.colors.neutral }),
            arrayOf("Critical", colorVariantOf { DBTheme.colors.critical }),
            arrayOf("Informational", colorVariantOf { DBTheme.colors.informational }),
            arrayOf("Successful", colorVariantOf { DBTheme.colors.successful }),
            arrayOf("Warning", colorVariantOf { DBTheme.colors.warning }),
            arrayOf("Brand", colorVariantOf { DBTheme.colors.brand }),

            // Additional colors
            arrayOf("Yellow", colorVariantOf { DBTheme.colors.yellow }),
            arrayOf("Orange", colorVariantOf { DBTheme.colors.orange }),
            arrayOf("Red", colorVariantOf { DBTheme.colors.red }),
            arrayOf("Burgundy", colorVariantOf { DBTheme.colors.burgundy }),
            arrayOf("Pink", colorVariantOf { DBTheme.colors.pink }),
            arrayOf("Violet", colorVariantOf { DBTheme.colors.violet }),
            arrayOf("Blue", colorVariantOf { DBTheme.colors.blue }),
            arrayOf("Cyan", colorVariantOf { DBTheme.colors.cyan }),
            arrayOf("Turquoise", colorVariantOf { DBTheme.colors.turquoise }),
            arrayOf("LightGreen", colorVariantOf { DBTheme.colors.lightGreen }),
            arrayOf("Green", colorVariantOf { DBTheme.colors.green }),
        )

        private fun colorVariantOf(block: @Composable () -> DBColorVariant): @Composable () -> DBColorVariant =
            block
    }

    @Test
    fun test_color_variant() {
        paparazzi.snapshot {
            DBTheme {
                ColorVariantFrame(
                    name = name,
                    colorVariant = colorVariant,
                )
            }
        }
    }
}

@Composable
private fun ColorVariantFrame(
    name: String,
    colorVariant: @Composable () -> DBColorVariant,
) {
    Column(
        modifier = Modifier.background(DBTheme.activeColor.bgBasicLevel1Default),
        verticalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedLg),
    ) {
        Text(
            text = name,
            style = DBTheme.typography.h5,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedLg),
        ) {
            listOf(false, true).forEach { darkTheme ->
                DBTheme(
                    darkTheme = darkTheme
                ) {
                    Column(
                        modifier = Modifier
                            .width(IntrinsicSize.Max)
                            .background(DBTheme.activeColor.bgBasicLevel1Default)
                            .padding(DBTheme.dimensions.spacing.fixedXs),
                        verticalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedLg),
                    ) {
                        val basicLevels = listOf(
                            colorVariant().bgBasicLevel1Default to "bg-basic-level-1",
                            colorVariant().bgBasicLevel2Default to "bg-basic-level-2",
                            colorVariant().bgBasicLevel3Default to "bg-basic-level-3",
                        )
                        val basicTransparent = listOf(
                            colorVariant().bgBasicTransparentFullDefault to "bg-basic-transparent-full",
                            colorVariant().bgBasicTransparentSemiDefault to "bg-basic-transparent-semi",
                        )
                        val basicBackgrounds = listOf(
                            colorVariant().onBgBasicEmphasis100Default to "on-bg-basic-emphasis-100",
                            colorVariant().onBgBasicEmphasis90Default to "on-bg-basic-emphasis-90",
                            colorVariant().onBgBasicEmphasis80Default to "on-bg-basic-emphasis-80",
                            colorVariant().onBgBasicEmphasis70Default to "on-bg-basic-emphasis-70",
                            colorVariant().onBgBasicEmphasis60Default to "on-bg-basic-emphasis-60",
                            colorVariant().onBgBasicEmphasis50Default to "on-bg-basic-emphasis-50",
                        )
                        val invertedContrast = listOf(
                            colorVariant().bgInvertedContrastMaxDefault to "bg-inverted-contrast-max",
                            colorVariant().bgInvertedContrastHighDefault to "bg-inverted-contrast-high",
                            colorVariant().bgInvertedContrastLowDefault to "bg-inverted-contrast-low",
                        )
                        val invertedBackgrounds = listOf(
                            colorVariant().onBgInvertedDefault to "on-bg-inverted",
                        )
                        val vibrant = listOf(colorVariant().bgVibrantDefault to "bg-vibrant")
                        val vibrantBackgrounds =
                            listOf(colorVariant().onBgVibrantDefault to "on-bg-vibrant")
                        val origin = listOf(colorVariant().originDefault to "origin")
                        val originBackgrounds =
                            listOf(colorVariant().onOriginDefault to "on-origin")

                        ColorFrame(
                            backgroundColors = basicLevels,
                            foregroundColors = basicBackgrounds,
                        )
                        HorizontalDivider()
                        ColorFrame(
                            backgroundColors = basicTransparent,
                            foregroundColors = basicBackgrounds,
                        )
                        HorizontalDivider()
                        ColorFrame(
                            backgroundColors = invertedContrast,
                            foregroundColors = invertedBackgrounds,
                        )
                        HorizontalDivider()
                        ColorFrame(
                            backgroundColors = vibrant,
                            foregroundColors = vibrantBackgrounds,
                        )
                        HorizontalDivider()
                        ColorFrame(
                            backgroundColors = origin,
                            foregroundColors = originBackgrounds,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ColorFrame(
    backgroundColors: List<Pair<Color, String>>,
    foregroundColors: List<Pair<Color, String>>,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedSm),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixed2xs),
        ) {
            backgroundColors.forEach { (color, name) ->
                Row(horizontalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedXs)) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(color),
                    )
                    Text(
                        text = name,
                        style = DBTheme.typography.bodyMd,
                        color = DBTheme.activeColor.onBgBasicEmphasis80Default,
                    )
                }
            }
        }
        Column(
            modifier = Modifier.padding(horizontal = DBTheme.dimensions.spacing.fixedLg),
            verticalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixed2xs),
        ) {
            foregroundColors.forEach { (color, name) ->
                Row(horizontalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedXs)) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(color),
                    )
                    Text(
                        text = name,
                        style = DBTheme.typography.bodyMd,
                        color = DBTheme.activeColor.onBgBasicEmphasis80Default,
                    )
                }
            }
        }
    }
}
