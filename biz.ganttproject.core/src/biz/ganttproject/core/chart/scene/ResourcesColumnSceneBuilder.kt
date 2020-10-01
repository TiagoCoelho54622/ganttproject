/*
Copyright 2020 Dmitry Kazakov, BarD Software s.r.o

This file is part of GanttProject, an open-source project management tool.

GanttProject is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

GanttProject is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with GanttProject.  If not, see <http://www.gnu.org/licenses/>.
*/
package biz.ganttproject.core.chart.scene

import biz.ganttproject.core.chart.canvas.Canvas
import biz.ganttproject.core.chart.canvas.TextMetrics
import java.awt.Color

private const val OFFSET = 5
private val ODD_ROW_COLOR = Color(0.933f, 0.933f, 0.933f)
private val EVEN_ROW_COLOR = null

class ResourcesColumnSceneBuilder(
    private val resources: List<String>,
    private val input: InputApi,
    val canvas: Canvas = Canvas()
) {
  val width = resources.map { input.textMetrics.getTextLength(it) }.maxOrNull() ?: 0 + OFFSET * 2

  fun build() {
    canvas.clear()
    var y = input.yCanvasOffset
    var isOddRow = false
    resources.forEach {
      val rectangle = canvas.createRectangle(0, y, width, input.rowHeight)
      rectangle.backgroundColor = if (isOddRow) ODD_ROW_COLOR else EVEN_ROW_COLOR
      val text = canvas.createText(OFFSET, rectangle.middleY, it)
      text.setAlignment(Canvas.HAlignment.LEFT, Canvas.VAlignment.CENTER)
      y += input.rowHeight
      isOddRow = !isOddRow
    }
  }

  interface InputApi {
    val textMetrics: TextMetrics
    val yCanvasOffset: Int
    val rowHeight: Int
  }
}
