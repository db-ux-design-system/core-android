package com.dbsystel.designsystem.foundation.theme.core
  
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

fun Modifier.dbShadow(
    elevation: DBElevation,
    shape: Shape = RectangleShape,
    clip: Boolean = true,
): Modifier {
    return drawWithCache {
        onDrawWithContent {
            fun drawShadow(config: DBElevationShadowConfig) {
                drawIntoCanvas { canvas ->
                    val spreadRadiusPx = config.spread.toPx()
                    val hasSpreadRadius = spreadRadiusPx != 0f

                    val shadowOutline = shape.createOutline(size = when {
                        hasSpreadRadius -> size.let { (width, height) ->
                            (2 * spreadRadiusPx).let { outset ->
                                Size(
                                    width = width + outset, height = height + outset
                                )
                            }
                        }

                        else -> size
                    }, layoutDirection = layoutDirection, density = this)

                    canvas.save()

                    canvas.drawOutline(outline = shadowOutline, paint = Paint().also { paint ->
                        paint.asFrameworkPaint().apply {
                            this.color = Color.Transparent.toArgb()
                            setShadowLayer(
                                config.blur.toPx(),
                                config.offset.x.toPx() - spreadRadiusPx,
                                config.offset.y.toPx() - spreadRadiusPx,
                                config.color.toArgb(),
                            )
                        }
                    })

                    canvas.restore()
                }
            }

            elevation.config.forEach(::drawShadow)
            drawContent()
        }
    }.let { modifier -> if (clip) modifier.clip(shape) else modifier }
}

internal data class DBElevationShadowConfig(
    val offset: DpOffset,
    val blur: Dp,
    val spread: Dp,
    val color: Color,
)

enum class DBElevation(internal val config: List<DBElevationShadowConfig>) {
    SM(
        listOf(
            DBElevationShadowConfig(DpOffset(0.dp, 0.dp), 1.dp, (-1).dp, Color(0x00000033)),
            DBElevationShadowConfig(DpOffset(0.dp, 0.dp), 4.dp, 1.dp, Color(0x0000001f)),
            DBElevationShadowConfig(DpOffset(0.dp, 0.dp), 2.dp, 0.dp, Color(0x00000024)),
        ),
    ),
    MD(
        listOf(
            DBElevationShadowConfig(DpOffset(0.dp, 0.dp), 2.dp, (-1).dp, Color(0x00000033)),
            DBElevationShadowConfig(DpOffset(0.dp, 0.dp), 8.dp, 1.dp, Color(0x0000001f)),
            DBElevationShadowConfig(DpOffset(0.dp, 0.dp), 4.dp, 0.dp, Color(0x00000024)),
        ),
    ),
    LG(
        listOf(
            DBElevationShadowConfig(DpOffset(0.dp, 0.dp), 4.dp, (-3).dp, Color(0x00000033)),
            DBElevationShadowConfig(DpOffset(0.dp, 0.dp), 16.dp, 3.dp, Color(0x0000001f)),
            DBElevationShadowConfig(DpOffset(0.dp, 0.dp), 8.dp, 1.dp, Color(0x00000024)),
        ),
    ),
}
