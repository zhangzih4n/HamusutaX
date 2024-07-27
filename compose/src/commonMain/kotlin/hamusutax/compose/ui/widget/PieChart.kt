package hamusutax.compose.ui.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import hamusutax.collections.sumOf
import kotlin.math.PI
import kotlin.math.atan2

data class PieChartItem(
    val name: String,
    val value: Float,
    val color: Color
)

/**
 * @param startAngle 起始角度（以度为单位）。0 代表 3 点钟方向
 */
@Composable
fun PieChart(
    items: List<PieChartItem>,
    modifier: Modifier = Modifier,
    startAngle: Float = 0F
) {
    fun calculateAngle(center: Offset, touchPoint: Offset): Float {
        val deltaX = touchPoint.x - center.x
        val deltaY = touchPoint.y - center.y
        return atan2(deltaY, deltaX) * (180.0 / PI).toFloat()
    }

    var rotation by remember { mutableFloatStateOf(startAngle) } // 当前起始点角度
    var arcCenter by remember { mutableStateOf(Offset.Zero) } // 圆心
    var dragInitialRotation by remember { mutableFloatStateOf(0F) } // 拖动时起始点角度

    val result by remember { derivedStateOf {
        var previousAngle = rotation
        val sum = items.sumOf { it.value }
        items.mapIndexed { index, item ->
            val angle = (item.value / sum) * 360
            val startAngle = previousAngle
            previousAngle += angle
            val sweepAngle = if (index == items.size - 1) rotation + 360 - startAngle else (item.value / sum) * 360
            item to (startAngle to sweepAngle)
        }.toMap()
    } }

    Box(
        modifier = modifier.fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        dragInitialRotation =
                            calculateAngle(arcCenter, offset)
                    },
                    onDrag = { change, _ ->
                        val newAngle =
                            calculateAngle(arcCenter, change.position)
                        rotation += newAngle - dragInitialRotation
                        dragInitialRotation = newAngle
                    }
                )
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val minEdge = minOf(size.width, size.height)
            val arcSize = Size(minEdge, minEdge)

            arcCenter = Offset(arcSize.width / 2, arcSize.height / 2)

            result.forEach { (item, angles) ->
                drawArc(
                    color = item.color,
                    startAngle = angles.first,
                    sweepAngle = angles.second,
                    useCenter = true,
                    size = arcSize,
                )
            }
        }
    }
}

@Composable
internal fun PieChartPreview() {
    val data = listOf(
        PieChartItem("Rank 3", 30F, Color.Blue),
        PieChartItem("Rank 5", 10F, Color.Red),
        PieChartItem("Rank 4", 20F, Color.Green)
    )
    PieChart(data)
}
